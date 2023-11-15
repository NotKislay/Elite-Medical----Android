package com.elite.medical.admin.ui.sidemenu.dashboard.jobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ApprovedJobsAdapter
import com.elite.medical.databinding.ActivityApprovedJobsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.Job

class ApprovedJobs : AppCompatActivity() {
    private lateinit var binding:ActivityApprovedJobsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_approved_jobs)

        binding.btnBack.setOnClickListener { finish() }
        binding.listApprovedJobs.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        fetchApprovedJobList()
    }

    private fun fetchApprovedJobList() {
        DashboardAPIs.getJobs(object : DashboardAPIs.Companion.ApprovedJobsCallback {
            override fun onListReceived(jobs: List<Job>) {
                val adapter = ApprovedJobsAdapter(this@ApprovedJobs,ArrayList(jobs))
                binding.listApprovedJobs.adapter = adapter
                binding.loader.visibility = View.GONE
            }
        })
    }
}