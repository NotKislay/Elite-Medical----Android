package com.elite.medical.admin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.ItemNurseReviewBinding
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel

class RecentNurseReviewAdapter(private val listItem: ArrayList<AdminDashboardModel.NurseReview>):RecyclerView.Adapter<RecentNurseReviewAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemNurseReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        val clinicName: TextView = binding.clinicName
        var nurseName = binding.nurseName
        val daysAgo: TextView = binding.tvDaysAgo
        val rating: RatingBar = binding.rating
        val comment: TextView = binding.comment
        val reviewLayout: LinearLayout = binding.reviewLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNurseReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val currentItem = listItem[position]

        holder.clinicName.text = currentItem.clinicName
        holder.nurseName.text = currentItem.nurseName
        holder.nurseName.isVisible = true
        holder.daysAgo.text = currentItem.timeAgo
        holder.rating.rating = currentItem.rating.toFloat()
        holder.comment.text = currentItem.comment

        var fullLength = false

        holder.reviewLayout.setOnClickListener {
            fullLength = !fullLength

            if (fullLength)
                holder.comment.maxLines = 40
            else
                holder.comment.maxLines = 2
        }

    }


}