package com.elite.medical.admin.ui.sidemenu.approvals.details.employmentapprovaldetails

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.databinding.ActivityEmploymentApprovalNurseDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel
import com.elite.medical.utils.endpoints.ConstantsAdmin

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

        binding.btnViewLicence.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ConstantsAdmin.URL_FOR_IMAGE + details.nurseLicense)
            )
            startActivity(urlIntent)
        }

        val arrayData = arrayOf(
            details.name.toString(),
            details.mobile.toString(),
            details.email.toString(),
            details.dob.toString(),
            details.address.toString(),
            details.licenseType.toString(),
            details.licenseExpiry.toString(),
            details.experience.toString(),
            details.speciality.toString(),
            details.usImmgStatus.toString(),
            details.nclexStatus.toString(),
            details.cgfnsStatus.toString(),
            details.approvalStatus.toString(),
        )

        displayDetails(arrayData)


    }


    private fun displayDetails(arrayData: Array<String>) {
        binding.tv1.text = arrayData.elementAt(0)
        binding.tv2.text = arrayData.elementAt(1)
        binding.tv3.text = arrayData.elementAt(2)
        binding.tv4.text = arrayData.elementAt(3)
        binding.tv5.text = arrayData.elementAt(4)
        binding.tv6.text = arrayData.elementAt(5)
        binding.tv7.text = arrayData.elementAt(6)
        binding.tv8.text = arrayData.elementAt(7)
        binding.tv9.text = arrayData.elementAt(8)
        binding.tv10.text = arrayData.elementAt(9)
        binding.tv11.text = arrayData.elementAt(10)
        binding.tv12.text = arrayData.elementAt(11)
        binding.tv13.text = arrayData.elementAt(12)
    }

}