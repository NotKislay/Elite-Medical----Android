package com.elite.medical.admin.adapters.sidemenu.review

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.ItemNurseReviewBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.NurseReviewFromNurseReviewModel

class NurseReviewAdapter(private val listItem: ArrayList<NurseReviewFromNurseReviewModel>):RecyclerView.Adapter<NurseReviewAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemNurseReviewBinding):RecyclerView.ViewHolder(binding.root){

        val clinicName: TextView = binding.clinicName
        var nurseName = binding.nurseName
        val daysAgo: TextView = binding.tvDaysAgo
        val rating: RatingBar = binding.rating
        val commentContent: TextView = binding.comment
        val reviewLayout: LinearLayout = binding.reviewLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNurseReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listItem.elementAt(position)

        holder.clinicName.text = item.clinicName
        holder.nurseName.text = item.nurseName
        holder.nurseName.isVisible = true
        holder.rating.rating = item.rating.toFloat()
        holder.commentContent.text = item.comment
        holder.daysAgo.text = item.timeAgo

        var fullLength = false
        holder.reviewLayout.setOnClickListener {
            fullLength = !fullLength

            if (fullLength)
                holder.commentContent.maxLines = 40
            else
                holder.commentContent.maxLines = 2
        }

    }
}