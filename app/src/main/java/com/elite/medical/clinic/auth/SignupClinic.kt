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
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.elite.medical.utils.InputValidation
import com.elite.medical.databinding.ActivitySignupClinicBinding
import com.elite.medical.nurse.LoginNurse
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
    private lateinit var str: String
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


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupClinicBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeBindings()
        initListeners()

//        binding.tvSignIn.setOnClickListener {
//            val intent = Intent(this, LoginClinic::class.java)
//            startActivity(intent)
//
//        }
//
//        binding.btnCancel.setOnClickListener {
//            finish()
//        }
//
//        binding.etUploadClinicLicense.setOnClickListener {
//            pickMedia.launch(arrayOf("*/*"))
//        }


        binding.btnSignup.setOnClickListener {
            //if (validateInputs()) {

                str = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)

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
                    str,
                    vatNo.text.toString(),
                    cstNo.text.toString(),
                    serviceTaxNo.text.toString(),
                    clinicUIN.text.toString(),
                    declaration.text.toString(),
                )

                CoroutineScope(Dispatchers.Main).launch {
                    AuthAPI.registerClinic(
                        clinicDetails,
                        object : AuthAPI.Companion.AuthSignUpCallbackNurse {
                            override fun onSignUpSuccess(message: String) {
                                Toast.makeText(this@SignupClinic, message, Toast.LENGTH_SHORT)
                                    .show()
                                finish()
                            }
                        }
                    )
                }


            //}

        }


    }

    private fun initListeners() {

        binding.tvSignIn.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.etUploadClinicLicense.setOnClickListener(this)

    }
    override fun onClick(view: View?) {

        println("insdie loop")
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

        var validated = false

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
            uploadLicense,
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
            }
            validated = str == ""
        }

        if (InputValidation.emailValidation(email = email.text.toString()) != "") {
            email.error = "Please Enter Valid email."
            email.requestFocus()
            validated = false
        }

        if (InputValidation.isFieldEmpty(uploadLicense.text.toString()) != "") {
            uploadLicense.error = "Please upload a file."
            uploadLicense.requestFocus()
            validated = false
        }

        return validated
    }

    private val pickMedia = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        val etUploadLicense = binding.etUploadClinicLicense
        etUploadLicense.error = null
        binding.imgPreview.setImageURI(it)

        imageBitmap = imageToBitmap(it)!!
    }
    private fun imageToBitmap(it: Uri?): Bitmap? =

        MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, it)



    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf("Take Photo", "Upload from storage", "Exit")
        val builder = AlertDialog.Builder(context)
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when (optionsMenu[i]) {

                "Take Photo" -> {
                    openCamera()
                }

                "Upload from storage" -> pickMedia.launch(arrayOf("image/jpeg"))

                "Exit" -> {
                    dialogInterface.dismiss()
                    binding.etUploadClinicLicense.text = ""
                }
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


    private fun openCamera() {
        val isStorageAvailable = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        if (isStorageAvailable) {
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.elite.medical.FileUsage",
                createImageFile()
            )
            takePicture.launch(photoURI)

        } else {
            Toast.makeText(
                this,
                "Storage Full! make sure you have space in your storage",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private var photoFile: File? = null

    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(java.util.Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("IMG_${timeStamp}_", ".jpg", storageDir).apply {
            photoFile = this
        }
    }

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {

                val photoUri: Uri = photoFile?.let {
                    FileProvider.getUriForFile(
                        this, "com.elite.medical.FileUsage", it
                    )
                } ?: return@registerForActivityResult


                binding.imgPreview.setImageURI(photoUri)
                imageBitmap = imageToBitmap(photoUri)!!
                str = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)

            } else {
                Toast.makeText(this, "Unable to capture image", Toast.LENGTH_SHORT).show()
            }
        }

}