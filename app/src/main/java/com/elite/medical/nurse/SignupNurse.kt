package com.elite.medical.nurse

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.utils.HelperMethods
import com.elite.medical.utils.InputValidation
import com.elite.medical.databinding.ActivitySignupNurseBinding
import com.elite.medical.retrofit.apis.AuthAPI
import com.elite.medical.retrofit.requestmodels.RegisterNurseModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.sql.Date
import java.util.Calendar


class SignupNurse : AppCompatActivity() {
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
    private var photoURI: Uri? = null

    private lateinit var binding: ActivitySignupNurseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup_nurse)

        initializeBindings()

        spinnerNurseType = binding.nurseType
        val adapter1 = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nurseType
        )
        spinnerNurseType.adapter = adapter1

        spinnerNCLEXStatus = binding.NCLEXStatus
        val adapter2 = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nclexStatus
        )
        spinnerNCLEXStatus.adapter = adapter2

        spinnerUsImmigrationStatus = binding.etUSImmigrationStatus
        val adapter3 = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            usImmigrationStatus
        )
        spinnerUsImmigrationStatus.adapter = adapter3

        spinnercgfnStatus = binding.etCGFNSStatus
        val adapter4 = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cgfnStatus
        )
        spinnercgfnStatus.adapter = adapter4

        val tvDob = binding.tvDob
        val tvLicenceIssueDate = binding.tvLicenceIssueDate
        val tvLicenceExpiryDate = binding.tvLicenceExpiryDate

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, LoginNurse::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
        binding.etUploadLicense.setOnClickListener {
            chooseImage(this)
        }
        binding.tvDob.setOnClickListener {
            openDatePicker(tvDob, "D.O.B")
        }
        binding.tvLicenceIssueDate.setOnClickListener {
            openDatePicker(tvLicenceIssueDate, "Issue Date")
        }
        binding.tvLicenceExpiryDate.setOnClickListener {
            openDatePicker(tvLicenceExpiryDate, "Expiry Date")
        }
        binding.btnSignup.setOnClickListener {

            //val fileDir = activity?.applicationContext?.filesDir
            val fileDir = applicationContext?.filesDir
            val file = File(fileDir, "image.png")
//            val inputStream = contentResolver?.openInputStream(photoURI!!)
//            val outputStream = FileOutputStream(file)
//            inputStream!!.copyTo(outputStream)
//            println("Dir- $fileDir, FIle is $file, istream $inputStream")
            if (validateInputs()) {

                val userDetails = RegisterNurseModel(
                    name.text.toString(),
                    email.text.toString(),
                    address.text.toString(),
                    mobile.text.toString(),
                    dob.text.toString(),
                    city.text.toString(),
                    state.text.toString(),
                    zip.text.toString(),
                    spinnerNCLEXStatus.selectedItem.toString(),
                    spinnerUsImmigrationStatus.selectedItem.toString(),
                    spinnercgfnStatus.selectedItem.toString(),
                    spinnerNurseType.selectedItem.toString(),
                    "test",
                    //uploadLicense.text.toString(),
                    licenceIssueDate.text.toString(),
                    licenceExpiryDate.text.toString(),
                    yearsOfExperience.text.toString(),
                    speciality.text.toString()
                )

                CoroutineScope(Dispatchers.Main).launch {
                    AuthAPI.registerNurse(userDetails, object : AuthAPI.Companion.AuthSignUpCallbackNurse {
                        override fun onSignUpSuccess(message: String) {
                            Toast.makeText(this@SignupNurse, message, Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                    )
                }
            }


        }


        HelperMethods.requestCameraPermission(this)

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

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) {
                binding.etUploadLicense.text = uri.path
            }
        }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImage = result.data?.data.toString()
                Toast.makeText(this, selectedImage, Toast.LENGTH_SHORT).show()
            }
        }

    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf("Take Photo", "Upload from storage", "Exit")
        val builder = AlertDialog.Builder(context)
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when (optionsMenu[i]) {
                "Take Photo" -> {
                    // Open the camera and get the photo
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                    resultLauncher.launch(takePicture)
                }

                "Upload from storage" -> {
                    pickMedia.launch(arrayOf("*/*"))

//                    registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri?->
//
//                        println(uri)
//                    }
                }

                "Exit" -> {
                    dialogInterface.dismiss()
                    binding.etUploadLicense.text = ""
                }
            }
        }
        builder.show()
        binding.etUploadLicense.error = null
    }

    private fun validateInputs(): Boolean {

        var validated = false

        val inputArray = arrayOf(
            name,
            email,
            address,
            mobile,
            dob,
            city,
            state,
            zip,
            uploadLicense,
            licenceIssueDate,
            licenceExpiryDate,
            yearsOfExperience,
            speciality
        )

        inputArray.map {
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

}