package com.elite.medical.admin.ui.sidemenu.approvals

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.approvals.ApprovalJobAdapter
import com.elite.medical.databinding.ActivityJobsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ApprovalAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobapproval.JobDetailsFromJobApprovalModel

class JobApprovals : AppCompatActivity() {

    private lateinit var binding: ActivityJobsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_jobs)


        recyclerView = binding.rvJobs
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        populateRecyclerView()

        binding.btnBack.setOnClickListener { finish() }

    }

    private fun populateRecyclerView() {
        val token = EliteMedical.AuthTokenAdmin
        ApprovalAPIs.fetchJobApprovalList(token!!,
            object : ApprovalAPIs.Companion.JobApprovalCallback {
                override fun onListReceived(jobs: List<JobDetailsFromJobApprovalModel>) {
                    val adapter =
                        ApprovalJobAdapter(ArrayList(jobs), this@JobApprovals, true)
                    recyclerView.adapter = adapter
                    binding.loader.visibility = View.GONE
                }

                override fun onResponseErr(msg: String, statusCode: String) {

                    Toast.makeText(this@JobApprovals, msg, Toast.LENGTH_SHORT).show()
                    finish()

                }
            })
    }
}