package com.elite.medical.admin.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.approvals.ApprovalNurseAdapter
import com.elite.medical.databinding.ActivityRecentNursesBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel

class RecentNurses : AppCompatActivity() {
    private lateinit var binding: ActivityRecentNursesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_nurses)

        binding.btnBack.setOnClickListener { finish() }


        val nurseList = intent.getParcelableArrayListExtra<NurseDetailsFromNurseApprovalModel>("nurse")

        if (nurseList != null) {
            binding.rvRecentNurses.layoutManager =
                GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
            val adapter = ApprovalNurseAdapter(nurseList, this,false,"Recent Nurse")
            binding.rvRecentNurses.adapter = adapter
        }


    }
}