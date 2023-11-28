package com.elite.medical.clinic.fragments.jobs.myjobs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentJobListBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.myjobs.MyJobsListModel

class JobListFragment : Fragment() {

    private lateinit var viewModel: MyJobsViewModel
    private lateinit var binding: FragmentJobListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]

        viewModel.fetchMyJobsList()



        viewModel.myJobsListCallback = { it ->
            val adapter = JobListAdapter(it.jobs, viewModel)
            binding.listview.adapter = adapter
            binding.loader.visibility = View.GONE
            adapter.onItemClicked = {
                viewModel.updateJobID(it.id)
                findNavController().navigate(R.id.action_jobListFragment_to_jobDetailsFragment2)
            }
        }


        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

    private fun onItemClicked(details: MyJobsListModel.Job) {}


}