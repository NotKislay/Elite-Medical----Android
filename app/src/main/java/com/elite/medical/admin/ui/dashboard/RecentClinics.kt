package com.elite.medical.admin.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.admin.adapters.RecentClinicsAdapter
import com.elite.medical.admin.adapters.sidemenu.approvals.ApprovalClinicsAdapter
import com.elite.medical.admin.viewmodels.AdminViewModel
import com.elite.medical.databinding.ActivityRecentClinicsBinding
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel

class RecentClinics : AppCompatActivity() {
    private lateinit var binding: ActivityRecentClinicsBinding
    private lateinit var viewModel: AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_clinics)
        viewModel = ViewModelProvider(this)[AdminViewModel::class.java]


        binding.btnBack.setOnClickListener { finish() }

        val data = intent.getParcelableExtra<AdminDashboardModel>("clinics")

        if (data != null) {
            val listClinic = data.clinics.take(5)
            val adapter = RecentClinicsAdapter(listClinic, this, "Recent Clinics")
            binding.tvRecentClinics.adapter = adapter
        }
    }
}