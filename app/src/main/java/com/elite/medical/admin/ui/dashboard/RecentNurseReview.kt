package com.elite.medical.admin.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.adapters.RecentNurseReviewAdapter
import com.elite.medical.databinding.ActivityRecentNurseReviewBinding
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel

class RecentNurseReview : AppCompatActivity() {
    private lateinit var binding:ActivityRecentNurseReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recent_nurse_review)

        val nurseReviewList = intent.getParcelableArrayListExtra<AdminDashboardModel.NurseReview>("reviews")
        if (nurseReviewList != null) {
            binding.rvRecentNursesReview.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
            val adapter = RecentNurseReviewAdapter(nurseReviewList)
            binding.rvRecentNursesReview.adapter = adapter
        }

        binding.btnBack.setOnClickListener { finish() }


    }
}