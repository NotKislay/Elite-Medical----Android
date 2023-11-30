package com.elite.medical.admin.fragments.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.admin.adapters.RecentNurseReviewAdapter
import com.elite.medical.admin.viewmodels.AdminViewModel
import com.elite.medical.databinding.FragmentRecentNurseReviewsAdminBinding

class RecentNurseReviewsAdminFragment : Fragment() {
    private lateinit var binding: FragmentRecentNurseReviewsAdminBinding
    private lateinit var viewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentNurseReviewsAdminBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[AdminViewModel::class.java]


        val nurseReviewList = viewModel.dashboardData.value?.nurseReviews
        if (nurseReviewList != null) {
            val adapter = RecentNurseReviewAdapter(nurseReviewList)
            binding.listview.adapter = adapter
        }

        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }


        return binding.root
    }

}