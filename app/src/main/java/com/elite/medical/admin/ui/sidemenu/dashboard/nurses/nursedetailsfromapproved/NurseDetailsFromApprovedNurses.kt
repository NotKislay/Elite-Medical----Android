package com.elite.medical.admin.ui.sidemenu.dashboard.nurses.nursedetailsfromapproved

import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
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

        binding.table.isVisible = false


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

                    val data = arrayOf(
                        nurseArray.name.toString(),
                        nurseArray.mobile.toString(),
                        nurseArray.email.toString(),
                        nurseArray.dob.toString(),
                        nurseArray.address.toString(),
                        nurseArray.licenseType.toString(),
                        nurseArray.licenseExpiry.toString(),
                        nurseArray.experience.toString(),
                        nurseArray.speciality.toString(),
                        nurseArray.usImmgStatus.toString(),
                        nurseArray.nclexStatus.toString(),
                        nurseArray.cgfnsStatus.toString(),
                    )

                    displayDetails(data)

                    setData(details)
                    binding.loader.visibility = View.GONE
                }

                override fun onResponseErr(errorData: String, statusCode: Int) {}
            })
    }


    private fun displayDetails(data: Array<String>) {

        binding.table.isVisible = true

        binding.tv1.text = data.elementAt(0)
        binding.tv2.text = data.elementAt(1)
        binding.tv3.text = data.elementAt(2)
        binding.tv4.text = data.elementAt(3)
        binding.tv5.text = data.elementAt(4)
        binding.tv6.text = data.elementAt(5)
        binding.tv7.text = data.elementAt(6)
        binding.tv8.text = data.elementAt(7)
        binding.tv9.text = data.elementAt(8)
        binding.tv10.text = data.elementAt(9)
        binding.tv11.text = data.elementAt(10)
        binding.tv12.text = data.elementAt(11)

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