package com.elite.medical.clinic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.clinic.ui.dahboardscreen.RecentJobApplicationAdapter
import com.elite.medical.clinic.viewmodels.ClinicViewModel
import com.elite.medical.databinding.FragmentRecentJobApplicantsBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel


class RecentJobApplicantsFragment : Fragment() {
    private lateinit var binding: FragmentRecentJobApplicantsBinding
    private lateinit var viewModel: ClinicViewModel
    private lateinit var recentJobApplicants: List<ClinicDashboardModel.NurseApplicant>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentJobApplicantsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ClinicViewModel::class.java]

        recentJobApplicants = viewModel.recentJobApplicants.value!!

        if (recentJobApplicants.isEmpty()) {
            binding.tvNoDataFound.isVisible = true
        } else {
            val adapter = RecentJobApplicationAdapter(recentJobApplicants)
            binding.listview.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.listview.adapter = adapter
            binding.tvNoDataFound.isVisible = false
        }






        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

}