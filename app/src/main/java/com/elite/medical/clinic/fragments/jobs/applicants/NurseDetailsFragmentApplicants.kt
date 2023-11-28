package com.elite.medical.clinic.fragments.jobs.applicants

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobApplicantsViewModel
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentNurseDetailsBinding
import com.elite.medical.databinding.ModalLayoutBinding
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.ClinicJobApplicantsModel


class NurseDetailsFragmentApplicants : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNurseDetailsBinding
    private lateinit var dialogBinding: ModalLayoutBinding

    private lateinit var dialog: Dialog

    private lateinit var viewModel: JobApplicantsViewModel
    private lateinit var viewModelMyJobs: MyJobsViewModel
    private lateinit var nurseDetails: ClinicJobApplicantsModel.NurseApplicant.Nurse

    private lateinit var currJobID: String
    private lateinit var currClinicID: String
    private lateinit var currNurseID: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobApplicantsViewModel::class.java]
        viewModelMyJobs = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]

        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

        nurseDetails = viewModel.currentNurseDetails.value!!

        currJobID = viewModel.currentJobID.value.toString()
        currClinicID = viewModel.currentClinicID.value.toString()
        currNurseID = viewModel.currentNurseID.value.toString()


        populateList()
        setupMoreBtn()
        setupHireActionButtons(nurseDetails)

        viewModel.hireActionCallback = {

            if (it.status == "success") {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            else {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }

        }



        binding.btnHire.setOnClickListener {
            val jobActionsModel = JobHiringActionModel(currNurseID,currClinicID,currJobID,"hire")
            viewModel.callHireAction(jobActionsModel)
        }

        binding.btnReject.setOnClickListener {
            val jobActionsModel = JobHiringActionModel(currNurseID,currClinicID,currJobID,"reject")
            viewModel.callHireAction(jobActionsModel)
        }

        binding.btnTrial.setOnClickListener {
            val jobActionsModel = JobHiringActionModel(currNurseID,currClinicID,currJobID,"trial")
            viewModel.callHireAction(jobActionsModel)
        }



        return binding.root

    }

    private fun setupHireActionButtons(nurseDetails: ClinicJobApplicantsModel.NurseApplicant.Nurse) {

        if (nurseDetails.buttonStatus.status && nurseDetails.availability == "Available") {

            if (nurseDetails.buttonStatus.employment.action != "hire" &&
                nurseDetails.buttonStatus.employment.status != "hiring_approved" &&
                nurseDetails.buttonStatus.employment.action != "reject"
            ) {
                binding.btnHire.isVisible = true
            }
            if (nurseDetails.buttonStatus.employment.trial == "false" &&
                nurseDetails.buttonStatus.employment.action != "hire" &&
                nurseDetails.buttonStatus.employment.action != "reject"
            ) {
                binding.btnTrial.isVisible = true
            }
            if (nurseDetails.buttonStatus.employment.action != "reject" &&
                nurseDetails.buttonStatus.employment.action != "hire"
            ) {
                binding.btnReject.isVisible = true
            }

        } else if (nurseDetails.availability == "Booked") {
            binding.btnReject.isVisible = false
            binding.btnTrial.isVisible = false
            binding.btnHire.isVisible = false
        } else {
            binding.btnReject.isVisible = true
            binding.btnTrial.isVisible = true
            binding.btnHire.isVisible = true
        }

    }

    private fun setupMoreBtn() {
        binding.moreBtnEnrNurseDetails.setOnClickListener {

            dialogBinding = ModalLayoutBinding.inflate(layoutInflater)
            dialog = Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)

            val btn1 = dialogBinding.btn1
            val btn2 = dialogBinding.btn2
            val btn3 = dialogBinding.btn3
            val btn4 = dialogBinding.btn4
            val btn5 = dialogBinding.btn5
            val ruler1 = dialogBinding.divider3
            val ruler2 = dialogBinding.divider4
            val ruler3 = dialogBinding.divider5


            btn1.text = "Reviews"
            btn5.text = "Cancel"

            btn4.isVisible = false
            btn2.isVisible = false
            btn3.isVisible = false
            ruler1.isVisible = false
            ruler2.isVisible = false
            ruler3.isVisible = false

            btn1.setOnClickListener(this)
            btn5.setOnClickListener(this)

            dialog.show()
        }
    }

    private fun populateList() {
        val arrayData = listOf(
            "Name: ${nurseDetails.name}",
            "Email: ${nurseDetails.email}",
            "Address: ${nurseDetails.address}",
            "License Expiry: ${nurseDetails.licenseExpiry}",
            "Speciality: ${nurseDetails.speciality}",
            "NCLEX Status: ${nurseDetails.nclexStatus}",
            "Hiring  Status: ${nurseDetails.approvalStatus}",
            "Contact No.: ${nurseDetails.mobile}",
            "D.O.B.: ${nurseDetails.dob}",
            "License Type: ${nurseDetails.licenseType}",
            "Experience: ${nurseDetails.experience}",
            "US Immigration Status: ${nurseDetails.usImmgStatus}",
            "CGFNS Status: ${nurseDetails.cgfnsStatus}",
            "Nurse Availability: ${nurseDetails.availability}",
            "Hiring Status: ${nurseDetails.hiringStatus}"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayData)
        binding.listview.adapter = adapter

    }

    override fun onClick(view: View?) {

        when (view?.id) {

            dialogBinding.btn1.id -> {
                dialog.dismiss()
                viewModelMyJobs.getNurseDetailsMyJobs(currJobID, currNurseID)
                findNavController().navigate(R.id.action_nurseDetailsFragment_to_applicantNurseReviewsFragment)
            }

            dialogBinding.btn5.id -> {
                dialog.dismiss()
            }


        }

    }

}