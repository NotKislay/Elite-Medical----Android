package com.elite.medical.clinic.ui.sidemenu.jobs.fragments.applicants

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
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobNApplicantsViewModel
import com.elite.medical.databinding.FragmentNurseDetailsBinding
import com.elite.medical.databinding.ModalLayoutBinding
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobsByClinicsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.Nurse


class NurseDetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNurseDetailsBinding
    private lateinit var dialogBinding: ModalLayoutBinding

    private lateinit var dialog: Dialog

    private lateinit var viewModel: JobNApplicantsViewModel
    private lateinit var nurseDetails: JobsByClinicsModel.NurseApplicant.Nurse

    private lateinit var currJobID: String
    private lateinit var currClinicID: String
    private lateinit var currNurseID: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[JobNApplicantsViewModel::class.java]

        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        nurseDetails = viewModel.currentNurseDetails.value!!
        currJobID = viewModel.currentJobID.value.toString()
        currClinicID = viewModel.currentClinicID.value.toString()
        currNurseID = nurseDetails.id.toString()


        populateList()

        binding.moreBtnEnrNurseDetails.setOnClickListener {

            dialogBinding = ModalLayoutBinding.inflate(layoutInflater)
            dialog = Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)

            val heading = dialogBinding.heading
            val btn1 = dialogBinding.btn1
            val btn2 = dialogBinding.btn2
            val btn3 = dialogBinding.btn3
            val btn4 = dialogBinding.btn4
            val btn5 = dialogBinding.btn5


            btn1.text = "Hire"
            btn2.text = "Trial"
            btn3.text = "Reject"
            btn4.text = "Reviews"
            btn5.text = "Cancel"

            btn4.isVisible = false

            btn1.setOnClickListener(this)
            btn2.setOnClickListener(this)
            btn3.setOnClickListener(this)
            btn4.setOnClickListener(this)
            btn5.setOnClickListener(this)

            dialog.show()
        }


        return binding.root

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
            "Nurse Availability: ---------",
            "Hiring Status: ----------"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayData)
        binding.listview.adapter = adapter

    }

    override fun onClick(view: View?) {

        when (view?.id) {

            dialogBinding.btn1.id -> {
                val hiringActionDetails =
                    JobHiringActionModel(currNurseID, currClinicID, currJobID, "hire")
                viewModel.callHireAction(hiringActionDetails)
                viewModel.HireActionCallback = {
                    dialog.dismiss()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
            }

            dialogBinding.btn2.id -> {
                val hiringActionDetails =
                    JobHiringActionModel(currNurseID, currClinicID, currJobID, "trial")
                viewModel.callHireAction(hiringActionDetails)
                viewModel.HireActionCallback = {
                    dialog.dismiss()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
            }

            dialogBinding.btn3.id -> {
                val hiringActionDetails =
                    JobHiringActionModel(currNurseID, currClinicID, currJobID, "reject")
                viewModel.callHireAction(hiringActionDetails)
                viewModel.HireActionCallback = {
                    dialog.dismiss()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
            }

            dialogBinding.btn4.id -> {
                dialog.dismiss()
            }

            dialogBinding.btn5.id -> {
                dialog.dismiss()
            }


        }

    }

}