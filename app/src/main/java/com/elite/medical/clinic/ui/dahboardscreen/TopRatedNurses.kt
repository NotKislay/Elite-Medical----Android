package com.elite.medical.clinic.ui.dahboardscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.databinding.ActivityTopRatedNursesBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel

class TopRatedNurses : AppCompatActivity() {
    private lateinit var binding:ActivityTopRatedNursesBinding
    private lateinit var clinicDashboardData: ClinicDashboardModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_top_rated_nurses)

        clinicDashboardData = intent.getParcelableExtra<ClinicDashboardModel>("clinicDashboardData")!!


        val adapter = TopRatedNursesAdapter(clinicDashboardData.topNurses)
        binding.listview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.listview.adapter = adapter

        binding.btnBack.setOnClickListener { finish() }


    }
}