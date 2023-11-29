package com.elite.medical.admin.ui.sidemenu.dashboard.nurses.nursedetailsfromapproved

import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityNurseDetailsFromApprovedNursesBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.NurseByUserIdModel

class NurseDetailsFromApprovedNurses : AppCompatActivity() {

    private lateinit var userId: String
    private lateinit var binding: ActivityNurseDetailsFromApprovedNursesBinding
    private lateinit var returnDetails: NurseByUserIdModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nurse_details_from_approved_nurses
        )

//        userId = intent.getStringExtra("userId").toString()
        val extras = intent.extras!!
        userId = extras.getString("userID", "")
        val id = extras.getInt("id", 0)


        fetchData()

        val myButton = findViewById<TextView>(R.id.moreBtn_nurse_details)
        myButton.setOnClickListener {
            openPopUp()
        }

        binding.btnTimesheet.setOnClickListener {
            val intent = Intent(this, TimesheetNurse::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun fetchData() {
        DashboardAPIs.getNurseByUserId(
            userId,
            object : DashboardAPIs.Companion.NurseByUserIdCallback {
                override fun onListReceived(details: NurseByUserIdModel) {

                    val nurseArray = details.nurse

                    val arrayData = arrayOf(
                        "Name: ${nurseArray.name}",
                        "Contact No.: ${nurseArray.mobile}",
                        "Email: ${nurseArray.email}",
                        "D.O.B.: ${nurseArray.dob}",
                        "Address: ${nurseArray.address}",
                        "License Type: ${nurseArray.licenseType}",
                        "License Expiry: ${nurseArray.licenseExpiry}",
                        "Experience: ${nurseArray.experience}",
                        "Speciality: ${nurseArray.speciality}",
                        "US Immigration Status: ${nurseArray.usImmgStatus}",
                        "NCLEX Status: ${nurseArray.nclexStatus}",
                        "CGFNS Status: ${nurseArray.cgfnsStatus}",
                    )


                    val adapter = ArrayAdapter(
                        this@NurseDetailsFromApprovedNurses,
                        R.layout.custom_single_item_textview,
                        arrayData
                    )
                    binding.listView.adapter = adapter
                    setData(details)
                    binding.loader.visibility = View.GONE
                }

                override fun onResponseErr(errorData: String, statusCode: Int) {}
            })
    }

    private fun setData(details: NurseByUserIdModel) {
        returnDetails = details
    }

    private fun openPopUp() {
        val customDialog = Dialog(this)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        val cancelBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
        cancelBtn.text = "Reviews"
        cancelBtn.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)

        val jobAppliedBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
        jobAppliedBtn.text = "Job Applied"


        val clinicBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)
        clinicBtn.text = "Clinic Enrolled"


        jobAppliedBtn.setOnClickListener {
            customDialog.dismiss()
            val intent = Intent(this, ActivityJobApplied::class.java)
            intent.putExtra("appliedJobs", ArrayList(returnDetails.appliedJobs))
            intent.putExtra("user_ID", returnDetails.userId)
            startActivity(intent)
        }

        clinicBtn.setOnClickListener {
            customDialog.dismiss()
            val intent = Intent(this, ActivityClinicEnrolled::class.java)
            intent.putExtra("details", ArrayList(returnDetails.empDetails))
            startActivity(intent)
        }

        cancelBtn.setOnClickListener {
            customDialog.dismiss()
            val intent = Intent(this, ActivityReviewsByClinic::class.java)
            intent.putExtra("reviews", ArrayList(returnDetails.nurseReviews))
            intent.putExtra("empdetails", ArrayList(returnDetails.empDetails))
            startActivity(intent)
        }
        customDialog.show()
    }
}