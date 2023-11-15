package com.elite.medical.admin.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.adapters.RecentClinicReviewAdapter
import com.elite.medical.databinding.ActivityRecentClinicReviewBinding
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ClinicReview

class RecentClinicReview : AppCompatActivity() {
    private lateinit var binding : ActivityRecentClinicReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recent_clinic_review)

        val clinicReviewList = intent.getParcelableArrayListExtra<ClinicReview>("reviews")
        if (clinicReviewList != null) {
            binding.rvRecentClinicReview.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
            val adapter = RecentClinicReviewAdapter(clinicReviewList)
            binding.rvRecentClinicReview.adapter = adapter
        }

        binding.btnBack.setOnClickListener { finish() }


    }
}