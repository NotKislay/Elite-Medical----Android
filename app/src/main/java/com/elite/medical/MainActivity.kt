package com.elite.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.clinic.auth.LoginClinic
import com.elite.medical.databinding.ActivityMainBinding
import com.elite.medical.nurse.LoginNurse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (intent.getStringExtra("token").equals("null")) {
            Toast.makeText(this, "session is expired, please login again.", Toast.LENGTH_SHORT)
                .show()
        }





        binding.admin.setOnClickListener {
            val intent = Intent(this, LoginAdmin::class.java)
            startActivity(intent)
        }

        binding.clinic.setOnClickListener {
            val intent = Intent(this, LoginClinic::class.java)
            startActivity(intent)
        }

        binding.nurse.setOnClickListener {
            val intent = Intent(this, LoginNurse::class.java)
            startActivity(intent)
        }


    }
}