package com.elite.medical.admin.ui.sidemenu.dashboard.nurses.nursedetailsfromapproved

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.NurseTimesheetAdapter
import com.elite.medical.databinding.ActivityTimesheetFromNurseDetailsBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.TimesheetDataModel
import com.google.android.material.snackbar.Snackbar

class TimesheetNurse : AppCompatActivity() {

    private lateinit var binding: ActivityTimesheetFromNurseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_timesheet_from_nurse_details)
        binding.btnBack.setOnClickListener { finish() }


        val id = intent.getIntExtra("Id", 0)
        fetchTimeSheet(id.toString())
    }

    private fun fetchTimeSheet(id: String) {
        DashboardAPIs.getTimesheetByNurseId(id,
            object : DashboardAPIs.Companion.NurseTimesheetCallback {
                override fun onListReceived(timesheetDataModels: List<TimesheetDataModel>) {
                    val adapter = NurseTimesheetAdapter(timesheetDataModels)

                    binding.rvTimedata.adapter = adapter
                    binding.loader.visibility = View.GONE
                }

                override fun onErr(errorMsg: String) {
                    binding.loader.visibility = View.GONE
                    val dialog= Dialog(this@TimesheetNurse)
                    dialog.setContentView(R.layout.modal_layout_clinic_details_more)

                    dialog.findViewById<TextView>(R.id.tv_more_details).text = "$errorMsg"
                    dialog.findViewById<Button>(R.id.btnReviews_modal).visibility=View.GONE
                    dialog.findViewById<Button>(R.id.btnNurseAssoc_modal).visibility=View.GONE
                    dialog.findViewById<View>(R.id.divider1).visibility=View.GONE
                    dialog.findViewById<View>(R.id.divider2).visibility=View.GONE
                    dialog.findViewById<Button>(R.id.btnCancel_modal).text="Go Back"
                    dialog.findViewById<Button>(R.id.btnCancel_modal).setOnClickListener{ finish()}
                    dialog.show()
                }

            })
    }
}