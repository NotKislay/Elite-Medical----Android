package com.elite.medical.admin.ui.sidemenu.dashboard.clinic_details.more

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ApprovedNursesAdapter
import com.elite.medical.admin.adapters.sidemenu.dashboard.NurseReviewByClinicFromNurseDetailsAdapter
import com.elite.medical.databinding.ActivityNurseReviewFromAssocNurseListBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ReviewDetails

class ActivityNurseReviewFromAssocNurseList : AppCompatActivity() {

    private lateinit var binding: ActivityNurseReviewFromAssocNurseListBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nurse_review_from_assoc_nurse_list
        )

        binding.btnBack.setOnClickListener { finish() }


        val ratingsAndComments = intent.getParcelableArrayListExtra<ReviewDetails>(
            "ratings_and_comments"
        )

        if (ratingsAndComments.isNullOrEmpty()) {
            displayDialogWhenNoReviews()
            binding.loader.visibility = View.GONE
        } else {
            recyclerView = binding.rvAssocNurseNurseReview
            val adapter = NurseReviewByClinicFromNurseDetailsAdapter(ArrayList(ratingsAndComments))
            recyclerView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
            recyclerView.adapter = adapter
            binding.loader.visibility = View.GONE
        }


    }

    private fun displayDialogWhenNoReviews() {
        val customDialog = Dialog(this)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        // Customize the dialog components
        val modaltitle = customDialog.findViewById<TextView>(R.id.tv_more_details)
        modaltitle.text = "No Reviews !"
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