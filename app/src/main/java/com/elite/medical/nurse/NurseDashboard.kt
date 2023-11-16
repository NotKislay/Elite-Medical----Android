package com.elite.medical.nurse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.databinding.ActivityNurseDashboardBinding
import com.elite.medical.nurse.fragments.clinics.ClinicReviewsFragment
import com.elite.medical.nurse.viewmodels.NurseViewModel


class NurseDashboard : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityNurseDashboardBinding
    private lateinit var viewModel: NurseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nurse_dashboard)
        viewModel = ViewModelProvider(this)[NurseViewModel::class.java]


    }


    override fun passData(data: String) {
        val bundle = Bundle()
        bundle.putString("key","data is : $data")
        val reviewsFragment = ClinicReviewsFragment()
        reviewsFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.enrolledclinicdetailsfrmnt,reviewsFragment)
            .addToBackStack(null)
            .commit()
    }


}


