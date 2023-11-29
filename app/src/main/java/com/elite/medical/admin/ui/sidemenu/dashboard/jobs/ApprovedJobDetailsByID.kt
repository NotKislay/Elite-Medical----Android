package com.elite.medical.admin.ui.sidemenu.dashboard.jobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityApprovedJobDetailsByIdBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.JobX

class ApprovedJobDetailsByID : AppCompatActivity() {
    private lateinit var binding: ActivityApprovedJobDetailsByIdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_approved_job_details_by_id)

        binding.btnBack.setOnClickListener { finish() }
        val id = intent.getIntExtra("jobID", -1)
        val userID = intent.getStringExtra("userID")

        displayJobDetailsByID(id, userID)


    }

    private fun displayJobDetailsByID(id: Int, userID: String?) {
        DashboardAPIs.getJobDetailsByID(id.toString(),
            object : DashboardAPIs.Companion.JobDetailsByIDCallback {
                override fun onDetailsReceived(jobDetails: JobX) {

                    var status = ""

                    status = if (jobDetails.hired.contains(userID.toString())) "Hired"
                    else {
                        if (jobDetails.applied.contains(userID.toString())) "On Processing"
                        else "Not Applied"
                    }

                    binding.jobTitle.text = jobDetails.title
                    binding.clinicName.text = jobDetails.clinicRegister.name

                    binding.tvJobType.text = jobDetails.type
                    binding.tvClinicName.text = jobDetails.clinicRegister.name
                    binding.tvClinicAddress.text = jobDetails.clinicRegister.address
                    binding.tvDescription.text = jobDetails.description
                    binding.tvAvailableFrom.text = jobDetails.engageFrom
                    binding.tvAvailableTo.text = jobDetails.engageTo
                    binding.tvLocation.text = jobDetails.locations.joinToString(",")
                    binding.tvVacancy.text = jobDetails.vacancy

                    val applied = jobDetails.applied.size
                    if (applied == 0) binding.tvApplied.text = "N/A"
                    else binding.tvApplied.text = applied.toString()

                    val hired = jobDetails.hired.size
                    if (hired == 0) binding.tvHired.text = "N/A"
                    else binding.tvHired.text = hired.toString()


                    binding.tvApprovalStatus.text = status

                    binding.llContent.visibility = View.VISIBLE
                    binding.loader.visibility = View.GONE

                }
            })
    }


}