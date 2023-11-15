package com.elite.medical.admin.ui.sidemenu.dashboard.clinic_details.more

import android.app.Dialog
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.dashboard.ClinicDetailsAdapterFromClinicDetails
import com.elite.medical.admin.adapters.sidemenu.review.ClinicReviewAdapter
import com.elite.medical.databinding.ActivityNavigationClinicReviewBinding
import com.elite.medical.databinding.ActivityNavigationNurseReviewBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.ClinicReviewsFromClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NursesDetailsFromAssociatedNurseModel

class ActivityClinicReviewsFromMoreClinicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationClinicReviewBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_clinic_review)

        binding.btnBack.setOnClickListener { finish() }
        binding.appHeading.text="Clinic Reviews By Nurses"
        val reviews = intent.getParcelableArrayListExtra<ClinicReviewsFromClinicDetailsModel>("clinicReviews")

        if(reviews.isNullOrEmpty()){
            displayDialogWhenNoReviews()
            binding.loader.visibility = View.GONE
        }
        else{
            recyclerView=binding.rvNavClinicReview
            recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)


            val adapter =
                ClinicDetailsAdapterFromClinicDetails(ArrayList(reviews), this,"")
            recyclerView.adapter = adapter
            binding.loader.visibility = View.GONE
        }

    }

    private fun displayDialogWhenNoReviews(){
        val customDialog = Dialog(this)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        // Customize the dialog components
        val modaltitle= customDialog.findViewById<TextView>(R.id.tv_more_details)
        modaltitle.text="No Reviews !"
        val okBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
        okBtn.text = "Ok"
        val nurseAssocBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
        nurseAssocBtn.visibility=View.GONE
        val reviewsBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)
        reviewsBtn.visibility=View.GONE
        customDialog.findViewById<View>(R.id.divider1).visibility=View.GONE
        customDialog.findViewById<View>(R.id.divider2).visibility=View.GONE

        okBtn.setOnClickListener {
            customDialog.dismiss()
            finish()
        }
        customDialog.show()

    }
}