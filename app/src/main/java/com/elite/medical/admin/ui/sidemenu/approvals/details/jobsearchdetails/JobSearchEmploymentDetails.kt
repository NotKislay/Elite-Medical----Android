package com.elite.medical.admin.ui.sidemenu.approvals.details.jobsearchdetails

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.elite.medical.R
import com.elite.medical.databinding.ActivityEmploymentApprovalEmploymentDetailsBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.Employment

class JobSearchEmploymentDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmploymentApprovalEmploymentDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmploymentApprovalEmploymentDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }


        val details =
            intent.getParcelableExtra<Employment>(
                "details"
            )!!

        if (details.trial == "false") {


            val arrayData = arrayOf(
                "Employment Start: ${details.empStart}",
                "Employment End: ${details.empEnd}",
            )

            val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
            binding.listView.adapter = adapter


        } else {

            val arrayData = arrayOf(
                "Trial Start: ${details.trialStart}",
                "Trial End: ${details.trialEnd}",
            )
            val adapter = ArrayAdapter(this, R.layout.custom_single_item_textview, arrayData)
            binding.listView.adapter = adapter
        }
    }
}