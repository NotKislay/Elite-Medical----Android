package com.elite.medical.nurse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.databinding.ActivityNurseDashboardBinding
import com.elite.medical.nurse.fragments.clinics.ClinicReviewsFragment


class NurseDashboard : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityNurseDashboardBinding

    private lateinit var viewModel: NurseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nurse_dashboard)
        viewModel = ViewModelProvider(this)[NurseViewModel::class.java]


    }

    //way to pass data from fragments to another fragments
    override fun passData(data: String) {
        var bundle = Bundle()
        bundle.putString("key","data is : $data")
        val fragmentreviews = ClinicReviewsFragment()
        fragmentreviews.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.enrolledclinicdetailsfrmnt,fragmentreviews)
            .addToBackStack(null)
            .commit()
    }


}


