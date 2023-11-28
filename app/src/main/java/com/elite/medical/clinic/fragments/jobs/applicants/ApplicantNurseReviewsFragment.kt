package com.elite.medical.clinic.fragments.jobs.applicants;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.clinic.adapter.NurseReviewsMyJobsAdapter
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentApplicantNurseReviewsBinding
import com.elite.medical.utils.HelperMethods


class ApplicantNurseReviewsFragment : Fragment() {

    private lateinit var binding: FragmentApplicantNurseReviewsBinding

    private lateinit var viewModel: MyJobsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentApplicantNurseReviewsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]

        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

        viewModel.nurseReviews.observe(viewLifecycleOwner) {
            val reviews = it!!
            if (reviews.isNotEmpty()) {
                val adapterNR = NurseReviewsMyJobsAdapter(reviews, requireContext())
                binding.rvNurseRevApplicants.adapter = adapterNR
            } else {
                HelperMethods.showDialog(
                    "No Review found!", "Go back", requireContext(), requireActivity(), activity?.onBackPressedDispatcher?.onBackPressed()
                )
            }
            binding.loader.isVisible = false
        }

        return binding.root

    }
}