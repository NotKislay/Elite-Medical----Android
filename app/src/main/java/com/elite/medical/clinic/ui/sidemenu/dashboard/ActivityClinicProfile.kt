package com.elite.medical.clinic.ui.sidemenu.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.ui.AdminDashboard
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.clinic.auth.LoginClinic
import com.elite.medical.clinic.ui.dahboardscreen.ClinicDashboard
import com.elite.medical.databinding.ActivityViewProfileBinding
import com.elite.medical.retrofit.apis.admin.DDAdminAPI
import com.elite.medical.retrofit.apis.clinic.DDClinicAPI
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.ClinicProfileDetailsModel

class ActivityClinicProfile : AppCompatActivity() {

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
        DDClinicAPI.getProfileDetails(
            object : DDClinicAPI.Companion.ProfileInfoCallback {
                override fun onInfoReceived(data: ClinicProfileDetailsModel?, statusCode: Int?) {

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
                            }
                            binding.btnUpdateProfile.isEnabled = !text.isNullOrBlank()


                        }
                        userName.doOnTextChanged { text, start, before, count ->

                            if (text.isNullOrBlank()) {
                                userName.error = "This can't be blank"
                            }
                            binding.btnUpdateProfile.isEnabled = !text.isNullOrBlank()

                        }


                    }
                }
            })
        binding.btnUpdateProfile.setOnClickListener {
            val updatedname = binding.etProfileName.text.toString()
            val updatedemail = binding.etProfileEmail.text.toString()
            //todo uncomment if want to implement the credentials updating part
            postUpdatedCredentialsToAPI(updatedname, updatedemail)
        }
    }

    private fun postUpdatedCredentialsToAPI(name: String, email: String) {
        DDClinicAPI.postUpdatedUserDetails(
            name,
            email,
            object : DDClinicAPI.Companion.ProfileUpdateCallback {
                override fun onSuccess(msg: String?, statusCode: Int?) {

                    if (statusCode == 200) {
                        Toast.makeText(this@ActivityClinicProfile, "$msg", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                        val intent =
                            Intent(this@ActivityClinicProfile, ClinicDashboard::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@ActivityClinicProfile, "$msg", Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            })
    }
}