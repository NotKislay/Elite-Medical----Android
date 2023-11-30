package com.elite.medical.admin.ui.sidemenu.dashboard.clinics

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ApprovedClinicAdapter
import com.elite.medical.databinding.ActivityApprovedClinicsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.ClinicDetailsFromApprovedClinicsModel

class ApprovedClinics : AppCompatActivity() {

    private lateinit var binding:ActivityApprovedClinicsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_approved_clinics)

        binding.btnBack.setOnClickListener { finish() }
        recyclerView = binding.rvClinics
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        populateClinics()

    }

    private fun populateClinics() {
        DashboardAPIs.getClinics(object : DashboardAPIs.Companion.ApprovedClinicsCallback {
            override fun onListReceived(clinics: List<ClinicDetailsFromApprovedClinicsModel>) {
                val adapter =
                    ApprovedClinicAdapter(ArrayList(clinics), this@ApprovedClinics)
                recyclerView.adapter = adapter
                binding.loader.visibility = View.GONE
            }
        })
    }
}