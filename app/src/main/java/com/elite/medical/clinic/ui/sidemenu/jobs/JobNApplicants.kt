package com.elite.medical.clinic.ui.sidemenu.jobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobNApplicantsViewModel
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.ActivityJobNapplicantsBinding
import com.elite.medical.databinding.FragmentNurseDetailsBinding
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobNApplicantsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobNApplicants : AppCompatActivity() {

    private lateinit var binding: ActivityJobNapplicantsBinding
    private lateinit var viewModel: JobNApplicantsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_napplicants)
        viewModel = ViewModelProvider(this)[JobNApplicantsViewModel::class.java]

        viewModel.getJobsList()


    }


}