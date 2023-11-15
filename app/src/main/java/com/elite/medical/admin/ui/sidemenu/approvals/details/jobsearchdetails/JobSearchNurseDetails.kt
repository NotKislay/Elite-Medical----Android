package com.elite.medical.admin.ui.sidemenu.approvals.details.jobsearchdetails

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.R
import com.elite.medical.databinding.ActivityEmploymentApprovalNurseDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel

class JobSearchNurseDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentApprovalNurseDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmploymentApprovalNurseDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }
        binding.details.text="Job Search Approval"

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
//            "City: ${details.city}",
//            "Created At: ${details.createdAt}",
//            "ID: ${details.id}",
//            "License Issue: ${details.licenseIssue}",
//            "Schedule: ${details.schedule}",
//            "Schedule Status: ${details.scheduleStatus}",
//            "Schedule Time: ${details.scheduleTime}",
//            "State: ${details.state}",
//            "Updated At: ${details.updatedAt}",
//            "User ID: ${details.userId}",
//            "ZIP: ${details.zip}"
        )

        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter
    }
}