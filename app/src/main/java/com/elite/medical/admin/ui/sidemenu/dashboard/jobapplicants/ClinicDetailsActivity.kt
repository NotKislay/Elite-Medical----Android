package com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityClinicDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid.Clinic

class ClinicDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClinicDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_clinic_details)

        binding.btnBack.setOnClickListener { finish() }

        val data = intent.getParcelableExtra<Clinic>("data")!!

        val jobDetailsArray = arrayOf(
            "Clinic Name: ${data.name}",
            "Contact No: ${data.mobile}",
            "Email: ${data.email}",
            "Address: ${data.address}",
            "Clinic Type: ${data.clinicType}",
            "VAT/TIN No: ${data.vatNo}",
            "CST No: ${data.cstNo}",
            "Service Tax No: ${data.serviceTaxNo}",
            "Clinic UIN: ${data.uinNo}",
            "Declaration: ${data.declaration}",
            "Approval Status: ${data.approvalStatus}",
            "View Licence: https://staging.emfwebapp.ikshudigital.com/storage/${data.clinicLicense}",
        )
        val adapter =
            ArrayAdapter(this, R.layout.custom_single_item_textview, jobDetailsArray)
        binding.listView.adapter = adapter
        binding.appbarTitle.text = "Clinic Details"

    }
}