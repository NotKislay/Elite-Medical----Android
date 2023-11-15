package com.elite.medical.clinic.ui.sidemenu.jobs.fragments.applicants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobNApplicantsViewModel
import com.elite.medical.databinding.FragmentJobNApplicantsBinding

class JobListFragment : Fragment() {
    private lateinit var binding: FragmentJobNApplicantsBinding
    private lateinit var viewModel: JobNApplicantsViewModel

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

        viewModel.jobsList.observe(viewLifecycleOwner) {
            binding.loader.visibility = View.GONE
            val adapter = JobListAdapter(it!!.nurseApplicants, viewModel)
            binding.listview.adapter = adapter
        }


    }

}