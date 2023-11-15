package com.elite.medical.clinic.ui.sidemenu.jobs.fragments.applicants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.JobNApplicantsViewModel
import com.elite.medical.databinding.CustomListItemBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.NurseApplicant

class JobListAdapter(
    private val items: List<NurseApplicant>,
    private val viewModel: JobNApplicantsViewModel
) :
    RecyclerView.Adapter<JobListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CustomListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val layout = binding.layout
        val row4 = binding.row4

        var tv1: TextView = binding.tv1
        var tv2: TextView = binding.tv2
        var tv3: TextView = binding.tv3

        var label1: TextView = binding.label1
        var label2: TextView = binding.label2
        var label3: TextView = binding.label3

        val btnGoDeep = binding.btnGoDeep

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = items[position]

        holder.row4.visibility = View.GONE

        holder.label1.text = "Job Title"
        holder.label2.text = "Job Type"
        holder.label3.text = "Job Created At"

        holder.tv1.text = currentItem.jobTitle
        holder.tv2.text = currentItem.jobType
        holder.tv3.text = currentItem.jobCreatedAt

        holder.btnGoDeep.visibility = View.VISIBLE


        holder.layout.setOnClickListener {


            viewModel.currentJobID.postValue(currentItem.jobId)
            viewModel.currentClinicID.postValue(0)
            viewModel.currentNurseList.postValue(currentItem.nurses)

            it.findNavController()
                .navigate(R.id.action_jobNApplicantsFragment_to_listJobApplicantsFragment)

        }


    }
}