package com.elite.medical.clinic.ui.sidemenu.nurses

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.clinic.adapter.EnrolledNursesAdapter
import com.elite.medical.databinding.ActivityEnrolledNursesBinding
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.elite.medical.retrofit.apis.clinic.sidemenu.NursesClinicAPIs
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse

class EnrolledNurses : AppCompatActivity() {

    private lateinit var binding: ActivityEnrolledNursesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enrolled_nurses)
        binding.btnBack.setOnClickListener { finish() }

        populateList()

    }

    private fun populateList() {
        NursesClinicAPIs.getNurseEnrolled(object :
            NursesClinicAPIs.Companion.NursesEnrolledCallback {
            override fun onListReceived(nurses: List<Nurse>) {
                binding.loader.visibility = View.GONE
                if (nurses.isNotEmpty()) {
                    val adapter = EnrolledNursesAdapter(nurses, this@EnrolledNurses)
                    binding.rvEnrolledNurses.adapter = adapter
                } else {
                    val dialog = Dialog(this@EnrolledNurses)
                    val dialogBinding = ModalLayoutClinicDetailsMoreBinding.inflate(layoutInflater)
                    dialog.setContentView(dialogBinding.root)

                    dialogBinding.tvMoreDetails.text = "No Nurses Enrolled !"
                    dialogBinding.btnReviewsModal.visibility = View.GONE
                    dialogBinding.btnNurseAssocModal.visibility = View.GONE
                    dialogBinding.divider1.visibility = View.GONE
                    dialogBinding.divider2.visibility = View.GONE
                    dialogBinding.btnCancelModal.text = "Go Back"
                    dialogBinding.btnCancelModal.setOnClickListener { finish() }

                    dialog.show()
                }
            }

        })
    }

}