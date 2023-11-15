package com.elite.medical.admin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.databinding.ActivityViewProfileBinding
import com.elite.medical.retrofit.apis.admin.DDAdminAPI
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ProfileDetailsModel

class ActivityViewProfile : AppCompatActivity() {

    private lateinit var binding: ActivityViewProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_profile)
        binding.btnBack.setOnClickListener { finish() }


        //disabling items
        binding.btnUpdateProfile.visibility = View.GONE
        binding.labelEmail.visibility = View.GONE
        binding.labelName.visibility = View.GONE
        binding.labelProfInfo.visibility = View.GONE
        binding.etProfileName.visibility = View.GONE
        binding.etProfileEmail.visibility = View.GONE

        fetchAndSetProfileData()
    }

    private fun fetchAndSetProfileData() {
        DDAdminAPI.getProfileDetails(
            object : DDAdminAPI.Companion.ProfileInfoCallback {
                override fun onInfoReceived(data: ProfileDetailsModel?, statusCode: Int?) {

                    if (statusCode == 200) {

                        //enabling if success
                        binding.btnUpdateProfile.visibility = View.VISIBLE
                        binding.labelEmail.visibility = View.VISIBLE
                        binding.labelName.visibility = View.VISIBLE
                        binding.labelProfInfo.visibility = View.VISIBLE
                        binding.etProfileName.visibility = View.VISIBLE
                        binding.etProfileEmail.visibility = View.VISIBLE


                        val userName = binding.etProfileName
                        val userEmail = binding.etProfileEmail
                        userName.setText(data?.user?.name)
                        userEmail.setText(data?.user?.email)
                        binding.loader.visibility = View.GONE


                        userEmail.doOnTextChanged { text, start, before, count ->

                            if (text.isNullOrBlank()) {

                                userEmail.error = "This can't be blank"
                                binding.btnUpdateProfile.isEnabled = false
                            }

                        }
                        userName.doOnTextChanged { text, start, before, count ->

                            if (text.isNullOrBlank()) {

                                userName.error = "This can't be blank"
                                binding.btnUpdateProfile.isEnabled = false
                            }

                        }

                    }
                }
            })
        binding.btnUpdateProfile.setOnClickListener {
            val updatedname = binding.etProfileName.text.toString()
            val updatedemail = binding.etProfileEmail.text.toString()
            postUpdatedCredentialsToAPI(updatedname, updatedemail)
        }
    }

    private fun postUpdatedCredentialsToAPI(name: String, email: String) {
        DDAdminAPI.postUpdatedUserDetails(
            name,
            email,
            object : DDAdminAPI.Companion.ProfileUpdateCallback {
                override fun onSuccess(msg: String?, statusCode: Int?) {

                    if (statusCode == 200) {
                        Toast.makeText(this@ActivityViewProfile, "$msg", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                        /*                        val intent =
                                                    Intent(this@ActivityViewProfile, AdminDashboard::class.java)
                                                startActivity(intent)*/
                    } else {
                        Toast.makeText(
                            this@ActivityViewProfile, "$msg", Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            })
    }
}