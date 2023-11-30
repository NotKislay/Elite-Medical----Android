package com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityNurseDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid.Nurse
import com.elite.medical.utils.endpoints.ConstantsAdmin

class NurseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNurseDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nurse_details)

        binding.btnBack.setOnClickListener { finish() }

        val data = intent.getParcelableExtra<Nurse>("data")!!

        val arrayData = arrayOf(
            data.name.toString(),
            data.mobile.toString(),
            data.email.toString(),
            data.dob.toString(),
            data.address.toString(),
            data.licenseType.toString(),
            data.licenseExpiry.toString(),
            data.experience.toString(),
            data.speciality.toString(),
            data.usImmgStatus.toString(),
            data.nclexStatus.toString(),
            data.cgfnsStatus.toString(),
            data.approvalStatus.toString(),
        )

        binding.btnViewLicence.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ConstantsAdmin.URL_FOR_IMAGE + data.nurseLicense)
            )
            startActivity(urlIntent)

        }

        setNurseDetailsData(arrayData)


    }


    private fun setNurseDetailsData(arrayData: Array<String>) {
        binding.tv1.text = arrayData.elementAt(0)
        binding.tv2.text = arrayData.elementAt(1)
        binding.tv3.text = arrayData.elementAt(2)
        binding.tv4.text = arrayData.elementAt(3)
        binding.tv5.text = arrayData.elementAt(4)
        binding.tv6.text = arrayData.elementAt(5)
        binding.tv7.text = arrayData.elementAt(6)
        binding.tv8.text = arrayData.elementAt(7)
        binding.tv9.text = arrayData.elementAt(8)
        binding.tv10.text = arrayData.elementAt(9)
        binding.tv11.text = arrayData.elementAt(10)
        binding.tv12.text = arrayData.elementAt(11)
        binding.tv13.text = arrayData.elementAt(12)
    }

}