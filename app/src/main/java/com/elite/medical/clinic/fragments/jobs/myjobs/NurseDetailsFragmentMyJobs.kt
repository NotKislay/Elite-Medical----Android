package com.elite.medical.clinic.fragments.jobs.myjobs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentNurseDetails2Binding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.myjobs.MyJobDetailsByIDModel

class NurseDetailsFragmentMyJobs : Fragment() {
    private lateinit var binding: FragmentNurseDetails2Binding
    private lateinit var viewModel: MyJobsViewModel
    private lateinit var nurseDetails: MyJobDetailsByIDModel.Nurse


    private lateinit var currJobID: String
    private lateinit var currClinicID: String
    private lateinit var currNurseID: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseDetails2Binding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]
        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }


        currJobID = viewModel.currentJobID.value.toString()
        currClinicID = viewModel.currentClinicID.value.toString()
        currNurseID = viewModel.currentNurseDetails.value!!.id.toString()

        currClinicID

        setupListView()
        setupMoreButton()
        setupHireActions(viewModel.currentNurseDetails.value!!)

        /*        binding.btnViewLicence.setOnClickListener {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(ConstantsNurse.URL_FOR_IMAGE + nurseDetails.nurseLicense)
                    )
                    startActivity(urlIntent)

                }*/

        viewModel.hireActionCallback = {

            if (it.status == "success") {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                activity?.onBackPressedDispatcher?.onBackPressed()
//                findNavController().navigate(R.id.action_nurseDetailsFragment2_to_clinicDashboardFragment)
            } else Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()


        }



        binding.btnHire.setOnClickListener {
            val jobActionsModel = JobHiringActionModel(currNurseID, currClinicID, currJobID, "hire")
            viewModel.callHireAction(jobActionsModel)
        }

        binding.btnReject.setOnClickListener {
            val jobActionsModel =
                JobHiringActionModel(currNurseID, currClinicID, currJobID, "reject")
            viewModel.callHireAction(jobActionsModel)
        }

        binding.btnTrial.setOnClickListener {
            val jobActionsModel =
                JobHiringActionModel(currNurseID, currClinicID, currJobID, "trial")
            viewModel.callHireAction(jobActionsModel)
        }



        return binding.root
    }

    private fun setupListView() {

        nurseDetails = viewModel.currentNurseDetails.value!!

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

    private fun setupHireActions(nurseDetails: MyJobDetailsByIDModel.Nurse?) {

        nurseDetails!!

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


    private fun setupMoreButton() {
        binding.btnMore.setOnClickListener {
            // Creating a custom dialog
            val dialogModelBinding: ModalLayoutClinicDetailsMoreBinding =
                ModalLayoutClinicDetailsMoreBinding.inflate(layoutInflater)
            val customDialog = Dialog(requireContext())
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            customDialog.setContentView(dialogModelBinding.root)



            dialogModelBinding.tvMoreDetails.visibility = View.GONE
            dialogModelBinding.divider1.visibility = View.GONE
            dialogModelBinding.btnNurseAssocModal.visibility = View.GONE
            dialogModelBinding.dividerBtn1.visibility = View.GONE

            val nurseDetailsButton = dialogModelBinding.btn1

            val jobDetailsButton = dialogModelBinding.btnNurseAssocModal
            val clinicDetailsButton = dialogModelBinding.btnReviewsModal
            jobDetailsButton.visibility = View.GONE
            clinicDetailsButton.visibility = View.GONE
            nurseDetailsButton.visibility = View.VISIBLE

            val cancelButton = dialogModelBinding.btnCancelModal

            nurseDetailsButton.text = "Nurse Review"
            nurseDetailsButton.setOnClickListener {
                customDialog.dismiss()
                findNavController().navigate(R.id.action_nurseDetailsFragment2_to_nurseRatingFragment)
            }

            cancelButton.setOnClickListener { customDialog.dismiss() }
            customDialog.show()
        }
    }


}