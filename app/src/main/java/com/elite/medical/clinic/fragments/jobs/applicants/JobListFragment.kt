package com.elite.medical.clinic.fragments.jobs.applicants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobApplicantsViewModel
import com.elite.medical.databinding.FragmentJobNApplicantsBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.ClinicJobApplicantsModel

class JobListFragment : Fragment() {
    private lateinit var binding: FragmentJobNApplicantsBinding
    private lateinit var viewModel: JobApplicantsViewModel

    private lateinit var adapter: JobListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobNApplicantsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobApplicantsViewModel::class.java]


        viewModel.getJobsList()


        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

    override fun onResume() {
        super.onResume()


        viewModel.jobApplicantsList.observe(viewLifecycleOwner) { it ->
            binding.loader.visibility = View.GONE
            adapter = JobListAdapter(it!!, viewModel)
            binding.listview.adapter = adapter

            adapter.onItemClickListener = { details ->
                onItemClicked(details)
            }

        }


    }

    private fun onItemClicked(details: ClinicJobApplicantsModel.NurseApplicant) {
        viewModel.currentJobID.postValue(details.jobId)
        viewModel.isCurrentJobClosed.postValue(details.jobStatus == "closed")
        viewModel.applicantsList.postValue(details.nurses)
        findNavController()
            .navigate(R.id.action_jobNApplicantsFragment_to_listJobApplicantsFragment)
    }


}