package com.elite.medical.clinic.fragments.jobs.myjobs

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants.ClinicDetailsActivity
import com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants.NurseDetailsActivity
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentJobDetailsBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class JobDetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentJobDetailsBinding
    private lateinit var viewModel: MyJobsViewModel
    private lateinit var jobID: String
    private lateinit var jobStatus: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        binding.closeJob.setOnClickListener(this)

        jobID = viewModel.jobID.value.toString()
        jobStatus = viewModel.jobStatus.value.toString()



        viewModel.getJobDetailsByID(jobID)

        viewModel.isJobCanceled.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(requireContext(), "Job Closed Successfully", Toast.LENGTH_SHORT)
                    .show()
                activity?.onBackPressed()
            }
            viewModel.isJobCanceled.postValue(false)
        }

        displayJobsDetails()
        setupMoreButton()
        return binding.root
    }

    private fun displayJobsDetails() {

        val adapter = JobDetailsAdapter(viewModel.currentJobDetails)
        binding.listviewJobDetails.layoutManager = LinearLayoutManager(requireContext())
        binding.listviewJobDetails.adapter = adapter
        if (jobStatus.isNotEmpty() && jobStatus == "closed") binding.closeJob.visibility = View.GONE

    }

    private fun setupMoreButton() {
        val moreButton = binding.btnMore
        moreButton.setOnClickListener {
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

            nurseDetailsButton.text = "Nurse List"
            nurseDetailsButton.setOnClickListener {
                customDialog.dismiss()
                findNavController().navigate(R.id.action_jobDetailsFragment_to_nurseListFragment2)
            }

            cancelButton.setOnClickListener { customDialog.dismiss() }
            customDialog.show()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            binding.closeJob.id -> {
                viewModel.closeJobByID(jobID)
            }


        }
    }

}