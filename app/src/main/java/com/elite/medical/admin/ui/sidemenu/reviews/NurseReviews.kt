package com.elite.medical.admin.ui.sidemenu.reviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.sidemenu.review.NurseReviewAdapter
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.databinding.ActivityNavigationNurseReviewBinding
import com.elite.medical.retrofit.apis.admin.sidemenu.ReviewsAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.NurseReviewFromNurseReviewModel
import com.elite.medical.utils.HelperMethods

class NurseReviews : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationNurseReviewBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_nurse_review)

        recyclerView = binding.rvNavNurseReview
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getNurseReviews()

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun getNurseReviews() {
        val token = EliteMedical.AuthTokenAdmin
        ReviewsAPIs.fetchNursesReview(object : ReviewsAPIs.Companion.NurseReviewsCallback {
            override fun onListReceived(
                reviews: List<NurseReviewFromNurseReviewModel>?,
                statusCode: Int
            ) {
                if (statusCode == 200) {
                    val adapter =
                        NurseReviewAdapter(ArrayList(reviews))
                    recyclerView.adapter = adapter
                    binding.loader.visibility = View.GONE
                }

            }

        })
    }
}


//do not delete
//<GridLayout
//android:layout_width="match_parent"
//android:layout_height="70dp"
//android:background="#012A4A"
//android:columnCount="3"
//android:orientation="horizontal">
//
//<ImageButton
//android:layout_columnWeight="1"
//android:id="@+id/btn_nav_menu"
//android:layout_width="50dp"
//android:layout_height="50dp"
//android:layout_gravity="start|center"
//android:layout_marginStart="15dp"
//android:background="#012A4A"
//android:scaleX="1.5"
//android:scaleY="1.5"
//android:src="@drawable/icon_menu" />
//
//<TextView
//android:layout_columnWeight="1"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_gravity="center"
//android:gravity="center"
//android:text="Dashboard"
//android:minWidth="200dp"
//android:textColor="@color/white"
//android:textSize="20sp"
//android:layout_column="1"
//android:layout_columnSpan="1"
///>
//
//<ImageButton
//android:layout_columnWeight="1"
//android:id="@+id/avatarImageView"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_gravity="end|center_vertical"
//android:background="@drawable/circular_background"
//android:paddingVertical="10dp"
//android:src="@drawable/icon_avatar"
//android:layout_marginEnd="10dp"
///>
//
//</GridLayout>