package com.elite.medical.nurse.fragments.searchjobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.databinding.FragmentSearchJobDetailBinding
import com.elite.medical.nurse.NurseViewModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job


class SearchJobDetail : Fragment() {
    private lateinit var binding: FragmentSearchJobDetailBinding
    private lateinit var viewModel: NurseViewModel
    private lateinit var currentJobID: String
    private lateinit var currentJobDetails: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchJobDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[NurseViewModel::class.java]

        currentJobID = viewModel.currentJobID.value!!
        viewModel.getJobDetailsByID(currentJobID)

        viewModel.jobDetail.observe(viewLifecycleOwner) {
            binding.loader.isVisible = false
            currentJobDetails = it!!
            displayJobDetails()
        }


        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

/*        EliteMedical.nurseAuthToken.observe(viewLifecycleOwner) {
            if (it == null) {
                val intent = Intent(requireContext(), LoginNurse::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }*/

        return binding.root
    }

    private fun displayJobDetails() {

        binding.label1.text = "Title"
        binding.label2.text = "Job Type"
        binding.label3.text = "Clinic Name"
        binding.label4.text = "Location"
        binding.label5.text = "Description"
        binding.label6.text = "Start Date"
        binding.label7.text = "End Date"
        binding.label8.text = "Vacancy"
        binding.label9.text = "Applied"
        binding.label10.text = "Status"

        binding.tv1.text = currentJobDetails.title
        binding.tv2.text = currentJobDetails.type
        binding.tv3.text = currentJobDetails.clinicName
        binding.tv4.text = currentJobDetails.locations.joinToString(",")
        binding.tv5.text = currentJobDetails.description
        binding.tv6.text = currentJobDetails.engageFrom
        binding.tv7.text = currentJobDetails.engageTo
        binding.tv8.text = currentJobDetails.vacancy
        binding.tv9.text = currentJobDetails.applied.size.toString()
        binding.tv10.text = currentJobDetails.status


    }

}