package com.elite.medical.clinic.fragments.jobs.myjobs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentNurseDetails2Binding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.NursesAppliedOnJobModel
import com.elite.medical.utils.Constants
import com.elite.medical.utils.ConstantsNurse

class NurseDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNurseDetails2Binding
    private lateinit var viewModel: MyJobsViewModel
    private lateinit var nurseDetails: NursesAppliedOnJobModel.Nurse


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseDetails2Binding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        setupListView()

        binding.btnViewLicence.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ConstantsNurse.URL_FOR_IMAGE + nurseDetails.nurseLicense)
            )
            startActivity(urlIntent)

        }



        return binding.root
    }

    private fun setupListView() {

        nurseDetails = viewModel.currentNurseDetails

        val arrayData = listOf(
            "Name: ${nurseDetails.name}",
            "Contact No.: ${nurseDetails.mobile}",
            "Email: ${nurseDetails.email}",
            "D.O.B.: ${nurseDetails.dob}",
            "Address: ${nurseDetails.address}",
            "License Type: ${nurseDetails.licenseType}",
            "License Expiry: ${nurseDetails.licenseExpiry}",
            "Experience(In Years): ${nurseDetails.experience}",
            "Speciality: ${nurseDetails.speciality}",
            "US Immigration Status: ${nurseDetails.usImmgStatus}",
            "NCLEX Status: ${nurseDetails.nclexStatus}",
            "CGFNS Status: ${nurseDetails.cgfnsStatus}",
            "Hiring Status: ${nurseDetails.hiringStatus}",
            "Nurse Availability: ${nurseDetails.availability}",
        )


        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayData)
        binding.listview.adapter = adapter


    }

}