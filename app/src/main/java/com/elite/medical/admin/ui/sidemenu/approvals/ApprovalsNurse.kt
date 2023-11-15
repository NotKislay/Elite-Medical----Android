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
import com.elite.medical.admin.adapters.sidemenu.approvals.ApprovalNurseAdapter
import com.elite.medical.databinding.ActivityNursesBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.approvals.ApprovalAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel

class ApprovalsNurse : AppCompatActivity() {

    private lateinit var binding: ActivityNursesBinding
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nurses)



        recyclerView = binding.rvNurses
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        populateRecyclerView()
        binding.btnBack.setOnClickListener { finish() }


    }

    private fun populateRecyclerView() {
        val token = EliteMedical.AuthTokenAdmin
        if (token != null) {
            ApprovalAPIs.fetchNurseApprovalList(token,
                object : ApprovalAPIs.Companion.NurseApprovalCallback {
                    override fun onListReceived(nurses: List<NurseDetailsFromNurseApprovalModel>) {
                        val adapter =
                            ApprovalNurseAdapter(ArrayList(nurses), this@ApprovalsNurse, true)
                        recyclerView.adapter = adapter
                        binding.loader.visibility = View.GONE
                    }

                    override fun onResponseErr(msg: String, statusCode: String) {
                        Toast.makeText(this@ApprovalsNurse, msg, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })
        }
    }
}