package com.elite.medical.admin.ui.dashboard.recents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.approvals.ApprovalClinicsAdapter
import com.elite.medical.databinding.ActivityRecentClinicsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel

class RecentClinics : AppCompatActivity() {
    private lateinit var binding:ActivityRecentClinicsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recent_clinics)

        binding.btnBack.setOnClickListener { finish() }

        val clinicList = intent.getParcelableArrayListExtra<ClinicDetailsFromClinicApprovalModel>("clinics")
        if (clinicList != null) {
            binding.tvRecentClinics.layoutManager =
                GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
            val adapter = ApprovalClinicsAdapter(ArrayList(clinicList), this,false)
            binding.tvRecentClinics.adapter = adapter
        }
    }
}