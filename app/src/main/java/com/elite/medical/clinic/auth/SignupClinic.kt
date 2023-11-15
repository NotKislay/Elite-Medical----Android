package com.elite.medical.clinic.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.elite.medical.utils.InputValidation
import com.elite.medical.databinding.ActivitySignupClinicBinding
import com.elite.medical.retrofit.apis.AuthAPI
import com.elite.medical.retrofit.requestmodels.RegisterClinicModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupClinic : AppCompatActivity() {

    private lateinit var binding: ActivitySignupClinicBinding
    lateinit var name: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var address: TextInputEditText
    lateinit var phone: TextInputEditText
    lateinit var city: TextInputEditText
    lateinit var state: TextInputEditText
    lateinit var zip: TextInputEditText
    lateinit var clinicType: TextInputEditText
    lateinit var location: TextInputEditText
    lateinit var uploadLicense: MaterialButton
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
        uploadLicense = binding.etUploadLicense
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

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, LoginClinic::class.java)
            startActivity(intent)

        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.etUploadLicense.setOnClickListener {
            pickMedia.launch(arrayOf("*/*"))
        }


        binding.btnSignup.setOnClickListener {
            if (validateInputs()) {

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
                    "test",
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
                            }
                        }
                    )
                }


            }

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
        val etUploadLicense = binding.etUploadLicense
        etUploadLicense.text = it!!.path
        etUploadLicense.error = null
    }


}