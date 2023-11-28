package com.elite.medical.nurse.fragments.appliedjobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.databinding.FragmentAppliedJobDetailsBinding
import com.elite.medical.nurse.viewmodels.jobs.JobsViewModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobDetailsModel


class AppliedJobDetails : Fragment() {
    private lateinit var binding: FragmentAppliedJobDetailsBinding
    private lateinit var viewModelJobs: JobsViewModel

    private lateinit var viewModel: JobsViewModel
    private lateinit var currentJobID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppliedJobDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobsViewModel::class.java]
        viewModelJobs = ViewModelProvider(requireActivity())[JobsViewModel::class.java]

        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        currentJobID = viewModelJobs.currentJobID.value!!
        viewModel.getAppliedJobDetailsByID(currentJobID)

        viewModel.appliedJobDetails.observe(viewLifecycleOwner) {
            displayJobDetails(it!!)
            binding.loader.isVisible = false
        }



        return binding.root
    }

    private fun displayJobDetails(job: AppliedJobDetailsModel.Job) {

        val tv1: TextView = binding.tv1
        val tv2: TextView = binding.tv2
        val ratingBar: RatingBar = binding.rating
        val tv4: TextView = binding.tv4
        val tv5: TextView = binding.tv5
        val tv6: TextView = binding.tv6
        val tv7: TextView = binding.tv7
        val tv8: TextView = binding.tv8
        val tv9: TextView = binding.tv9
        val tv10: TextView = binding.tv10
        val startDate: TextView = binding.tvStartDate
        val endDate: TextView = binding.tvEndDate

        var rating = job.clinicRating
        if (rating.isEmpty()) rating = "0"
        else rating.toFloat()

        tv1.text = job.title
        tv2.text = job.type
        tv9.text = "NA"
        tv10.text = job.clinicName
        tv4.text = job.locations.joinToString(", ")
        tv5.text = job.description
        tv6.text = job.vacancy
        tv7.text = job.applied.size.toString()
        tv8.text = job.nurseStatus
        startDate.text = job.engageFrom
        endDate.text = job.engageTo
        ratingBar.rating = rating.toFloat()


    }

}