package com.elite.medical.admin.ui.sidemenu.dashboard.clinics.more

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.elite.medical.R
import com.elite.medical.databinding.ActivityNurseDetailsFromAssocNurseBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NursesDetailsFromAssociatedNurseModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ReviewDetails

class ActivityNurseDetailsFromAssocNurse : AppCompatActivity() {

    private lateinit var binding: ActivityNurseDetailsFromAssocNurseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_nurse_details_from_assoc_nurse)

        binding.btnBack.setOnClickListener { finish() }
        val details =
            intent.getParcelableExtra<NursesDetailsFromAssociatedNurseModel>(
                "specific_nurse_details"
            )


        val data = arrayOf(
            details?.name.toString(),
            details?.mobile.toString(),
            details?.email.toString(),
            details?.dob.toString(),
            details?.address.toString(),
            details?.licenseType.toString(),
            details?.licenseExpiry.toString(),
            details?.experience.toString(),
            details?.speciality.toString(),
            details?.usImmgStatus.toString(),
            details?.nclexStatus.toString(),
            details?.cgfnsStatus.toString(),
            details?.shift.toString(),
        )

        displayDetails(data)


        for (review in details?.review!!) {
            val rating = review.rating
            val comment = review.comment

            // Now you can use the rating and comment in your activity

        }
        //More btn handle inside Nurse details via list of asssoc nurse
        binding.moreBtnClinicdetails.setOnClickListener {
            handleMoreDialog(details)
        }

    }


    private fun displayDetails(data: Array<String>) {

        binding.tv1.text = data.elementAt(0)
        binding.tv2.text = data.elementAt(1)
        binding.tv3.text = data.elementAt(2)
        binding.tv4.text = data.elementAt(3)
        binding.tv5.text = data.elementAt(4)
        binding.tv6.text = data.elementAt(5)
        binding.tv7.text = data.elementAt(6)
        binding.tv8.text = data.elementAt(7)
        binding.tv9.text = data.elementAt(8)
        binding.tv10.text = data.elementAt(9)
        binding.tv11.text = data.elementAt(10)
        binding.tv12.text = data.elementAt(11)

    }

    private fun handleMoreDialog(details: NursesDetailsFromAssociatedNurseModel) {
        val customDialog = Dialog(this)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        customDialog.findViewById<View>(R.id.divider2).visibility = View.GONE
        customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal).visibility = View.GONE

        // Customize the dialog components
        val cancelBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
        val nurseReviews = customDialog.findViewById<Button>(R.id.btnReviews_modal)
        nurseReviews.text = "Nurse Reviews"

        cancelBtn.setOnClickListener {
            customDialog.dismiss()
        }
        nurseReviews.setOnClickListener {
            customDialog.dismiss()


            if (details.review != null) {
                val reviewList = details.review

                // Create a new list to store only rating and comment
                val ratingsAndComments = mutableListOf<ReviewDetails>()

                // Iterate through the original review list and extract rating and comment
                for (review in reviewList) {
                    val rating = review.rating
                    val comment = review.comment

                    // Create a new ReviewDetails object with only rating and comment
                    val simplifiedReview = ReviewDetails(
                        formatted_date = "", // Set empty values for unused fields
                        nurse_name = "",
                        clinic_name = "",
                        time_ago = "",
                        rating = rating,
                        comment = comment
                    )

                    // Add the simplified review to the new list
                    ratingsAndComments.add(simplifiedReview)
                }

                // Now, pass the ratingsAndComments list to the next activity
                val intent = Intent(this, ActivityNurseReviewFromAssocNurseList::class.java)
                intent.putParcelableArrayListExtra(
                    "ratings_and_comments",
                    ArrayList(ratingsAndComments)
                )
                this.startActivity(intent)
            }

        }
        customDialog.show()
    }
}
