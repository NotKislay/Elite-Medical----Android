package com.elite.medical.admin.ui.sidemenu.approvals.details

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.ui.AdminDashboard
import com.elite.medical.admin.ui.sidemenu.approvals.ApprovalsClinic
import com.elite.medical.databinding.ActivityClinicApprovalDetailsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ButtonAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.utils.GenericDetailsAdapterB
import com.elite.medical.utils.endpoints.ConstantsAdmin

class ClinicApprovalDetails : AppCompatActivity() {

    private lateinit var binding: ActivityClinicApprovalDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityClinicApprovalDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val details = intent.getParcelableExtra<ClinicApprovalModel.ClinicApproval>("details")!!

        val data = listOf(
            details.name,
            details.mobile,
            details.email,
            details.address,
            details.clinicType,
            details.vatNo,
            details.cstNo,
            details.serviceTaxNo,
            details.uinNo,
            details.approvalStatus,
            details.declaration,
        )

        displayDetails(data)


        binding.btnViewLicence.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ConstantsAdmin.URL_FOR_IMAGE + details.clinicLicense)
            )
            startActivity(urlIntent)
        }

        if (details.approvalStatus == "approved" || details.approvalStatus == "cancelled")
            binding.btnGroup.isVisible = false


        binding.ApproveBtn.setOnClickListener {
            val email = details.email
            ButtonAPIs.approveUserRequest(email,
                object : ButtonAPIs.Companion.ButtonsCallback {
                    override fun onSuccess(msg: String) {
                        Toast.makeText(
                            this@ClinicApprovalDetails,
                            "Clinic Approved Successfully.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent =
                            Intent(this@ClinicApprovalDetails, ApprovalsClinic::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }

                    override fun onResponseErr(msg: String, statusCode: String) {

                        Toast.makeText(this@ClinicApprovalDetails, msg, Toast.LENGTH_SHORT)
                            .show()
                        val intent =
                            Intent(this@ClinicApprovalDetails, AdminDashboard::class.java)
                        startActivity(intent)

                    }

                })
        }

        binding.CancelBtn.setOnClickListener {
            var token = EliteMedical.AuthTokenAdmin
            token = "Bearer $token"
            val id = details.id
            ButtonAPIs.cancelClinicRequest(
                id,
                object : ButtonAPIs.Companion.ButtonsCallback {
                    override fun onSuccess(msg: String) {
                        Toast.makeText(
                            this@ClinicApprovalDetails,
                            "Clinic Cancelled Successfully",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        val intent =
                            Intent(this@ClinicApprovalDetails, ApprovalsClinic::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }

                    override fun onResponseErr(msg: String, statusCode: String) {

                        Toast.makeText(this@ClinicApprovalDetails, msg, Toast.LENGTH_SHORT)
                            .show()
                        val intent =
                            Intent(this@ClinicApprovalDetails, AdminDashboard::class.java)
                        startActivity(intent)
                    }


                })

        }

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun displayDetails(data: List<String>) {

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
