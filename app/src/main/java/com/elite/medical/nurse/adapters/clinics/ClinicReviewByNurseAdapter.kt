package com.elite.medical.nurse.adapters.clinics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.ItemNurseReviewBinding
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ReviewEnrolledClinic

class ClinicReviewByNurseAdapter(
    private val listItem: ArrayList<ReviewEnrolledClinic>,
    private val context: Context
) : RecyclerView.Adapter<ClinicReviewByNurseAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemNurseReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var rating= binding.rating
        var comment_body= binding.comment
        var time_ago= binding.tvDaysAgo
        var clinicName = binding.clinicName
        var review_layout = binding.reviewLayout
        val update_btn = binding.btnSubmitReview
        val edit_text = binding.addComment


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNurseReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data= listItem.elementAt(position)
        holder.clinicName.text = "Your review for this Clinic"
        holder.rating.rating = data.rating.toFloat()

        holder.edit_text.setText(data.comment)
        holder.edit_text.visibility = View.GONE
        holder.time_ago.visibility = View.GONE
        holder.comment_body.text = data.comment
        holder.update_btn.visibility = View.GONE
        holder.comment_body.maxLines = 2


        var fullLength = false
        holder.review_layout.setOnClickListener {
            fullLength = !fullLength

            if (fullLength)
                holder.comment_body.maxLines = 40
            else
                holder.comment_body.maxLines = 2
        }

        holder.update_btn.setOnClickListener {
            Toast.makeText(context, "Build me, Im inside adapter", Toast.LENGTH_LONG).show()
        }

    }
}
