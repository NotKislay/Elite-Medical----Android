package com.elite.medical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.elite.medical.databinding.ActivityForgotPasswordBinding
import com.elite.medical.utils.InputValidation

class ForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)

        binding.btnBack.setOnClickListener { finish() }

        binding.btnSubmit.setOnClickListener {
            submit(binding.etEmail.text.toString())
        }


    }

    private fun submit(email: String) {

        val str = InputValidation.emailValidation(email)
        if (str == "") {
            Toast.makeText(
                this,
                "please check your email, create a new password.",
                Toast.LENGTH_SHORT
            ).show()
        } else
            binding.etEmail.error = str

    }


}