package com.elite.medical.nurse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.EliteMedical
import com.elite.medical.databinding.ActivityLoginNurseBinding
import com.elite.medical.retrofit.apis.AuthAPI
import com.elite.medical.retrofit.responsemodel.auth.LoginResponse
import com.elite.medical.utils.ForgotPassword
import com.elite.medical.utils.HelperMethods
import com.elite.medical.utils.InputValidation

class LoginNurse : AppCompatActivity() {

    private lateinit var binding: ActivityLoginNurseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginNurseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (EliteMedical.AuthTokenNurse != null) {
            val intent = Intent(this, NurseDashboard::class.java)
            startActivity(intent)
        }


        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignupNurse::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSignIn.setOnClickListener {
            binding.loader.visibility = View.VISIBLE
            binding.btnSignIn.visibility = View.INVISIBLE
            login()
        }

        binding.tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignIn.visibility = View.VISIBLE

    }

    private fun login() {

        val email = binding.etEmail.text
        val password = binding.etPassword.text

        val validateEmail = InputValidation.emailValidation(email.toString())
        val validatePassword = InputValidation.passwordValidation(password.toString())

        if (validateEmail.isNotEmpty()) binding.etEmail.error = validateEmail

        if (validatePassword.isNotEmpty()) binding.etPassword.error = validatePassword


        if (validateEmail.isEmpty() && validatePassword.isEmpty()) {
            HelperMethods.hideKeyboard(this)
            AuthAPI.loginGeneric(
                email.toString(),
                password.toString(),
                object : AuthAPI.Companion.AuthLoginCallback {


                    override fun onLoginResult(loginResponse: LoginResponse) {

                        if (loginResponse.success == "success") {
                            EliteMedical.updateNurseToken(loginResponse.token)
                            val intent = Intent(this@LoginNurse, NurseDashboard::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@LoginNurse, loginResponse.message, Toast.LENGTH_SHORT
                            ).show()
                        }


                    }
                })


        }
    }

}