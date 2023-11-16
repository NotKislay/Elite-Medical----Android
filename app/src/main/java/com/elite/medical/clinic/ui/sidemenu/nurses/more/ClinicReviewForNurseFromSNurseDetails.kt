package com.elite.medical.clinic.ui.sidemenu.nurses.more

import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.clinic.adapter.ReviewAdapterSearchNurses
import com.elite.medical.databinding.ActivityNavigationClinicReviewBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details.ClinicReviewModelFromAvlblNurseDetails

class ClinicReviewForNurseFromSNurseDetails : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationClinicReviewBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviews: ArrayList<ClinicReviewModelFromAvlblNurseDetails>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_clinic_review)

        binding.btnBack.setOnClickListener { finish() }

        reviews= intent.getParcelableArrayListExtra("ClinicReviewforNurse")!!

        recyclerView = binding.rvNavClinicReview
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )

        if(reviews.isNotEmpty()){
            val adapter = ReviewAdapterSearchNurses(reviews)
            recyclerView.adapter = adapter
        }
        else{
            val customdialog=Dialog(this)
            customdialog.setContentView(R.layout.modal_layout_clinic_details_more)

            customdialog.findViewById<TextView>(R.id.tv_more_details).text="No Reviews !"
            customdialog.findViewById<Button>(R.id.btnReviews_modal).visibility=View.GONE
            customdialog.findViewById<Button>(R.id.btnNurseAssoc_modal).visibility=View.GONE
            customdialog.findViewById<View>(R.id.divider1).visibility=View.GONE
            customdialog.findViewById<View>(R.id.divider2).visibility=View.GONE
            val btngoback=customdialog.findViewById<Button>(R.id.btnCancel_modal)
            btngoback.text="Go back"
            btngoback.setOnClickListener {
                finish()
            }
            customdialog.show()
        }

        binding.loader.visibility = View.GONE

    }
}