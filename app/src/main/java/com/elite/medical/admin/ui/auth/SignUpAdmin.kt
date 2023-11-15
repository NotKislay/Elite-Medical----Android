package com.elite.medical.admin.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elite.medical.MainActivity
import com.elite.medical.databinding.ActivitySignupAdminBinding

class SignUpAdmin : AppCompatActivity() {

    private lateinit var binding: ActivitySignupAdminBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupAdminBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, LoginAdmin::class.java)
            startActivity(intent)
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }




    }
}