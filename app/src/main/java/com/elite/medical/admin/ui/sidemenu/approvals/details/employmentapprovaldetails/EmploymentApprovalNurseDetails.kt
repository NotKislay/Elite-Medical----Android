package com.elite.medical.admin.ui.sidemenu.approvals.details.employmentapprovaldetails

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.R
import com.elite.medical.databinding.ActivityEmploymentApprovalNurseDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel

class EmploymentApprovalNurseDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentApprovalNurseDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmploymentApprovalNurseDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        val details =
            intent.getParcelableExtra<NurseDetailsFromNurseApprovalModel>(
                "details"
            )!!

        val arrayData = arrayOf(
            "Nurse Name: ${details.name}",
            "Contact No. : ${details.mobile}",
            "Email: ${details.email}",
            "Date of Birth: ${details.dob}",
            "Address: ${details.address}",
            "License Type: ${details.licenseType}",
            "License Expiry: ${details.licenseExpiry}",
            "Experience: ${details.experience}",
            "Speciality: ${details.speciality}",
            "US Immigration Status: ${details.usImmgStatus}",
            "NCLEX Status: ${details.nclexStatus}",
            "CGFNS Status: ${details.cgfnsStatus}",
            "View Licence: https://staging.emfwebapp.ikshudigital.com/storage/${details.nurseLicense}",
            "Approval Status: ${details.approvalStatus}",
//            "Created At: ${details.createdAt}",
        )

        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter
    }
}