package com.elite.medical.clinic.ui.sidemenu.nurses.more

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.clinic.adapter.NurseReviewByClinicAdapter
import com.elite.medical.clinic.ui.sidemenu.nurses.ActivityEnrolledNurseDetails
import com.elite.medical.clinic.viewmodels.sidemenu.NursesViewModel
import com.elite.medical.databinding.ActivityReviewsByClinicToNursesBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.ReviewFromEnrNurseDet

class ActivityReviewsByClinicToNurses : AppCompatActivity() {

    private lateinit var binding: ActivityReviewsByClinicToNursesBinding
    private lateinit var nurseid: String
    private lateinit var viewmodel: NursesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_reviews_by_clinic_to_nurses)
        viewmodel = ViewModelProvider(this)[NursesViewModel::class.java]
        binding.btnBack.setOnClickListener { finish() }


        val reviews =
            intent.getParcelableArrayListExtra<ReviewFromEnrNurseDet>("ClinicReviewforNurse")
        nurseid = ""

        for (keys in reviews!!) {
            nurseid = keys.nurseRegisterId
        }


        //todo the below if-else may be required in future
        if (reviews.isEmpty()) {
            //submit the review
            nurseid = intent.getIntExtra("NurseID", 0).toString()
            val inflater = LayoutInflater.from(this)
            val itemReviewView =
                inflater.inflate(R.layout.item_nurse_review, binding.contentarea, false)
            itemReviewView.findViewById<TextView>(R.id.clinic_name).text =
                "Post a review for this Nurse"
            itemReviewView.findViewById<TextView>(R.id.tv_days_ago).visibility = View.GONE
            itemReviewView.findViewById<TextView>(R.id.comment).visibility = View.GONE
            itemReviewView.findViewById<TextView>(R.id.nurse_name).isVisible = false
            val rating = itemReviewView.findViewById<RatingBar>(R.id.rating)
            val comment = itemReviewView.findViewById<EditText>(R.id.add_comment)
            val submitrevbtn = itemReviewView.findViewById<Button>(R.id.btn_submit_review)

            rating.setIsIndicator(false)
            comment.visibility = View.VISIBLE
            submitrevbtn.visibility = View.VISIBLE
            submitrevbtn.text = "Submit Review"

            binding.contentarea.addView(itemReviewView)
            binding.rvNurseReviewByClinic.visibility = View.GONE

            submitrevbtn.setOnClickListener {
                submitReviewForNurse(nurseid, comment.text.toString(), rating.rating)
            }
            binding.loader.visibility = View.GONE
        } else {

            val adapter = NurseReviewByClinicAdapter(ArrayList(reviews), this)
            binding.rvNurseReviewByClinic.adapter = adapter
            binding.loader.visibility = View.GONE
        }

    }

    private fun submitReviewForNurse(nurseid: String, comment: String, rating: Float) {
        viewmodel.postClinicReview(nurseid, comment, rating.toInt())
        viewmodel.onSuccessPostReviewCallback = {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ActivityEnrolledNurseDetails::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        viewmodel.onErrorPostReviewCallback = {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }
}