package com.elite.medical.clinic.auth

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import com.elite.medical.utils.InputValidation
import com.elite.medical.databinding.ActivitySignupClinicBinding
import com.elite.medical.retrofit.apis.AuthAPI
import com.elite.medical.retrofit.requestmodels.RegisterClinicModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class SignupClinic : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignupClinicBinding
    private lateinit var imageBitmap: Bitmap
    private lateinit var base64Image: String
    private lateinit var imageBase64: String
    lateinit var name: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var address: TextInputEditText
    private lateinit var phone: TextInputEditText
    lateinit var city: TextInputEditText
    lateinit var state: TextInputEditText
    lateinit var zip: TextInputEditText
    lateinit var clinicType: TextInputEditText
    lateinit var location: TextInputEditText
    private lateinit var uploadLicense: MaterialButton
    lateinit var vatNo: TextInputEditText
    lateinit var cstNo: TextInputEditText
    lateinit var serviceTaxNo: TextInputEditText
    lateinit var clinicUIN: TextInputEditText
    lateinit var declaration: TextInputEditText

    private val viewModel by viewModels<ClinicAuthViewModel>()
    private var isImageSet = false
    private var photoFile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupClinicBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeBindings()
        initListeners()

        viewModel.registerClinicCallback = {
            binding.loader.isVisible = false
            binding.btnSignup.isVisible = true

            if (it?.status == "success") {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                finish()
            } else Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
        }


        binding.btnSignup.setOnClickListener {
            if (validateInputs()) {

                binding.loader.isVisible = true
                binding.btnSignup.isVisible = false

                val clinicDetails = RegisterClinicModel(
                    name.text.toString(),
                    email.text.toString(),
                    address.text.toString(),
                    phone.text.toString(),
                    city.text.toString(),
                    state.text.toString(),
                    zip.text.toString(),
                    clinicType.text.toString(),
                    location.text.toString(),
                    imageBase64,
                    vatNo.text.toString(),
                    cstNo.text.toString(),
                    serviceTaxNo.text.toString(),
                    clinicUIN.text.toString(),
                    declaration.text.toString(),
                )

                viewModel.registerClinic(clinicDetails)


            }
        }


    }

    private fun initializeBindings() {
        name = binding.etName
        email = binding.etEmail
        address = binding.etAddress
        phone = binding.etContactNo
        city = binding.etCity
        state = binding.etState
        zip = binding.etZip
        clinicType = binding.etClinicType
        location = binding.etLocation
        uploadLicense = binding.etUploadClinicLicense
        vatNo = binding.etVATTIN
        cstNo = binding.etCstNo
        serviceTaxNo = binding.etServiceTaxNo
        clinicUIN = binding.etClinicUIN
        declaration = binding.etDeclaration
    }

    private fun initListeners() {

        binding.tvSignIn.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.etUploadClinicLicense.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when (view?.id) {


            binding.tvSignIn.id -> {
                val intent = Intent(this, LoginClinic::class.java)
                startActivity(intent)
                finish()
            }

            binding.btnCancel.id -> finish()


            binding.etUploadClinicLicense.id -> chooseImage(this@SignupClinic)

        }

    }

    private fun validateInputs(): Boolean {

        var validated: Boolean

        if (!isImageSet) {
            binding.etUploadClinicLicense.requestFocus()
            binding.etUploadClinicLicense.error = "choose an image"
            validated = false
        } else validated = true

        val inputFields = arrayOf(
            name,
            email,
            address,
            phone,
            city,
            state,
            zip,
            clinicType,
            location,
            vatNo,
            cstNo,
            serviceTaxNo,
            clinicUIN,
            declaration
        )




        inputFields.map {
            val str = InputValidation.isFieldEmpty(it.text.toString())
            if (str != "") {
                it.error = str
                it.requestFocus()
                validated = false
            }
        }


        return validated
    }


    private fun imageToBitmap(it: Uri?): Bitmap? =

        MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, it)


    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf("Take Photo", "Select from storage", "Exit")
        val builder = AlertDialog.Builder(context)
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when (optionsMenu[i]) {

                "Take Photo" -> openImageDialog()

                "Select from storage" -> pickImageFromGallery.launch(arrayOf("image/jpeg"))

                "Exit" -> dialogInterface.dismiss()

            }
        }
        builder.show()
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        val image = stream.toByteArray()
        return Base64.encodeToString(image, Base64.NO_WRAP)
    }


    private fun openImageDialog() {
        val isStorageAvailable = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        if (isStorageAvailable) {
            val photoURI: Uri = FileProvider.getUriForFile(
                this, "com.elite.medical.FileUsage", createImageFile()
            )
            pickImageFromCamera.launch(photoURI)

        } else {
            Toast.makeText(
                this, "Storage Full! make sure you have space in your storage", Toast.LENGTH_LONG
            ).show()
        }
    }


    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(java.util.Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("IMG_${timeStamp}_", ".jpg", storageDir).apply {
            photoFile = this
        }
    }

    private val pickImageFromCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {

                val photoUri: Uri = photoFile?.let {
                    FileProvider.getUriForFile(
                        this, "com.elite.medical.FileUsage", it
                    )
                } ?: return@registerForActivityResult

                binding.etUploadClinicLicense.error = null
                binding.imgPreview.setImageURI(photoUri)
                imageBitmap = imageToBitmap(photoUri)!!
                imageBase64 = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)
                isImageSet = true

            } else {
                Toast.makeText(this, "Unable to capture image", Toast.LENGTH_SHORT).show()
            }
        }

    private val pickImageFromGallery =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            if (it != null) {
                isImageSet = true
                binding.etUploadClinicLicense.error = null
                binding.imgPreview.setImageURI(it)
                imageBitmap = imageToBitmap(it)!!
                imageBase64 = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)
            } else {
                isImageSet = false
                Toast.makeText(this, "Image not Selected", Toast.LENGTH_SHORT).show()
           }
        }

}