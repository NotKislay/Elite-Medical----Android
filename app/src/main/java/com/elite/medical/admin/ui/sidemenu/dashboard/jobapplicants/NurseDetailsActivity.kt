package com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityNurseDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid.Nurse

class NurseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNurseDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nurse_details)

        binding.btnBack.setOnClickListener { finish() }

        val data = intent.getParcelableExtra<Nurse>("data")!!

        val arrayData = arrayOf(
            "Name: ${data.name}",
            "Email: ${data.email}",
            "Address: ${data.address}",
            "Contact No: ${data.mobile}",
            "D.O.B: ${data.dob}",
            "License Type: ${data.licenseType}",
            "License Expiry: ${data.licenseExpiry}",
            "Experience: ${data.experience}",
            "Speciality: ${data.speciality}",
            "US Immigration Status: ${data.usImmgStatus}",
            "NCLEX Status: ${data.nclexStatus}",
            "CGFNS Status: ${data.cgfnsStatus}",
            "Approval Status: ${data.approvalStatus}",
            "View Licence: https://staging.emfwebapp.ikshudigital.com/storage/${data.nurseLicense}",
        )
        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter
        binding.appbarTitle.text = "Nurse Details"

    }
}