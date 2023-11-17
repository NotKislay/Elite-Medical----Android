package com.elite.medical.clinic.fragments.jobs.applicants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobNApplicantsViewModel
import com.elite.medical.databinding.FragmentListJobApplicantsBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobsByClinicsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.Nurse

class NursesListFragment : Fragment() {
    private lateinit var binding: FragmentListJobApplicantsBinding
    private lateinit var viewModel: JobNApplicantsViewModel

    private lateinit var nurses: List<JobsByClinicsModel.NurseApplicant.Nurse>
    private var currentJobID: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListJobApplicantsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobNApplicantsViewModel::class.java]


        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        currentJobID = viewModel.currentJobID.value!!
        nurses = viewModel.currentNurseList.value!!

        val adapter = NurseListAdapter(nurses, viewModel)

        binding.listview.layoutManager = LinearLayoutManager(requireContext())
        binding.listview.adapter = adapter









//        return inflater.inflate(R.layout.fragment_list_job_applicants, container, false)
        return binding.root
    }

}