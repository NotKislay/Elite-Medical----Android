package com.elite.medical.nurse.fragments.clinics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.R
import com.elite.medical.databinding.FragmentEnrolledClinicsDetailsBinding
import com.elite.medical.nurse.Communicator
import com.elite.medical.nurse.viewmodels.clinics.ClinicsViewModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ReviewEnrolledClinic


class EnrolledClinicsDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEnrolledClinicsDetailsBinding
    private lateinit var viewmodel: ClinicsViewModel
    private lateinit var currentID: String
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnrolledClinicsDetailsBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[ClinicsViewModel::class.java]
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        currentID = viewmodel.currentClinicID.value!!

        getClinicDetailsByID(currentID)
        return binding.root
    }

    private fun getClinicDetailsByID(id: String) {
        viewmodel.getClinicDetailsByID(id)
        viewmodel.enrolledClinicDetails.observe(viewLifecycleOwner) { it ->

            val data = arrayOf(
                "Clinic Name : ${it.clinic.name}",
                "Job title : ${it.employment.jobTitle}",
                "Contact Number : ${it.clinic.mobile}",
                "Email : ${it.clinic.email}",
                "Address : ${it.clinic.address}",
                "Clinic Type : ${it.clinic.clinicType}",
                "VAT/ TIN No : ${it.clinic.vatNo}",
                "CST No. : ${it.clinic.cstNo}",
                "Service Tax No. : ${it.clinic.serviceTaxNo}",
                "Clinic UIN : ${it.clinic.uinNo}",
                "Declaration : ${it.clinic.declaration}",
            )

            binding.loader.isVisible = false

            val arrayAdapter =
                ArrayAdapter(requireContext(), R.layout.custom_single_item_textview, data)

            binding.listViewEnrClinicsDet.adapter = arrayAdapter

            val reviews = it.review


            binding.btnReviews.setOnClickListener { displayreviews(reviews) }
        }
    }

    private fun displayreviews(review: List<ReviewEnrolledClinic>) {

        viewmodel.reviews.postValue(review)
//        communicator = requireActivity() as Communicator
//        communicator.passData("test data that was passed")
        findNavController().navigate(R.id.action_enrolledClinicsDetailsFragment_to_clinicReviewsFragment)
    }

}