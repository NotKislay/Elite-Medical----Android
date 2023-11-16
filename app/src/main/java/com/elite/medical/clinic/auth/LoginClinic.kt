package com.elite.medical.clinic.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.EliteMedical
import com.elite.medical.clinic.ui.ClinicNavHost
import com.elite.medical.databinding.ActivityLoginClinicBinding
import com.elite.medical.retrofit.apis.AuthAPI
import com.elite.medical.utils.ForgotPassword
import com.elite.medical.utils.HelperMethods
import com.google.android.material.snackbar.Snackbar

class LoginClinic : AppCompatActivity() {

    private lateinit var binding: ActivityLoginClinicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginClinicBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (EliteMedical.AuthTokenClinic != null) {
            val intent = Intent(this@LoginClinic, ClinicNavHost::class.java)
            startActivity(intent)
        }

        binding.btnCancel.setOnClickListener { finish() }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignupClinic::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSignIn.setOnClickListener {

            binding.btnSignIn.visibility = View.INVISIBLE
            binding.loader.visibility = View.VISIBLE

            login(
                binding.etEmail.text.toString(), binding.etPassword.text.toString()
            )
        }


    }

    override fun onResume() {
        super.onResume()
        binding.btnSignIn.visibility = View.VISIBLE
        binding.loader.visibility = View.GONE

    }

    private fun login(email: String, password: String) {
        if (isInputValid()) {
            HelperMethods.hideKeyboard(this)
            binding.loader.visibility = View.VISIBLE
            binding.btnSignIn.visibility = View.INVISIBLE
            AuthAPI.loginClinic(email, password, object : AuthAPI.Companion.LoginClinicCallback {
                override fun onClinicLogin(message: String?) {
                    if (message == null) {
                        val intent = Intent(this@LoginClinic, ClinicNavHost::class.java)
                        startActivity(intent)
                        binding.loader.visibility = View.GONE
                    } else {
                        val snackBar =
                            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
                        snackBar.setAction("Retry") { snackBar.dismiss() }
                        snackBar.show()
                    }


                    binding.loader.visibility = View.GONE
                    binding.btnSignIn.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun isInputValid(): Boolean {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        val emailErrorText = String.validateEmail(email)
        val passwordErrorText = if (password.isNotEmpty()) null else "Please Enter Password"

        if (emailErrorText != null) binding.etEmail.error = emailErrorText
        if (passwordErrorText != null) binding.etPassword.error = passwordErrorText
        if (emailErrorText == null && passwordErrorText == null) return true
        return false
    }

    private fun String.Companion.validateEmail(email: String): String? {
        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
        if (email.isEmpty()) return "please enter your email."
        else if (!email.matches(emailRegex.toRegex())) return "Please enter valid email"
        return null
    }

}