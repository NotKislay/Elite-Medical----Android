package com.elite.medical.admin.ui.sidemenu.dashboard.nurses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ApprovedNursesAdapter
import com.elite.medical.databinding.ActivityApprovedNursesBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.Nurse

class ApprovedNurses : AppCompatActivity() {
    private lateinit var binding: ActivityApprovedNursesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_approved_nurses)

        binding.listApprovedNurses.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.btnBack.setOnClickListener { finish() }

        fetchApprovedNursesList()
    }

    private fun fetchApprovedNursesList() {
        EliteMedical.AuthTokenAdmin
        DashboardAPIs.getNurses(object : DashboardAPIs.Companion.ApprovedNursesCallback {
            override fun onListReceived(nurses: List<Nurse>) {
                val adapter = ApprovedNursesAdapter(ArrayList(nurses))

                binding.listApprovedNurses.adapter = adapter
                binding.loader.visibility = View.GONE
            }
        })
    }
}