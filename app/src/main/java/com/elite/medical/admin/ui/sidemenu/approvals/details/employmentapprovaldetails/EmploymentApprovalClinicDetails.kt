package com.elite.medical.admin.ui.sidemenu.approvals.details.employmentapprovaldetails

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.R
import com.elite.medical.databinding.ActivityEmploymentApprovalClinicDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval.EmploymentDetailsFromEmploymentApprovalModel
import com.elite.medical.utils.endpoints.ConstantsAdmin

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

        binding.btnViewLicence.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ConstantsAdmin.URL_FOR_IMAGE + details.clinicLicense)
            )
            startActivity(urlIntent)
        }

        val arrayData = arrayOf(
            details.name.toString(),
            details.mobile.toString(),
            details.email.toString(),
            details.address.toString(),
            details.clinicType.toString(),
            details.vatNo.toString(),
            details.cstNo.toString(),
            details.serviceTaxNo.toString(),
            details.uinNo.toString(),
            details.approvalStatus.toString(),
            details.declaration.toString(),
        )

        displayDetails(arrayData)

    }

    private fun displayDetails(data: Array<String>) {

        binding.tv1.text = data.elementAt(0)
        binding.tv2.text = data.elementAt(1)
        binding.tv3.text = data.elementAt(2)
        binding.tv4.text = data.elementAt(3)
        binding.tv5.text = data.elementAt(4)
        binding.tv6.text = data.elementAt(5)
        binding.tv7.text = data.elementAt(6)
        binding.tv8.text = data.elementAt(7)
        binding.tv9.text = data.elementAt(8)
        binding.tv10.text = data.elementAt(9)
        binding.tv11.text = data.elementAt(10)

    }

}
