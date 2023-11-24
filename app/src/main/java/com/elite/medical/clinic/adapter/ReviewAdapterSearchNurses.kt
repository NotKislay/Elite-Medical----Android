package com.elite.medical.clinic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details.ClinicReviewModelFromAvlblNurseDetails

class ReviewAdapterSearchNurses(private val cardItems: ArrayList<ClinicReviewModelFromAvlblNurseDetails>) :
    RecyclerView.Adapter<ReviewAdapterSearchNurses.ModelViewHolder>() {

    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.clinic_name)
        val daysago: TextView = itemView.findViewById(R.id.tv_days_ago)
        val rating: RatingBar = itemView.findViewById(R.id.rating)
        val commentcontent: TextView = itemView.findViewById(R.id.comment)
        val nurse_name = itemView.findViewById<TextView>(R.id.clinic_name)
        val review_layout: LinearLayout = itemView.findViewById(R.id.review_layout)//item layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_nurse_review, parent, false)
        return ModelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val currentItem = cardItems[position]

        holder.nurse_name.isVisible = false
        holder.name.text = currentItem.clinic_name
        holder.daysago.text = currentItem.formatteddate
        holder.rating.rating = currentItem.rating.toFloat()
        holder.commentcontent.text = currentItem.comment
        var fullLength = false
        holder.review_layout.setOnClickListener {
            fullLength = !fullLength

            if (fullLength)
                holder.commentcontent.maxLines = 40
            else
                holder.commentcontent.maxLines = 2
        }
    }

    override fun getItemCount() = cardItems.size
}

