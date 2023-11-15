package com.elite.medical.admin.ui.sidemenu.reviews

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.review.ClinicReviewAdapter
import com.elite.medical.clinic.adapter.NurseRevByClinicFromAvlNurseDetlsAdapter
import com.elite.medical.databinding.ActivityNavigationClinicReviewBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.ReviewsAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ClinicReviewFromClinicReviewModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.ReviewFromEnrNurseDet

class ClinicReviewForNurseFromSrcNurDet : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationClinicReviewBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviews: ArrayList<ReviewFromEnrNurseDet>

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
        //todo add the adpater
        println(reviews)
//        val adapter =
//            NurseRevByClinicFromAvlNurseDetlsAdapter(reviews, this@ClinicReviewForNurseFromSrcNurDet)
//        recyclerView.adapter = adapter
        binding.loader.visibility = View.GONE
//        getClinicReviews()


    }

    //todo if uncommented this funcn please change the api call it should be for clinic currently its in admin
//    private fun getClinicReviews() {
//
//        ReviewsAPIs.fetchClinicsReview(object : ReviewsAPIs.Companion.ClinicReviewsCallback {
//            override fun onListReceived(reviews: List<ClinicReviewFromClinicReviewModel>) {
//                println("revwis are"+reviews)
//                val adapter =
//                    ClinicReviewAdapter(ArrayList(reviews), this@ClinicReviewForNurseFromSrcNurDet)
//                recyclerView.adapter = adapter
//                binding.loader.visibility = View.GONE
//            }
//
//        })
//    }

}