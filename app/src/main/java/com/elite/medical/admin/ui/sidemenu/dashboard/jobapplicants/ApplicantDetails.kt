package com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityApplicantDetailsBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.AllJobApplicant
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.NurseFromApprovedJobApplication
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid.JobClinicNurseDetailsByID
import java.text.SimpleDateFormat
import java.util.Locale

class ApplicantDetails : AppCompatActivity() {
    private lateinit var binding: ActivityApplicantDetailsBinding
    private lateinit var jobID: String
    private lateinit var nurseID: String
    private lateinit var allDetails: JobClinicNurseDetailsByID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_applicant_details)

        binding.btnBack.setOnClickListener { finish() }
        val nurseDetails =
            intent.getParcelableExtra<NurseFromApprovedJobApplication>("nurseDetails")
        val allDetails = intent.getParcelableExtra<AllJobApplicant>("allDetails")


        jobID = allDetails!!.jobId.toString()
        nurseID = nurseDetails!!.id.toString()

        setupMoreButton()
        fetchAllDetailsByID(jobID, nurseID)

    }

    private fun fetchAllDetailsByID(jobID: String, nurseID: String) {
        DashboardAPIs.getJobApplicationDetailsByID(jobID, nurseID,
            object : DashboardAPIs.Companion.JobApplicationsDetailsCallback {
                override fun onDetailsReceived(details: JobClinicNurseDetailsByID) {
                    allDetails = details
                    displayJobDetails()
                    binding.loader.visibility = View.GONE
                }
            })
    }

    private fun setupMoreButton() {
        val moreButton = binding.btnMore
        moreButton.setOnClickListener {
            // Creating a custom dialog
            val dialogModelBinding: ModalLayoutClinicDetailsMoreBinding =
                ModalLayoutClinicDetailsMoreBinding.inflate(layoutInflater)
            val customDialog = Dialog(this)
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            customDialog.setContentView(dialogModelBinding.root)



            dialogModelBinding.tvMoreDetails.visibility = View.GONE
            dialogModelBinding.divider1.visibility = View.GONE
            dialogModelBinding.btnNurseAssocModal.visibility = View.GONE
            dialogModelBinding.dividerBtn1.visibility = View.GONE

            val jobDetailsButton = dialogModelBinding.btnNurseAssocModal


            val nurseDetailsButton = dialogModelBinding.btn1
            val clinicDetailsButton = dialogModelBinding.btnReviewsModal

//            val nurseDetailsButtonDivider = dialogModelBinding.dividerBtn1
            nurseDetailsButton.visibility = View.VISIBLE
//            nurseDetailsButtonDivider.visibility = View.VISIBLE

            val cancelButton = dialogModelBinding.btnCancelModal



            jobDetailsButton.text = "Job Details"
            nurseDetailsButton.text = "Nurse Details"
            clinicDetailsButton.text = "Clinic Details"


            jobDetailsButton.setOnClickListener {
                customDialog.dismiss()
                displayJobDetails()
            }

            clinicDetailsButton.setOnClickListener {
                customDialog.dismiss()
                val intent = Intent(this, ClinicDetailsActivity::class.java)
                intent.putExtra("data", allDetails.clinic)
                startActivity(intent)

            }

            nurseDetailsButton.setOnClickListener {
                customDialog.dismiss()

                val intent = Intent(this, NurseDetailsActivity::class.java)
                intent.putExtra("data", allDetails.nurse)
                startActivity(intent)

            }

            cancelButton.setOnClickListener { customDialog.dismiss() }
            customDialog.show()
        }
    }

    private fun displayJobDetails() {
        val jobDetails = allDetails.job
        val inputformat= SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
        val reqFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date= inputformat.parse(jobDetails.createdAt)!!

        val jobDetailsArray = arrayOf(
            "Job Title: ${jobDetails.title}",
            "Job Type: ${jobDetails.type}",
            "Start Date: ${jobDetails.engageFrom}",
            "End Date: ${jobDetails.engageTo}",
            "Job Locations: ${jobDetails.locations.joinToString(",")}",
            "Vacancy: ${jobDetails.vacancy}",
            "Status: ${jobDetails.status}",
            "Created On: ${reqFormat.format(date)}",
            "Description: ${jobDetails.description}",
        )
        val adapter =
            ArrayAdapter(this, R.layout.custom_single_item_textview, jobDetailsArray)
        binding.listView.adapter = adapter
        binding.appbarTitle.text = "Job Details"
    }

    private fun displayClinicDetails() {
        val clinicDetails = allDetails.clinic
        val jobDetailsArray = arrayOf(
            "Clinic Name: ${clinicDetails.name}",
            "Contact No: ${clinicDetails.mobile}",
            "Email: ${clinicDetails.email}",
            "Address: ${clinicDetails.address}",
            "Clinic Type: ${clinicDetails.clinicType}",
            "VAT/TIN No: ${clinicDetails.vatNo}",
            "CST No: ${clinicDetails.cstNo}",
            "Service Tax No: ${clinicDetails.serviceTaxNo}",
            "Clinic UIN: ${clinicDetails.uinNo}",
            "Declaration: ${clinicDetails.declaration}",
            "Approval Status: ${clinicDetails.approvalStatus}",
            "View Licence: https://staging.emfwebapp.ikshudigital.com/storage/${clinicDetails.clinicLicense}",
        )
        val adapter =
            ArrayAdapter(this, R.layout.custom_single_item_textview, jobDetailsArray)
        binding.listView.adapter = adapter
        binding.appbarTitle.text = "Clinic Details"
    }

}