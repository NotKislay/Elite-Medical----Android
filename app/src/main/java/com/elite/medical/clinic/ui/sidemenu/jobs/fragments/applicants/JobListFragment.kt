package com.elite.medical.clinic.ui.sidemenu.jobs.fragments.applicants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobNApplicantsViewModel
import com.elite.medical.databinding.FragmentJobNApplicantsBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobsByClinicsModel

class JobListFragment : Fragment() {
    private lateinit var binding: FragmentJobNApplicantsBinding
    private lateinit var viewModel: JobNApplicantsViewModel

    private lateinit var adapter: JobListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobNApplicantsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobNApplicantsViewModel::class.java]




//        return inflater.inflate(R.layout.fragment_job_n_applicants, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        viewModel.jobsList.observe(viewLifecycleOwner) { it ->
            binding.loader.visibility = View.GONE
            adapter = JobListAdapter(it!!, viewModel)
            binding.listview.adapter = adapter

            adapter.onItemClickListener = {details ->
                onItemClicked(details)
            }

            /*adapter.onItemClickListener = { details ->

            }*/

        }


    }

    private fun onItemClicked(details: JobsByClinicsModel.NurseApplicant) {
        viewModel.currentJobID.postValue(details.jobId)
        viewModel.currentNurseList.postValue(details.nurses)

        findNavController()
            .navigate(R.id.action_jobNApplicantsFragment_to_listJobApplicantsFragment)
    }


}