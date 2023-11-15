package com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ApprovedJobApplicantsAdapterL2
import com.elite.medical.databinding.ActivityJobApplicantsNurseListBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.AllJobApplicant

class AllJobApplicants : AppCompatActivity() {
    private lateinit var binding: ActivityJobApplicantsNurseListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_job_applicants_nurse_list)

        binding.btnBack.setOnClickListener { finish() }
        val jobAndNurseDetails = intent.getParcelableExtra<AllJobApplicant>("jobAndNurseDetails")

        val adapter = ApprovedJobApplicantsAdapterL2(this,jobAndNurseDetails)
        binding.listView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.listView.adapter = adapter


    }
}