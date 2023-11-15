package com.elite.medical.clinic.ui.sidemenu.jobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.ActivityMyJobsBinding

class MyJobs : AppCompatActivity() {
    private lateinit var binding: ActivityMyJobsBinding
    private lateinit var viewModel: MyJobsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_jobs)
        viewModel = ViewModelProvider(this)[MyJobsViewModel::class.java]


        viewModel.loadJobList()








    }
}