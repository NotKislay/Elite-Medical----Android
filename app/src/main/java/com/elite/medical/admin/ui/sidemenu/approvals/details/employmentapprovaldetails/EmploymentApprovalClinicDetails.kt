package com.elite.medical.admin.ui.sidemenu.approvals.details.employmentapprovaldetails

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.R
import com.elite.medical.databinding.ActivityEmploymentApprovalClinicDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval.EmploymentDetailsFromEmploymentApprovalModel

class EmploymentApprovalClinicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentApprovalClinicDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmploymentApprovalClinicDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener { finish() }

        val details =
            intent.getParcelableExtra<ClinicDetailsFromClinicApprovalModel>(
                "details"
            )!!

        val arrayData = arrayOf(
            "Clinic Name: ${details.name}",
            "Contact No. : ${details.mobile}",
            "Email: ${details.email}",
            "Address: ${details.address}",
            "Clinic Type: ${details.clinicType}",
            "VAT/TIN No. : ${details.vatNo}",
            "CST No: ${details.cstNo}",
            "Service Tax No: ${details.serviceTaxNo}",
            "Clinic UIN: ${details.uinNo}",
            "View Licence: https://staging.emfwebapp.ikshudigital.com/storage/${details.clinicLicense}",
            "Approval Status: ${details.approvalStatus}",
            "Declaration: ${details.declaration}",
        )

        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter

    }
}
