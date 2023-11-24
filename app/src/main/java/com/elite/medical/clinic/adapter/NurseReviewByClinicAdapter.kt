package com.elite.medical.clinic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.ReviewFromEnrNurseDet

class NurseReviewByClinicAdapter(
    private val listItem: ArrayList<ReviewFromEnrNurseDet>,
    private val context: Context
) : RecyclerView.Adapter<NurseReviewByClinicAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var rating: RatingBar = itemView.findViewById(R.id.rating)
        var comment_body: TextView
        var time_ago: TextView
        var clinicName: TextView
        var review_layout: LinearLayout
        val update_btn: Button
        val edit_text: EditText
        val nurse_name: TextView

        init {
            comment_body = itemView.findViewById(R.id.comment)
            time_ago = itemView.findViewById(R.id.tv_days_ago)
            clinicName = itemView.findViewById(R.id.clinic_name)
            review_layout = itemView.findViewById(R.id.review_layout)
            update_btn = itemView.findViewById(R.id.btn_submit_review)
            edit_text = itemView.findViewById(R.id.add_comment)
            edit_text.visibility = View.VISIBLE
            update_btn.visibility = View.VISIBLE
            nurse_name = itemView.findViewById(R.id.nurse_name)
            nurse_name.isVisible = false
            rating.setIsIndicator(true)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_nurse_review, parent, false)
        )
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.clinicName.text = "Your review for this nurse"
        holder.rating.rating = listItem[position].rating.toFloat()

//        holder.edit_text.setText(listItem[position].comment)
        holder.edit_text.visibility = View.GONE
        holder.time_ago.visibility = View.GONE
        holder.comment_body.text = listItem[position].comment
        holder.update_btn.visibility = View.GONE
        //holder.edit_text.maxLines = 2
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