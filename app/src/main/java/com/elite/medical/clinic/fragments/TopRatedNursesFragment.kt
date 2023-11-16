package com.elite.medical.clinic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.clinic.ui.dahboardscreen.TopRatedNursesAdapter
import com.elite.medical.clinic.viewmodels.ClinicViewModel
import com.elite.medical.databinding.FragmentTopRatedNursesBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel


class TopRatedNursesFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedNursesBinding
    private lateinit var viewModel: ClinicViewModel
    private lateinit var topNurses: List<ClinicDashboardModel.TopNurse>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedNursesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ClinicViewModel::class.java]

        topNurses = viewModel.topNurses.value!!


        if (topNurses.isEmpty()) {
            binding.tvNoDataFound.isVisible = true
        } else {

            val adapter = TopRatedNursesAdapter(topNurses)
            binding.listview.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.listview.adapter = adapter
            binding.tvNoDataFound.isVisible = false


        }




        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

}