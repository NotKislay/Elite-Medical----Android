package com.elite.medical.admin.ui.sidemenu.dashboard.clinic_details.more

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.AssocNursesAdapter
import com.elite.medical.databinding.ActivityNurseAssocToClinicBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NursesDetailsFromAssociatedNurseModel

class ActivityNurseAssocToClinic : AppCompatActivity() {

    private lateinit var binding: ActivityNurseAssocToClinicBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nurse_assoc_to_clinic)

        val userId = intent.getStringExtra("userid")

        binding.btnBack.setOnClickListener { finish() }
        recyclerView = binding.rvAssocNurses
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        if (userId != null) {
            populateListOfAssocNurses(userId)
        }
    }

    private fun populateListOfAssocNurses(userID: String) {
        DashboardAPIs.getAssocNursesList(
            userID,
            object : DashboardAPIs.Companion.AssocNursesCallback {
                override fun onListReceived(clinics: List<NursesDetailsFromAssociatedNurseModel>) {
                    if (clinics.isNotEmpty()) {
                        val adapter =
                            AssocNursesAdapter(ArrayList(clinics), this@ActivityNurseAssocToClinic)
                        recyclerView.adapter = adapter
                        binding.loader.visibility = View.GONE
                    } else {
                        binding.loader.visibility = View.GONE
                        binding.textabovervlistofassocnurse.text = ""
                        binding.rvAssocNurses.visibility = View.GONE
                        binding.textabovervlistofassocnurse.visibility = View.GONE
                        displayDialogWhenNoNurses()
                    }
                }
            })
    }

    private fun displayDialogWhenNoNurses() {
        val customDialog = Dialog(this)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        // Customize the dialog components
        val modaltitle = customDialog.findViewById<TextView>(R.id.tv_more_details)
        modaltitle.text = "No Associated Nurses!"
        val okBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
        okBtn.text = "Ok"
        val nurseAssocBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
        nurseAssocBtn.visibility = View.GONE
        val reviewsBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)
        reviewsBtn.visibility = View.GONE
        customDialog.findViewById<View>(R.id.divider1).visibility = View.GONE
        customDialog.findViewById<View>(R.id.divider2).visibility = View.GONE

        okBtn.setOnClickListener {
            customDialog.dismiss()
            finish()
        }
        customDialog.show()

    }

}