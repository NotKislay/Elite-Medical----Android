package com.elite.medical.nurse.fragments.home

import android.content.ContentValues
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.elite.medical.databinding.FragmentClockInOutBinding
import com.elite.medical.nurse.viewmodels.UserNurseMainViewModel
import com.elite.medical.utils.GPSLocation
import com.elite.medical.utils.HelperMethods
import java.io.ByteArrayOutputStream
import java.util.Locale

class ClockInOutFragment : Fragment() {

    private lateinit var binding: FragmentClockInOutBinding
    private val viewModel by viewModels<UserNurseMainViewModel>()
    private lateinit var location: GPSLocation
    private var photoURI: Uri? = null
    private var imageBase64: String = ""
    private lateinit var imageBitmap: Bitmap

    private var imageCapture: ImageCapture? = null
    private val TAG = "Camera"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClockInOutBinding.inflate(inflater, container, false)

        HelperMethods.requestCameraPermission(requireActivity())
        location = HelperMethods.getLocation(requireActivity())


        viewModel.clockOUTCallback = {
            Toast.makeText(requireContext(), it?.message, Toast.LENGTH_SHORT).show()
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.layoutClockOut.isVisible = photoURI != null

        binding.clockOutBtn.setOnClickListener { clockOut() }



        startCamera()

        binding.btnPreview.setOnClickListener { takePhoto() }
        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

        return binding.root
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat("ddMMyyyy_hhmmss", Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "elite_medical_$name")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Elite Medical")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireActivity().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun
                        onImageSaved(output: ImageCapture.OutputFileResults) {
                    photoURI = output.savedUri!!
                    imageBitmap = imageToBitmap(output.savedUri)!!
                    binding.layoutClockOut.isVisible = photoURI != null
                    binding.imgCaptured.setImageURI(output.savedUri)

                }
            }
        )
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        val image = stream.toByteArray()
        return Base64.encodeToString(image, Base64.NO_WRAP)
    }

    private fun imageToBitmap(it: Uri?): Bitmap? =
        MediaStore.Images.Media.getBitmap(requireContext().contentResolver, it)

    private fun clockOut() {

        binding.loader.isVisible = true
        binding.clockOutBtn.isVisible = false


        imageBase64 = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)


        val gps = HelperMethods.makeAddressFromLocation(requireContext(), location)!!
        viewModel.clockOut(gps, imageBase64)

    }


}