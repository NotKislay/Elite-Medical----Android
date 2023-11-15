package com.elite.medical.admin.adapters.sidemenu.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.NurseReviewFromNurseReviewModel

class NurseReviewAdapter(private val listItem: ArrayList<NurseReviewFromNurseReviewModel>):RecyclerView.Adapter<NurseReviewAdapter.ViewHolder>() {
    inner class ViewHolder(view : View):RecyclerView.ViewHolder(view){

        //        var id:TextView
//        var nurse_register_id:TextView
//        var clinic_register_id:TextView
        var rating: RatingBar = itemView.findViewById(R.id.rating)
        var comment: TextView
        //        var created_at:TextView
//        var updated_at:TextView
        var time_ago: TextView
        var clinicName: TextView
        var review_layout: LinearLayout

        init {
//            id = itemView.findViewById(R.id.id)
//            nurse_register_id = itemView.findViewById(R.id.nurse_register_id)
//            clinic_register_id = itemView.findViewById(R.id.clinic_register_id)
            comment = itemView.findViewById(R.id.comment)
//            created_at = itemView.findViewById(R.id.created_at)
//            updated_at = itemView.findViewById(R.id.updated_at)
            time_ago = itemView.findViewById(R.id.tv_days_ago)
            clinicName = itemView.findViewById(R.id.clinic_name)
            review_layout = itemView.findViewById(R.id.review_layout)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nurse_review,parent,false))
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.clinicName.text = listItem[position].clinicName
        holder.rating.rating = listItem[position].rating.toFloat()
        holder.comment.text = listItem[position].comment
        holder.time_ago.text = listItem[position].timeAgo

        var fullLength = false
        holder.review_layout.setOnClickListener {
            fullLength = !fullLength

            if (fullLength)
                holder.comment.maxLines = 40
            else
                holder.comment.maxLines = 2
        }

    }
}