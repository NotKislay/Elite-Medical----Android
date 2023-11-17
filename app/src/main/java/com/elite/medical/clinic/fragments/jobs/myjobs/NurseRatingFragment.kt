package com.elite.medical.clinic.fragments.jobs.myjobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentNurseRatingBinding


class NurseRatingFragment : Fragment() {
    private lateinit var binding: FragmentNurseRatingBinding
    private lateinit var viewModel: MyJobsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseRatingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]

        binding.btnBack.setOnClickListener { activity?.onBackPressed() }



        return binding.root
    }


}