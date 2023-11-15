package com.elite.medical.clinic.ui.sidemenu.jobs.fragments.myjobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentNurseDetails2Binding
import com.elite.medical.databinding.FragmentNurseDetailsBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.Nurse

class NurseDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNurseDetails2Binding
    private lateinit var viewModel: MyJobsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseDetails2Binding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        setupListView()



        return binding.root
    }

    private fun setupListView() {

        val nurses = viewModel.currentNurseDetails

        val arrayData = listOf(
            "Name: ${nurses.name}",
            "Contact No.: ${nurses.mobile}",
            "Email: ${nurses.email}",
            "D.O.B.: ${nurses.dob}",
            "Address: ${nurses.address}",
            "License Type: ${nurses.licenseType}",
            "License Expiry: ${nurses.licenseExpiry}",
            "Experience(In Years): ${nurses.experience}",
            "Speciality: ${nurses.speciality}",
            "US Immigration Status: ${nurses.usImmgStatus}",
            "NCLEX Status: ${nurses.nclexStatus}",
            "CGFNS Status: ${nurses.cgfnsStatus}",
            "Hiring Status: ${nurses.hiringStatus}",
            "Nurse Availability: ${nurses.availability}",
        )


        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayData)
        binding.listview.adapter = adapter


    }

}