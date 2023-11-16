package com.elite.medical.nurse.fragments.appliedjobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.databinding.FragmentAppliedJobsBinding
import com.elite.medical.nurse.adapters.jobs.AppliedJobsAdapter
import com.elite.medical.nurse.viewmodels.jobs.JobsViewModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel

class AppliedJobs : Fragment() {

    private lateinit var binding: FragmentAppliedJobsBinding
    private lateinit var viewModel: JobsViewModel
    private lateinit var adapter: AppliedJobsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppliedJobsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobsViewModel::class.java]

        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        viewModel.appliedJobs.observe(viewLifecycleOwner) { it ->
            if (!it.isNullOrEmpty()) {
                adapter = AppliedJobsAdapter(it)
                binding.listview.layoutManager = LinearLayoutManager(requireContext())
                binding.listview.adapter = adapter
                binding.loader.isVisible = false
                adapter.onItemClicked = {
                    onItemClicked(it)
                }
            }
            else {
                Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show()
                binding.loader.isVisible = false
                activity?.onBackPressed()
            }

        }

        return binding.root
    }

    private fun onItemClicked(job: AppliedJobsModel.AppliedJob) {
        viewModel.currentJobID.postValue(job.id.toString())
        findNavController().navigate(R.id.action_appliedJobsTab_to_appliedJobDetails)

    }


}