package com.elite.medical.admin.ui.sidemenu.dashboard.nurses.nursedetailsfromapproved

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ClinicDetailsAdapterFromClinicDetails
import com.elite.medical.admin.adapters.sidemenu.dashboard.JobAppliedAdapter
import com.elite.medical.databinding.ActivityNurseJobAppliedBinding
import com.elite.medical.databinding.ActivityReviewsByClinicBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.Job
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.ClinicReviewsFromClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.EmpDetailsInNurseById
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ReviewDetails

class ActivityReviewsByClinic : AppCompatActivity() {

    private lateinit var binding: ActivityReviewsByClinicBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_reviews_by_clinic)
        binding.btnBack.setOnClickListener { finish() }

        recyclerView = binding.rvNurseRevByClinic
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)


        val nurserevbyclinic =
            intent.getParcelableArrayListExtra<ClinicReviewsFromClinicDetailsModel>(
                "reviews"
            )
        val empdetails =
            intent.getParcelableArrayListExtra<EmpDetailsInNurseById>(
                "empdetails"
            )!!

        if(nurserevbyclinic.isNullOrEmpty()){
            binding.noRevsByClinic.visibility=View.VISIBLE
        }
        else{
            val clinicname= empdetails[0].clinicName
            val adapter =
                ClinicDetailsAdapterFromClinicDetails(ArrayList(nurserevbyclinic), this@ActivityReviewsByClinic,clinicname)
            recyclerView.adapter = adapter
        }
        binding.loader.visibility = View.GONE

        binding.btnBack.setOnClickListener { finish() }


    }
}