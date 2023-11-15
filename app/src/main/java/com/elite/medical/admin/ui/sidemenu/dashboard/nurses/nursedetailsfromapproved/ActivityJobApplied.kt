package com.elite.medical.admin.ui.sidemenu.dashboard.nurses.nursedetailsfromapproved

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.JobAppliedAdapter
import com.elite.medical.databinding.ActivityNurseJobAppliedBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.Job

class ActivityJobApplied : AppCompatActivity() {
    private lateinit var binding: ActivityNurseJobAppliedBinding
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nurse_job_applied)

        recyclerView = binding.rvJobApplied
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)


        val appliedJobs =
            intent.getParcelableArrayListExtra<Job>(
                "appliedJobs"
            )


        if (appliedJobs.isNullOrEmpty()) {
            binding.noData.isVisible = true
        } else {
            val adapter =
                JobAppliedAdapter(appliedJobs, this@ActivityJobApplied, true)
            recyclerView.adapter = adapter
        }
        binding.loader.visibility = View.GONE

        binding.btnBack.setOnClickListener { finish() }
    }
}