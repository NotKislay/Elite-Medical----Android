package com.elite.medical.admin.ui.sidemenu.approvals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.approvals.ApprovalClinicsAdapter
import com.elite.medical.databinding.ActivityClinicsBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ApprovalAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicApprovalModel

class ApprovalsClinic : AppCompatActivity() {

    private lateinit var binding: ActivityClinicsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_clinics)


        recyclerView = binding.rvClinics
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        populateRecyclerView()

        binding.btnBack.setOnClickListener { finish() }

    }

    private fun populateRecyclerView() {
        val token = EliteMedical.AuthTokenAdmin
        ApprovalAPIs.fetchClinicApprovalList(token!!,
            object : ApprovalAPIs.Companion.ClinicApprovalCallback {
                override fun onListReceived(clinics: List<ClinicApprovalModel.ClinicApproval>) {

                    val adapter =
                        ApprovalClinicsAdapter(
                            clinics,
                            this@ApprovalsClinic,
                            "ClinicApprovals"
                        )
                    recyclerView.adapter = adapter
                    binding.loader.visibility = View.GONE
                }

                override fun onResponseErr(msg: String, statusCode: String) {

                    Toast.makeText(this@ApprovalsClinic, msg, Toast.LENGTH_SHORT)
                        .show()
                    finish()

                }

            })
    }
}