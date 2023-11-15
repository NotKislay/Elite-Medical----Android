package com.elite.medical.clinic.ui.dahboardscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.databinding.ActivityTopRatedNursesBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel

class TopRatedNurses : AppCompatActivity() {
    private lateinit var binding: ActivityTopRatedNursesBinding
    private lateinit var clinicDashboardData: ClinicDashboardModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_rated_nurses)

        clinicDashboardData = intent.getParcelableExtra("clinicDashboardData")!!


        if (clinicDashboardData.topNurses.isEmpty()) {
            binding.tvNoDataFound.isVisible = true
        } else {

            val adapter = TopRatedNursesAdapter(clinicDashboardData.topNurses)
            binding.listview.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.listview.adapter = adapter
            binding.tvNoDataFound.isVisible = false


        }




        binding.btnBack.setOnClickListener { finish() }


    }
}