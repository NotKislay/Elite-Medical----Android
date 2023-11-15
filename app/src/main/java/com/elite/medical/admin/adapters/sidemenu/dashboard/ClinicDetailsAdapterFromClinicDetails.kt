package com.elite.medical.admin.adapters.sidemenu.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.ClinicReviewsFromClinicDetailsModel

class ClinicDetailsAdapterFromClinicDetails(private val reviews: List<ClinicReviewsFromClinicDetailsModel>,
                                            private val context: Context,
                                            private val clinic_name:String) :
    RecyclerView.Adapter<ClinicDetailsAdapterFromClinicDetails.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.clinic_name)
        val daysago: TextView = itemView.findViewById(R.id.tv_days_ago)
        val rating: RatingBar = itemView.findViewById(R.id.rating)
        val commentcontent: TextView = itemView.findViewById(R.id.comment)
        val review_layout: LinearLayout = itemView.findViewById(R.id.review_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_nurse_review, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = reviews[position]


        holder.name.text = currentItem.nurseName ?: "$clinic_name"
//        holder.name.text = clinic_name
        holder.daysago.text= currentItem.formattedDate
        holder.rating.rating= currentItem.rating.toFloat()
        holder.commentcontent.text= currentItem.comment
        
        var fullLength = false
        holder.review_layout.setOnClickListener {
            fullLength = !fullLength

            if (fullLength)
                holder.commentcontent.maxLines = 40
            else
                holder.commentcontent.maxLines = 2
        }
    }

    override fun getItemCount() = reviews.size
}
