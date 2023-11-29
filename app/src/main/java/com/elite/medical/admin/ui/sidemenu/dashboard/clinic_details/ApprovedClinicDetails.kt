package com.elite.medical.admin.ui.sidemenu.dashboard.clinic_details

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.dashboard.clinic_details.more.ActivityClinicReviewsFromMoreClinicDetails
import com.elite.medical.admin.ui.sidemenu.dashboard.clinic_details.more.ActivityNurseAssocToClinic
import com.elite.medical.databinding.ActivityApprovedClinicDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.ClinicDetailsFromApprovedClinicsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.ClinicReviewsFromClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NursesDetailsFromAssociatedNurseModel

class ApprovedClinicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityApprovedClinicDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_approved_clinic_details)


        val details =
            intent.getParcelableExtra<ClinicDetailsFromApprovedClinicsModel>(
                "clinic_details")


        val arrayData = arrayOf(
            "Clinic Name: ${details?.name}",
            "Contact No: ${details?.contactno}",
            "Email: ${details?.email}",
            "Address: ${details?.clinicaddress}",
            "Clinic Type: ${details?.clinictype}",
            "VAT No: ${details?.vattinno}",
            "CST No: ${details?.cstno}",
            "Service Tax No: ${details?.servicetaxno}",
            "Clinic UIN: ${details?.clinicuin}",
            "Declaration: ${details?.declaration}"
        )


        val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
        binding.listView.adapter = adapter

        binding.moreBtnClinicdetails.setOnClickListener {
            // Creating a custom dialog
            val customDialog = Dialog(this)
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

            // Customize the dialog components
            val cancelBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
            val nurseAssocBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
            val reviewsBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)

            nurseAssocBtn.setOnClickListener {
                customDialog.dismiss()
                val intent= Intent(this,ActivityNurseAssocToClinic::class.java)
                intent.putExtra("userid",details?.userId)
                startActivity(intent)
            }

            reviewsBtn.setOnClickListener {
                customDialog.dismiss()
                val intent= Intent(this, ActivityClinicReviewsFromMoreClinicDetails::class.java)
                val dataReviews = details?.reviewlist?.let { it1 -> ArrayList(it1) }
                
                intent.putParcelableArrayListExtra("clinicReviews", dataReviews)
                startActivity(intent)//
            }

            cancelBtn.setOnClickListener {
                customDialog.dismiss()
            }
            customDialog.show()
        }


        binding.btnBack.setOnClickListener { finish() }
    }
}