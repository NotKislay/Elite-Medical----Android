package com.elite.medical.admin.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.elite.medical.EliteMedical
import com.elite.medical.admin.ui.AdminDashboard
import com.elite.medical.databinding.ActivityAdminLoginBinding
import com.elite.medical.retrofit.apis.AuthAPI
import com.elite.medical.utils.InputValidation
import com.elite.medical.retrofit.responsemodel.auth.LoginResponse
import com.elite.medical.utils.HelperMethods
import com.google.android.material.snackbar.Snackbar

class LoginAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityAdminLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (EliteMedical.AuthTokenAdmin != null) {
            val intent = Intent(this, AdminDashboard::class.java)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener { login() }
        binding.btnCancel.setOnClickListener { finish() }


    }

    override fun onResume() {
        super.onResume()
        binding.btnSignIn.visibility = View.VISIBLE
    }

    private fun login() {

        binding.loader.visibility = View.VISIBLE
        binding.btnSignIn.visibility = View.INVISIBLE


        val email = binding.etEmail.text
        val password = binding.etPassword.text

        val validateEmail =
            InputValidation.emailValidation(email.toString()) // error text after validation
        val validatePassword =
            InputValidation.passwordValidation(password.toString())  // error text after validation

        if (validateEmail.isNotEmpty()) binding.etEmail.error = validateEmail
        if (validatePassword.isNotEmpty()) binding.etPassword.error = validatePassword


        if (validateEmail.isEmpty() && validatePassword.isEmpty()) {
            HelperMethods.hideKeyboard(this)
            AuthAPI.loginGeneric(email.toString(), password.toString(),
                object : AuthAPI.Companion.AuthLoginCallback {
                    override fun onLoginResult(loginResponse: LoginResponse) {
                        if (loginResponse.success == "success") {
                            val intent = Intent(this@LoginAdmin, AdminDashboard::class.java)
                            startActivity(intent)
                            EliteMedical.updateAdminToken(loginResponse.token)
                        } else {
                            val snackBar =
                                Snackbar.make(
                                    binding.root,
                                    loginResponse.message.toString(),
                                    Snackbar.LENGTH_SHORT
                                )
                            snackBar.setAction("Retry") { snackBar.dismiss() }
                            snackBar.show()
                        }
                        binding.loader.visibility = View.GONE
                        binding.btnSignIn.visibility = View.VISIBLE

                    }
                })
        }
    }
}