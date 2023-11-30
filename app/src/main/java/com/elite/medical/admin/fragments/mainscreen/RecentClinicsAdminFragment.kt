package com.elite.medical.admin.fragments.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.admin.adapters.RecentClinicsAdapter
import com.elite.medical.admin.viewmodels.AdminViewModel
import com.elite.medical.databinding.FragmentRecentClinicsAdminBinding

class RecentClinicsAdminFragment : Fragment(),View.OnClickListener {
    private lateinit var binding: FragmentRecentClinicsAdminBinding
    private lateinit var viewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentClinicsAdminBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[AdminViewModel::class.java]


        val data = viewModel.dashboardData.value?.clinics

        if (data != null) {
            val adapter = RecentClinicsAdapter(data, requireContext(), "Recent Clinics")
            binding.tvRecentClinics.adapter = adapter
        }





        binding.btnBack.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(view: View?) {

        when(view?.id) {

            binding.btnBack.id -> activity?.onBackPressedDispatcher?.onBackPressed()



        }

    }

}