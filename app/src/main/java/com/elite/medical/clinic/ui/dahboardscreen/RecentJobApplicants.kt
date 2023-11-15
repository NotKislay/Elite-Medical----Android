package com.elite.medical.clinic.ui.dahboardscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.databinding.ActivityRecentJobApplicantsBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel

class RecentJobApplicants : AppCompatActivity() {
    private lateinit var binding: ActivityRecentJobApplicantsBinding
    private lateinit var clinicDashboardData: ClinicDashboardModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_job_applicants)
        clinicDashboardData =
            intent.getParcelableExtra("clinicDashboardData")!!

        val adapter = RecentJobApplicationAdapter(clinicDashboardData.nurseApplicants)
        binding.listview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.listview.adapter = adapter


        binding.btnBack.setOnClickListener { finish() }

    }
}