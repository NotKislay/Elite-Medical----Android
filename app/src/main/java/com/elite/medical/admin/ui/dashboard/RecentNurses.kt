package com.elite.medical.admin.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.admin.adapters.RecentNursesAdapter
import com.elite.medical.databinding.ActivityRecentNursesBinding
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel

class RecentNurses : AppCompatActivity() {
    private lateinit var binding: ActivityRecentNursesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_nurses)

        binding.btnBack.setOnClickListener { finish() }


        val data = intent.getParcelableExtra<AdminDashboardModel>("nurse")


        if (data != null) {
            val listNurses = data.nurses.take(5)
            val adapter = RecentNursesAdapter(listNurses, this, false, "Recent Nurse")
            binding.rvRecentNurses.adapter = adapter
        }


    }
}