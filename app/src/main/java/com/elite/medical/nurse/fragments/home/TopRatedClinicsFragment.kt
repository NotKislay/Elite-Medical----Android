package com.elite.medical.nurse.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.elite.medical.databinding.FragmentTopRatedClinicsBinding
import com.elite.medical.nurse.adapters.home.TopRatedClinicsAdapter
import com.elite.medical.nurse.viewmodels.UserNurseMainViewModel


class TopRatedClinicsFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedClinicsBinding
    private val viewModel by viewModels<UserNurseMainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTopRatedClinicsBinding.inflate(inflater, container, false)
        viewModel.getNurseDashboardData()
        viewModel.nurseDashboardDataCallback = { p1, p2 ->

            if (p1 != null) {

                val topRatedClinics = p1.topClinics

                if (topRatedClinics.isNotEmpty()) {
                    val adapter = TopRatedClinicsAdapter(topRatedClinics)
                    binding.listview.adapter = adapter
                    binding.loader.isVisible = false
                } else {
                    Toast.makeText(requireContext(), "No Data Available", Toast.LENGTH_SHORT).show()
                    activity?.onBackPressedDispatcher?.onBackPressed()
                }
            }


        }



        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

}