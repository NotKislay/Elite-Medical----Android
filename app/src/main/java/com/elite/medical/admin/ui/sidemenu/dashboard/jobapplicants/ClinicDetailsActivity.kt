package com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityClinicDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid.Clinic
import com.elite.medical.utils.endpoints.ConstantsAdmin

class ClinicDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClinicDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_clinic_details)

        binding.btnBack.setOnClickListener { finish() }

        val data = intent.getParcelableExtra<Clinic>("data")!!

        binding.btnViewLicence.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ConstantsAdmin.URL_FOR_IMAGE + data.clinicLicense)
            )
            startActivity(urlIntent)
        }

        val jobDetailsArray = arrayOf(
            data.name.toString(),
            data.mobile.toString(),
            data.email.toString(),
            data.address.toString(),
            data.clinicType.toString(),
            data.vatNo.toString(),
            data.cstNo.toString(),
            data.serviceTaxNo.toString(),
            data.uinNo.toString(),
            data.approvalStatus.toString(),
            data.declaration.toString(),
        )

        displayDetails(jobDetailsArray)


    }

    private fun displayDetails(data: Array<String>) {

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

    }
}