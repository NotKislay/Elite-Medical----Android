package com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ApprovedJobApplicantsAdapterL1
import com.elite.medical.databinding.ActivityApprovedJobApplicantsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.AllJobApplicant

class ApprovedJobApplicants : AppCompatActivity() {
    private lateinit var binding: ActivityApprovedJobApplicantsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_approved_job_applicants)

        binding.btnBack.setOnClickListener { finish() }
        binding.listView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        displayApprovedJobApplicants()


    }

    private fun displayApprovedJobApplicants() {
        DashboardAPIs.getJobApplicants(object : DashboardAPIs.Companion.ApprovedJobApplicantsCallback {
            override fun onListReceived(jobApplications: List<AllJobApplicant>) {
                val adapter = ApprovedJobApplicantsAdapterL1(this@ApprovedJobApplicants, jobApplications)
                binding.listView.adapter = adapter
                binding.loader.visibility = View.GONE

            }
        })
    }
}