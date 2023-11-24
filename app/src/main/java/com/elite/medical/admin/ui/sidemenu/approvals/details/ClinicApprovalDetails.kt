package com.elite.medical.admin.ui.sidemenu.approvals.details

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.ui.AdminDashboard
import com.elite.medical.admin.ui.sidemenu.approvals.ApprovalsClinic
import com.elite.medical.databinding.ActivityClinicApprovalDetailsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ButtonAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.utils.Constants
import com.elite.medical.utils.ConstantsNurse

class ClinicApprovalDetails : AppCompatActivity() {

    private lateinit var binding: ActivityClinicApprovalDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityClinicApprovalDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener { finish() }

        val details = intent.getParcelableExtra<ClinicDetailsFromClinicApprovalModel>(
            "details"
        )

        details!!

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
            "Approval Status: ${details.approvalStatus}",
            "Declaration: ${details.declaration}",
        )

        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayData)
        binding.listview.adapter = adapter

        binding.btnViewLicence.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(Constants.URL_FOR_IMAGE + details.clinicLicense)
            )
            startActivity(urlIntent)

        }

        if (details.approvalStatus == "approved" || details.approvalStatus == "cancelled") {
            binding.btnGroup.visibility = View.GONE
        }

        binding.ApproveBtn.setOnClickListener {
            var token = EliteMedical.AuthTokenAdmin
            token = "Bearer $token"
            val email = details.email
            if (email != null) {
                ButtonAPIs.approveUserRequest(token,
                    email,
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
        }

        binding.CancelBtn.setOnClickListener {
            var token = EliteMedical.AuthTokenAdmin
            token = "Bearer $token"
            val id = details.id
            if (id != null) {
                ButtonAPIs.cancelClinicRequest(
                    token!!,
                    id,
                    object : ButtonAPIs.Companion.ButtonsCallback {
                        override fun onSuccess(msg: String) {
                            Toast.makeText(this@ClinicApprovalDetails, "Clinic Cancelled Successfully", Toast.LENGTH_SHORT)
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

        }
    }
}
