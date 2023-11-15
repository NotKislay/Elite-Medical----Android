package com.elite.medical.admin.ui.sidemenu.approvals.details.jobsearchdetails

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.R
import com.elite.medical.databinding.ActivityEmploymentApprovalClinicDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel

class JobSearchClinicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentApprovalClinicDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmploymentApprovalClinicDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        binding.details.text="Job Search Approval"

        val details =
            intent.getParcelableExtra<ClinicDetailsFromClinicApprovalModel>(
                "details"
            )

        val arrayData = arrayOf(
            "Clinic Name: ${details?.name}",
            "Contact No. : ${details?.mobile}",
            "Email: ${details?.email}",
            "Address: ${details?.address}",
            "Clinic Type: ${details?.clinicType}",
            "VAT/TIN No. : ${details?.vatNo}",
            "CST No: ${details?.cstNo}",
            "Service Tax No: ${details?.serviceTaxNo}",
            "Clinic UIN: ${details?.uinNo}",
            "Declaration: ${details?.declaration}",
            "View Licence: https://staging.emfwebapp.ikshudigital.com/storage/${details?.clinicLicense}",
            "Approval Status: ${details?.approvalStatus}",
//            "City: ${details?.city}",
//            "Created At: ${details?.createdAt}",
//            "ID: ${details?.id}",
//            "Locations: ${details?.locations?.joinToString(", ")}",
//            "State: ${details?.state}",
//            "Updated At: ${details?.updatedAt}",
//            "User ID: ${details?.userId}",
//            "ZIP: ${details?.zip}"
        )

        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter

    }
}