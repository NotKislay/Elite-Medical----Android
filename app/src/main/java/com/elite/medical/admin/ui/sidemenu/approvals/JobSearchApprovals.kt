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
import com.elite.medical.admin.adapters.sidemenu.approvals.ApprovalJobSearchAdapter
import com.elite.medical.databinding.ActivityEmploymentsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ApprovalAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.NurseDetailJobSearch

class JobSearchApprovals : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employments)

        binding.heading.text = "Job Search Approval"


        recyclerView = binding.rvEmployments
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        populateRecyclerView()

        binding.btnBack.setOnClickListener { finish() }

    }

    private fun populateRecyclerView() {
        val token = EliteMedical.AuthTokenAdmin
        ApprovalAPIs.fetchJobSearchApprovalList(object : ApprovalAPIs.Companion.JobSearchApprovalCallback {
            override fun onListReceived(jobs: List<NurseDetailJobSearch>) {
                val adapter =
                    ApprovalJobSearchAdapter(
                        ArrayList(jobs),
                        this@JobSearchApprovals,
                        true
                    )
                recyclerView.adapter = adapter
                binding.loader.visibility = View.GONE
            }

            override fun onResponseErr(msg: String, statusCode: String) {
                Toast.makeText(this@JobSearchApprovals, msg, Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        })
    }
}