package com.elite.medical.nurse

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivitySignupNurseBinding
import com.elite.medical.nurse.auth.NurseAuthViewModel
import com.elite.medical.utils.HelperMethods
import com.elite.medical.utils.InputValidation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.ByteArrayOutputStream
import java.io.File
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class SignupNurse : AppCompatActivity(), View.OnClickListener {

    private var photoFile: File? = null
    private lateinit var imageBitmap: Bitmap
    private var imageBase64: String = ""

    private val nurseType = arrayOf("C.M.T", "C.N.A", "L.P.N", "R.N", "P.A", "N.P")
    private val usImmigrationStatus = arrayOf(
        "I am a US citizen or I have permission to work in the US",
        "I am in the US and require sponsorship",
        "I am outside the US and need sponsorship",
    )
    private val nclexStatus = arrayOf(
        "I have not yet applied for the NCLEX",
        "I have applied to take the NCLEX",
        "I am eligible to take the NCLEX ",
        "I have passed my NCLEX",
        "I hold a US nursing license"
    )
    private val cgfnStatus = arrayOf(
        "I have not yet applied for CGFNS certification",
        "I have applied for certification",
        "I am scheduled to take the exam ",
        "I have passed my CGFNS",
        "I hold CGFNS certification"
    )

    lateinit var name: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var address: TextInputEditText
    lateinit var mobile: TextInputEditText
    lateinit var dob: MaterialButton
    lateinit var city: TextInputEditText
    lateinit var state: TextInputEditText
    lateinit var zip: TextInputEditText
    lateinit var spinnerNurseType: Spinner
    lateinit var spinnerNCLEXStatus: Spinner
    lateinit var spinnerUsImmigrationStatus: Spinner
    lateinit var spinnercgfnStatus: Spinner
    lateinit var uploadLicense: MaterialButton
    lateinit var licenceIssueDate: MaterialButton
    lateinit var licenceExpiryDate: MaterialButton
    lateinit var yearsOfExperience: TextInputEditText
    lateinit var speciality: TextInputEditText

    private lateinit var binding: ActivitySignupNurseBinding
    private val viewModel by viewModels<NurseAuthViewModel>()
    private var isImageSet = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup_nurse)

        initializeBindings()
        initListeners()

        viewModel.registerNurseCallback = {
            binding.loader.isVisible = false
            binding.btnSignup.isVisible = true

            if (it?.status == "success") {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                finish()
            } else
                Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
        }

        setupNurseTypeAdapter(nurseType)
        setupNCLEXStatusAdapter(nclexStatus)
        setupUSImmigrationStatusAdapter(usImmigrationStatus)
        setupCGFNStatusAdapter(cgfnStatus)


        binding.btnSignup.setOnClickListener {
            if (validateInputs()) {

                binding.loader.isVisible = true
                binding.btnSignup.isVisible = false

//                str = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)

                val name = name.text.toString()
                val email = email.text.toString()
                val address = address.text.toString()
                val mobile = mobile.text.toString()
                val dob = dob.text.toString()
                val city = city.text.toString()
                val state = state.text.toString()
                val zip = zip.text.toString()
                val spinnerNCLEXStatus = spinnerNCLEXStatus.selectedItem.toString()
                val spinnerUsImmigrationStatus = spinnerUsImmigrationStatus.selectedItem.toString()
                val spinnercgfnStatus = spinnercgfnStatus.selectedItem.toString()
                val spinnerNurseType = spinnerNurseType.selectedItem.toString()
                val licenceIssueDate = licenceIssueDate.text.toString()
                val licenceExpiryDate = licenceExpiryDate.text.toString()
                val yearsOfExperience = yearsOfExperience.text.toString()
                val speciality = speciality.text.toString()

                viewModel.registerNurse(
                    name,
                    email,
                    address,
                    mobile,
                    dob,
                    city,
                    state,
                    zip,
                    spinnerNCLEXStatus,
                    spinnerUsImmigrationStatus,
                    spinnercgfnStatus,
                    spinnerNurseType,
                    imageBase64,
                    licenceIssueDate,
                    licenceExpiryDate,
                    yearsOfExperience,
                    speciality
                )
            }

        }


        HelperMethods.requestCameraPermission(this)

    }

    private fun setupCGFNStatusAdapter(cgfnStatus: Array<String>) {
        spinnercgfnStatus = binding.etCGFNSStatus
        val cGFNStatusAdapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cgfnStatus
        )
        spinnercgfnStatus.adapter = cGFNStatusAdapter
    }

    private fun setupUSImmigrationStatusAdapter(usImmigrationStatus: Array<String>) {
        spinnerUsImmigrationStatus = binding.etUSImmigrationStatus
        val usImmigrationStatusAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            usImmigrationStatus
        )
        spinnerUsImmigrationStatus.adapter = usImmigrationStatusAdapter
    }

    private fun setupNCLEXStatusAdapter(nCLEXStatus: Array<String>) {
        spinnerNCLEXStatus = binding.NCLEXStatus
        val nCLEXStatusAdapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nCLEXStatus
        )
        spinnerNCLEXStatus.adapter = nCLEXStatusAdapter
    }

    private fun setupNurseTypeAdapter(nurseType: Array<String>) {
        spinnerNurseType = binding.nurseType
        val nurseTypeAdapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nurseType
        )
        spinnerNurseType.adapter = nurseTypeAdapter
    }

    private fun initializeBindings() {

        name = binding.etNurseName
        email = binding.etEmail
        address = binding.etAddress
        mobile = binding.etPhone
        dob = binding.tvDob
        city = binding.etCity
        state = binding.etState
        zip = binding.etZipCode
        uploadLicense = binding.etUploadLicense
        licenceIssueDate = binding.tvLicenceIssueDate
        licenceExpiryDate = binding.tvLicenceExpiryDate
        yearsOfExperience = binding.etYearsOfExperience
        speciality = binding.etSpeciality

    }

    private fun openDatePicker(button: MaterialButton, text: String) {
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            this, DatePickerDialog.THEME_DEVICE_DEFAULT_DARK, { _, year, month, dayOfMonth ->
                val date = Date(year - 1900, month, dayOfMonth)
                val str = "$date"
                button.text = str
            }, y, m, d
        )
        dialog.show()
        dialog.setOnCancelListener {
            button.text = ""
        }
        button.error = null
    }


    private fun imageToBitmap(it: Uri?): Bitmap? =
        MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, it)


    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf("Take Photo", "Select from storage", "Exit")
        val builder = AlertDialog.Builder(context)
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when (optionsMenu[i]) {

                "Take Photo" -> {
                    openCamera()
                }

                "Select from storage" -> pickMedia.launch(arrayOf("image/jpeg"))

                "Exit" -> {
                    dialogInterface.dismiss()
                    binding.etUploadLicense.text = ""
                }
            }
        }
        builder.show()
    }

    private fun validateInputs(): Boolean {

        var validated: Boolean

        val inputArray = arrayOf(
            name,
            email,
            address,
            mobile,
            dob,
            city,
            state,
            zip,
            licenceIssueDate,
            licenceExpiryDate,
            yearsOfExperience,
            speciality
        )


        if (!isImageSet) {
            binding.etUploadLicense.requestFocus()
            binding.etUploadLicense.error = "choose select an image"
            validated = false

        } else
            validated = true

        inputArray.map {
            val str = InputValidation.isFieldEmpty(it.text.toString())
            if (str != "") {
                Log.i("check", "${it.hint} - is empty")
                it.error = str
                it.requestFocus()
                validated = false
            }
        }


        Log.i("test", "----------------------------------")
        Log.i("test", "Current state of validation - $validated")
        return validated
    }


    private fun bitmapToBase64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        val image = stream.toByteArray()
        return Base64.encodeToString(image, Base64.NO_WRAP)
    }

    private fun initListeners() {

        binding.tvSignIn.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.tvDob.setOnClickListener(this)
        binding.tvLicenceIssueDate.setOnClickListener(this)
        binding.tvLicenceExpiryDate.setOnClickListener(this)
        binding.etUploadLicense.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when (view?.id) {

            binding.tvSignIn.id -> {
                val intent = Intent(this, LoginNurse::class.java)
                startActivity(intent)
                finish()
            }

            binding.btnCancel.id -> finish()

            binding.tvDob.id -> {
                val tvDob = binding.tvDob
                openDatePicker(tvDob, "D.O.B")
            }

            binding.tvLicenceIssueDate.id -> {
                val tvLicenceIssueDate = binding.tvLicenceIssueDate
                openDatePicker(tvLicenceIssueDate, "Issue Date")
            }

            binding.tvLicenceExpiryDate.id -> {
                val tvLicenceExpiryDate = binding.tvLicenceExpiryDate
                openDatePicker(tvLicenceExpiryDate, "Expiry Date")
            }

            binding.etUploadLicense.id -> chooseImage(this@SignupNurse)

        }

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

                isImageSet = true
                binding.etUploadLicense.error = null
                binding.imgPreview.setImageURI(photoUri)
                imageBitmap = imageToBitmap(photoUri)!!
                imageBase64 = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)


            } else {
                Toast.makeText(this, "Unable to capture image", Toast.LENGTH_SHORT).show()
                isImageSet = false
            }
        }

    private val pickMedia = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        if(it != null){
            isImageSet = true
            binding.etUploadLicense.error = null
            binding.imgPreview.setImageURI(it)
            imageBitmap = imageToBitmap(it)!!
            imageBase64 = "data:image/jpg;base64," + bitmapToBase64(imageBitmap)
        }
        else{
            isImageSet = false
            Toast.makeText(this, "Image not Selected", Toast.LENGTH_SHORT).show()

        }

    }
}