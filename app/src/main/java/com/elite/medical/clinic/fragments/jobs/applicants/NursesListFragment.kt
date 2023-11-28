package com.elite.medical.clinic.fragments.jobs.applicants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobApplicantsViewModel
import com.elite.medical.databinding.FragmentListJobApplicantsBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.ClinicJobApplicantsModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NursesListFragment : Fragment() {
    private lateinit var binding: FragmentListJobApplicantsBinding
    private lateinit var viewModel: JobApplicantsViewModel
    private lateinit var nurses: List<ClinicJobApplicantsModel.NurseApplicant.Nurse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListJobApplicantsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobApplicantsViewModel::class.java]



        nurses = viewModel.applicantsList.value!!

        val isJobClosed = true

        val adapter = NurseListAdapter(nurses, viewModel.isCurrentJobClosed.value!!)
        binding.listview.layoutManager = LinearLayoutManager(requireContext())
        binding.listview.adapter = adapter
        adapter.onItemClicked = {
            viewModel.currentNurseDetails.postValue(it)
            viewModel.currentNurseID.postValue(it.id)
            findNavController()
                .navigate(R.id.action_listJobApplicantsFragment_to_nurseDetailsFragment)
        }







        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

}