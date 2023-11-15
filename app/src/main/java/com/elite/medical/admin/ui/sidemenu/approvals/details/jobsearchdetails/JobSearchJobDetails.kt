package com.elite.medical.admin.ui.sidemenu.approvals.details.jobsearchdetails

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.approvals.details.employmentapprovaldetails.EmploymentApprovalNurseDetails
import com.elite.medical.databinding.ActivityEmploymentApprovalJobDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.NurseDetailJobSearch

class JobSearchJobDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentApprovalJobDetailsBinding


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmploymentApprovalJobDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener { finish() }

        binding.btnGroup.visibility=View.GONE
        binding.details.text="Job Search Approval"

        val details =
            intent.getParcelableExtra<NurseDetailJobSearch>(
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
            "Created On: ${details.createdAt}",
            "Description: ${details.job.description}",
        )
        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter

        binding.moreBtn.setOnClickListener{
            openModalList(details)
        }

    }
    @SuppressLint("MissingInflatedId")
    private fun openModalList(details: NurseDetailJobSearch) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.employment_approval_more_popup_layout, null)

        val clinicBtn=dialogView.findViewById<TextView>(R.id.clinicBtn)
        val nurseBtn=dialogView.findViewById<TextView>(R.id.nurseBtn)
        val employmentBtn=dialogView.findViewById<TextView>(R.id.employmentBtn)
        val cancelBtn=dialogView.findViewById<TextView>(R.id.cancelBtn)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        cancelBtn.setOnClickListener{
            alertDialog.dismiss()
        }
        clinicBtn.setOnClickListener{
            alertDialog.dismiss()
            val intent = Intent(this, JobSearchClinicDetails::class.java)
            intent.putExtra("details",details.clinic)
            startActivity(intent)
        }
        nurseBtn.setOnClickListener{
            alertDialog.dismiss()
            val intent = Intent(this, JobSearchNurseDetails::class.java)
            intent.putExtra("details",details.nurse)
            startActivity(intent)
        }
        employmentBtn.setOnClickListener{
            alertDialog.dismiss()
            val intent = Intent(this, JobSearchEmploymentDetails::class.java)
            intent.putExtra("details",details.employment)
            startActivity(intent)
        }
        alertDialog.show()
    }
}