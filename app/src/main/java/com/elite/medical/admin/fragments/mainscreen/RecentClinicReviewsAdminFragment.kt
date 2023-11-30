package com.elite.medical.admin.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.admin.adapters.RecentClinicReviewAdapter
import com.elite.medical.admin.viewmodels.AdminViewModel
import com.elite.medical.databinding.FragmentRecentClinicReviewsAdminBinding

class RecentClinicReviewsAdminFragment : Fragment() {
    private lateinit var binding: FragmentRecentClinicReviewsAdminBinding
    private lateinit var viewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentClinicReviewsAdminBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[AdminViewModel::class.java]


        val clinicReviewList = viewModel.dashboardData.value?.clinicReviews
        if (clinicReviewList != null) {
            val adapter = RecentClinicReviewAdapter(clinicReviewList)
            binding.listview.adapter = adapter
        }

        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

}