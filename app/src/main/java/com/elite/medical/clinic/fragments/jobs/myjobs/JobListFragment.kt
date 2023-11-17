package com.elite.medical.clinic.fragments.jobs.myjobs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentJobListBinding

class JobListFragment : Fragment() {

    private lateinit var viewModel: MyJobsViewModel
    private lateinit var binding: FragmentJobListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]

        viewModel.loadJobList()


        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

/*        viewModel.jobListLiveData.observe(requireActivity()) {
            val adapter = JobListAdapter(it!!.jobs, viewModel)
            binding.listview.adapter = adapter
            binding.loader.visibility = View.GONE
        }*/




        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadJobList()

        viewModel.jobListLiveData.observe(requireActivity()) {
            val adapter = JobListAdapter(it!!.jobs, viewModel)
            binding.listview.adapter = adapter
            binding.loader.visibility = View.GONE
        }



    }


}