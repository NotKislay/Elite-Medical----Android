package com.elite.medical.admin.ui.sidemenu.approvals.details.employmentapprovaldetails

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.approvals.EmploymentApprovals
import com.elite.medical.databinding.ActivityEmploymentApprovalJobDetailsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ButtonAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval.EmploymentDetailsFromEmploymentApprovalModel

class EmploymentApprovalJobDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentApprovalJobDetailsBinding

    private lateinit var details: EmploymentDetailsFromEmploymentApprovalModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmploymentApprovalJobDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener { finish() }

        details =
            intent.getParcelableExtra<EmploymentDetailsFromEmploymentApprovalModel>(
                "details"
            )!!

        val arrayData = arrayOf(
            "Job Title: ${details.job.title}",
            "Job Type: ${details.job.type}",
            "Start Date: ${details.job.engageFrom}",
            "End Date: ${details.job.engageTo}",
            "Locations: ${details.job.locations.joinToString(", ")}",
            "Vacancy: ${details.job.vacancy}",
            "Approval Status: ${details.job.approvalStatus}",
            "Created On: ${details.created_at}",
            "Description: ${details.job.description}",
        )
        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter


        if (details.approvalStatus == "hiring_approved" ||
            details.approvalStatus == "rejected" ||
            details.approvalStatus == "trial_approved" ||
            details.approvalStatus == "termination_approved" ||
            details.approvalStatus == "rejection_approved" ||
            details.approvalStatus == "cancelled"
        ) {
            binding.btnGroup.visibility = View.GONE
        }

        binding.moreBtn.setOnClickListener {
            openModalList()
        }

        binding.ApproveBtn.setOnClickListener {
            val id = details.id
            val action = details.request_type
            ButtonAPIs.approveEmploymentRequest(
                id, action, object : ButtonAPIs.Companion.ButtonsCallback {
                    override fun onSuccess(msg: String) {
                        Toast.makeText(
                            this@EmploymentApprovalJobDetails,
                            msg,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent =
                            Intent(
                                this@EmploymentApprovalJobDetails,
                                EmploymentApprovals::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }

                    override fun onResponseErr(msg: String, statusCode: String) {

                        Toast.makeText(
                            this@EmploymentApprovalJobDetails,
                            msg,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()

                    }

                })

        }

        binding.CancelBtn.setOnClickListener {
            var token = EliteMedical.AuthTokenAdmin
            token = "Bearer $token"
            val id = details.id
            ButtonAPIs.cancelEmploymentRequest(
                id, object : ButtonAPIs.Companion.ButtonsCallback {
                    override fun onSuccess(msg: String) {
                        Toast.makeText(
                            this@EmploymentApprovalJobDetails,
                            msg,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        finish()
                        val intent =
                            Intent(
                                this@EmploymentApprovalJobDetails,
                                EmploymentApprovals::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }

                    override fun onResponseErr(msg: String, statusCode: String) {
                        Toast.makeText(
                            this@EmploymentApprovalJobDetails,
                            msg,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        finish()
                    }

                })

        }
    }

    @SuppressLint("MissingInflatedId")
    private fun openModalList() {
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.employment_approval_more_popup_layout, null)

        val clinicBtn = dialogView.findViewById<TextView>(R.id.clinicBtn)
        val nurseBtn = dialogView.findViewById<TextView>(R.id.nurseBtn)
        val employmentBtn = dialogView.findViewById<TextView>(R.id.employmentBtn)
        val cancelBtn = dialogView.findViewById<TextView>(R.id.cancelBtn)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        cancelBtn.setOnClickListener {
            alertDialog.dismiss()
        }
        clinicBtn.setOnClickListener {
            alertDialog.dismiss()
            val intent = Intent(this, EmploymentApprovalClinicDetails::class.java)
            intent.putExtra("details", details.clinic)
            startActivity(intent)
        }
        nurseBtn.setOnClickListener {
            alertDialog.dismiss()
            val intent = Intent(this, EmploymentApprovalNurseDetails::class.java)
            intent.putExtra("details", details.nurse)
            startActivity(intent)
        }
        employmentBtn.setOnClickListener {
            alertDialog.dismiss()
            val intent = Intent(this, EmploymentApprovalEmploymentDetails::class.java)
            intent.putExtra("details", details)
            startActivity(intent)
        }

        alertDialog.show()
    }
}