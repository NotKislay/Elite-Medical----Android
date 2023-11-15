package com.elite.medical.admin.adapters.sidemenu.dashboard

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.NurseReviewFromNurseReviewModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ReviewDetails

class NurseReviewByClinicFromNurseDetailsAdapter(private val listItem: ArrayList<ReviewDetails>):RecyclerView.Adapter<NurseReviewByClinicFromNurseDetailsAdapter.ViewHolder>() {
    inner class ViewHolder(view : View):RecyclerView.ViewHolder(view){

        //        var id:TextView
//        var nurse_register_id:TextView
//        var clinic_register_id:TextView
        var rating: RatingBar = itemView.findViewById(R.id.rating)
        var comment: TextView = itemView.findViewById(R.id.comment)

        //        var created_at:TextView
//        var updated_at:TextView
        var time_ago: TextView
        var review_layout: LinearLayout

        init {
            itemView.findViewById<TextView>(R.id.clinic_name).text="Review By Clinic"
            itemView.findViewById<TextView>(R.id.clinic_name).setTypeface(Typeface.DEFAULT,Typeface.NORMAL)

            time_ago = itemView.findViewById<TextView?>(R.id.tv_days_ago)
            time_ago.visibility=View.GONE
            review_layout = itemView.findViewById(R.id.review_layout)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nurse_review,parent,false))
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.rating.rating = listItem[position].rating.toFloat()
        holder.comment.text = listItem[position].comment

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