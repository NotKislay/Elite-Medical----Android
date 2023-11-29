package com.elite.medical.clinic.fragments.jobs.applicants


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.ItemNurseReviewBinding
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel

class ReviewAdapter(private val listItem: ArrayList<AdminDashboardModel.NurseReview>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemNurseReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {



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


    }
}