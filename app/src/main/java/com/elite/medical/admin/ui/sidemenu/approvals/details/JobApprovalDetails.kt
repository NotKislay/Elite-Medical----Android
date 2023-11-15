package com.elite.medical.admin.ui.sidemenu.approvals.details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.ui.AdminDashboard
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.admin.ui.sidemenu.approvals.JobApprovals
import com.elite.medical.databinding.ActivityJobApprovalDetailsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ButtonAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobapproval.JobDetailsFromJobApprovalModel

class JobApprovalDetails : AppCompatActivity() {

    private lateinit var binding: ActivityJobApprovalDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityJobApprovalDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener { finish() }

        val details =
            intent.getParcelableExtra<JobDetailsFromJobApprovalModel>(
                "details"
            )!!

        val arrayData = arrayOf(
            "Job Title: ${details.title}",
            "Job Type: ${details.type}",
            "Start Date: ${details.engage_from}",
            "End Date: ${details.engage_to}",
            "Job Locations: ${details?.locations?.joinToString(", ")}",
            "Vacancy: ${details.vacancy}",
            "Status: ${details.approvalStatus}",
            "Created On: ${details.created_at}",
            "Description: ${details.description}",
//            "id: ${details.id}",
//            "Clinic Register Id: ${details.clinic_register_id}",
//            "Applied: ${details?.locations?.joinToString(", ")}",
//            "Hired:${details?.locations?.joinToString(", ")}",
//            "Updated At: ${details.updated_at}",
//            "Clinic Name: ${details.clinic_name}",
//            "Clinic Type: ${details.clinic_type}"
        )
        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter

        if (details.approvalStatus == "approved" || details.approvalStatus == "cancelled") {
            binding.btnGroup.visibility = View.GONE
        }
        if (details.approvalStatus == "closed") {
            binding.btnGroup.visibility = View.GONE
        }


        binding.ApproveBtn.setOnClickListener {
            var token = EliteMedical.AuthTokenAdmin
            token = "Bearer $token"
            val id = details.id
            ButtonAPIs.approveJobRequest(
                token, id,
                object : ButtonAPIs.Companion.ButtonsCallback {
                    override fun onSuccess(msg: String) {
                        Toast.makeText(
                            this@JobApprovalDetails,
                            "Job Approved Successfully.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent =
                            Intent(this@JobApprovalDetails, JobApprovals::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }

                    override fun onResponseErr(msg: String, statusCode: String) {
                        Toast.makeText(this@JobApprovalDetails, msg, Toast.LENGTH_SHORT)
                            .show()
                        val intent =
                            Intent(this@JobApprovalDetails, AdminDashboard::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }


                })

        }

        binding.CancelBtn.setOnClickListener {
            var token = EliteMedical.AuthTokenAdmin
            token = "Bearer $token"
            val id = details.id
            ButtonAPIs.cancelJobRequest(
                token!!, id,
                object : ButtonAPIs.Companion.ButtonsCallback {
                    override fun onSuccess(msg: String) {
                        Toast.makeText(
                            this@JobApprovalDetails,
                            "Job Cancelled Successfully",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent =
                            Intent(this@JobApprovalDetails, JobApprovals::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }

                    override fun onResponseErr(msg: String, statusCode: String) {

                        Toast.makeText(this@JobApprovalDetails, msg, Toast.LENGTH_SHORT)
                            .show()
                        val intent =
                            Intent(this@JobApprovalDetails, AdminDashboard::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }


                })
        }

    }
}