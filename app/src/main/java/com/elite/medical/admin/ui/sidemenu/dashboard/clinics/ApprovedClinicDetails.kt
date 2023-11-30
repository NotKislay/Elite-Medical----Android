package com.elite.medical.admin.ui.sidemenu.dashboard.clinics

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.dashboard.clinics.more.ActivityClinicReviewsFromMoreClinicDetails
import com.elite.medical.admin.ui.sidemenu.dashboard.clinics.more.ActivityNurseAssocToClinic
import com.elite.medical.databinding.ActivityApprovedClinicDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.ClinicDetailsFromApprovedClinicsModel

class ApprovedClinicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityApprovedClinicDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_approved_clinic_details)


        val details =
            intent.getParcelableExtra<ClinicDetailsFromApprovedClinicsModel>(
                "clinic_details")!!

        val data = listOf(
            details.name.toString(),
            details.contactno.toString(),
            details.email.toString(),
            details.clinicaddress.toString(),
            details.clinictype.toString(),
            details.vattinno.toString(),
            details.cstno.toString(),
            details.servicetaxno.toString(),
            details.clinicuin.toString(),
            details.declaration.toString(),
        )

        displayDetails(data)






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

    private fun displayDetails(data: List<String>) {

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

    }

}