package com.elite.medical.admin.ui.sidemenu.reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.review.ClinicReviewAdapter
import com.elite.medical.databinding.ActivityNavigationClinicReviewBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.ReviewsAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ClinicReviewFromClinicReviewModel

class ClinicReviews : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationClinicReviewBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_clinic_review)

        binding.btnBack.setOnClickListener { finish() }
        recyclerView = binding.rvNavClinicReview
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        getClinicReviews()


    }

    private fun getClinicReviews() {
        ReviewsAPIs.fetchClinicsReview(object : ReviewsAPIs.Companion.ClinicReviewsCallback {
            override fun onListReceived(reviews: List<ClinicReviewFromClinicReviewModel>) {
                val adapter =
                    ClinicReviewAdapter(ArrayList(reviews), this@ClinicReviews)
                recyclerView.adapter = adapter
                binding.loader.visibility = View.GONE
            }

        })
    }

}