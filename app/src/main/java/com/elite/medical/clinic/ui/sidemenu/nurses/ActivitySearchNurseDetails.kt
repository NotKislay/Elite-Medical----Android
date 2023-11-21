package com.elite.medical.clinic.ui.sidemenu.nurses

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.nurses.more.ClinicReviewForNurseFromSNurseDetails
import com.elite.medical.databinding.ActivitySearchNurseDetailsBinding
import com.elite.medical.databinding.CustomDialogWithSpinnerBinding
import com.elite.medical.retrofit.apis.clinic.sidemenu.NursesClinicAPIs
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details.ClinicReviewModelFromAvlblNurseDetails
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.NurseFromEnrNurseDet

class ActivitySearchNurseDetails : AppCompatActivity() {

    private lateinit var binding: ActivitySearchNurseDetailsBinding
    private lateinit var reviewslist: List<ClinicReviewModelFromAvlblNurseDetails>
    private lateinit var listofjobs: MutableList<String>
    private lateinit var getid: String
    private lateinit var nurseName: String
    private lateinit var jobtitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_nurse_details)
        binding.btnBack.setOnClickListener { finish() }
        binding.listViewEnrNursesDet.visibility = View.GONE


        getid = intent.getStringExtra("Nurse_id")!!
        fetchAvailableNurseDetailByID(getid)

        binding.moreBtnEnrNurseDetails.setOnClickListener {
            // Creating a custom dialog
            val customDialog = Dialog(this)
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

            // Customize the dialog components
            val cancelBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
            val inviteBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
            inviteBtn.text = "Invite Nurse"
            val reviewsBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)

            inviteBtn.setOnClickListener {
                customDialog.dismiss()//get the id and list of aprvdjobs
                inviteNurse()
            }

            reviewsBtn.setOnClickListener {
                customDialog.dismiss()
                val intent = Intent(this, ClinicReviewForNurseFromSNurseDetails::class.java)
                intent.putParcelableArrayListExtra("ClinicReviewforNurse", ArrayList(reviewslist))
                startActivity(intent)

            }

            cancelBtn.setOnClickListener {
                customDialog.dismiss()
            }
            customDialog.show()
        }
    }

    private fun inviteNurse() {
        val dialog = Dialog(this)

        //adding a blank item in listof jobs

        listofjobs.add(0, "Select job title")
        val binding: CustomDialogWithSpinnerBinding =
            CustomDialogWithSpinnerBinding.inflate(layoutInflater)
        val msg = binding.msg
        val spinnerJob = binding.spinnerPopup
        val close = binding.btnClose
        val invite = binding.btnInvite
        msg.text = "Select Job title for sending invitation to nurse $nurseName"

        val spinnerjobadapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listofjobs)
        spinnerJob.adapter = spinnerjobadapter
        spinnerJob.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    jobtitle = listofjobs[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        close.setOnClickListener {
            dialog.dismiss()
        }

        invite.setOnClickListener {
            inviteNurseToJobByID(getid, jobtitle)
            dialog.dismiss()
        }
        dialog.setContentView(binding.root)
        dialog.show()
    }

    private fun inviteNurseToJobByID(nurseID: String, jobTitle: String) {
        NursesClinicAPIs.inviteNurseToJobByID(
            nurseID,
            jobTitle,
            object : NursesClinicAPIs.Companion.InviteNurseCallback {
                override fun onSuccess(msg: GenericSuccessErrorModel) {
                    Toast.makeText(this@ActivitySearchNurseDetails, msg.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onError(msg: GenericSuccessErrorModel?) {
                    Toast.makeText(this@ActivitySearchNurseDetails, "something went wrong.", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    private fun fetchAvailableNurseDetailByID(id: String) {

        NursesClinicAPIs.getAvlblNurseDetailsByid(id,
            object : NursesClinicAPIs.Companion.avlblNurseDetailsByIdCallback {
                override fun onNurseDetailsReceived(
                    nurses: NurseFromEnrNurseDet,
                    statusCode: Int?
                ) {
                    nurseName = nurses?.name.toString()
                    binding.listViewEnrNursesDet.visibility = View.VISIBLE
                    val arrayData = listOf(
                        "Name: $nurseName",
                        "Email: ${nurses?.email}",
                        "Contact No.: ${nurses?.mobile}",
                        "D.O.B.: ${nurses?.dob}",
                        "Address: ${nurses?.address}",
                        "License Type: ${nurses?.licenseType}",
                        "License Expiry: ${nurses?.licenseExpiry}",
                        "Experience: ${nurses?.experience}",
                        "Speciality: ${nurses?.speciality}",
                        "US Immigration Status: ${nurses?.usImmigrationStatus}",
                        "NCLEX Status: ${nurses?.nclexStatus}",
                        "CGFNS Status: ${nurses?.cgfnsStatus}"
                    )
                    binding.loader.visibility = View.GONE
                    val adapter = ArrayAdapter(
                        this@ActivitySearchNurseDetails,
                        R.layout.custom_single_item_textview,
                        arrayData
                    )
                    binding.listViewEnrNursesDet.adapter = adapter
                }

                override fun onReviewReceived(
                    reviews: List<ClinicReviewModelFromAvlblNurseDetails>,
                    statusCode: Int?
                ) {
                    reviewslist = reviews
                }

                override fun onListofApprovedJobsReceived(joblist: List<String>, statusCode: Int?) {
                    listofjobs = joblist as MutableList<String>
                }

            })
        binding.loader.visibility = View.GONE
    }
}