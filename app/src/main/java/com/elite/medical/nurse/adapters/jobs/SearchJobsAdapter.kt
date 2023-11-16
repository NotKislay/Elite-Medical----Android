package com.elite.medical.nurse.adapters.jobs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.RvItemJobSearchBinding
import com.elite.medical.nurse.viewmodels.NurseViewModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job

class SearchJobsAdapter(
    private var list: List<Job>,
    private val viewModel: NurseViewModel,
    private val context: Context?
) :
    RecyclerView.Adapter<SearchJobsAdapter.ViewHolder>() {


    var onItemClicked: ((Job) -> Unit)? = null
    var applyForJob: ((Job) -> Unit)? = null

    inner class ViewHolder(binding: RvItemJobSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val layout = binding.layout

        val row1 = binding.row1
        val row2 = binding.row2
        val row3 = binding.row3
        val row4 = binding.row4
        val row5 = binding.row5
        val row6 = binding.row6
        val row7 = binding.row7
        val row8 = binding.row8
        val row9 = binding.row9
        val row10 = binding.row10
        val row11 = binding.row11

        var tv1 = binding.tv1
        var tv2 = binding.tv2
        var tv3 = binding.tv3
        var tv4 = binding.tv4
        var tv5 = binding.tv5
        var tv6 = binding.tv6
        var tv7 = binding.tv7
        var tv8 = binding.tv8
        var tv9 = binding.tv9
        var tv10 = binding.tv10
        var btnApply = binding.btnApply
        var btnAlreadyApplied = binding.btnAlreadyApplied

        var label1 = binding.label1
        var label2 = binding.label2
        var label3 = binding.label3
        var label4 = binding.label4
        var label5 = binding.label5
        var label6 = binding.label6
        var label7 = binding.label7
        var label8 = binding.label8
        var label9 = binding.label9
        var label10 = binding.label10
        var label11 = binding.label11


        val btnGoDeep = binding.btnGoDeep

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemJobSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list.elementAt(position)

        holder.row1.isVisible = true
        holder.row2.isVisible = true
        holder.row3.isVisible = true
        holder.row4.isVisible = true
        holder.row5.isVisible = true
        holder.row6.isVisible = false
        holder.row7.isVisible = false
        holder.row8.isVisible = true
        holder.row9.isVisible = true
        holder.row10.isVisible = false
        holder.row11.isVisible = true

        holder.tv5.maxLines = 2

        holder.label1.text = "Title"
        holder.label2.text = "Job Type"
        holder.label3.text = "Clinic Name"
        holder.label4.text = "Location"
        holder.label5.text = "Description"
        holder.label6.text = "Start Date"
        holder.label7.text = "End Date"
        holder.label8.text = "Vacancy"
        holder.label9.text = "Applied"
        holder.label10.text = "Status"

        holder.tv1.text = item.title
        holder.tv2.text = item.type
        holder.tv3.text = item.clinicName
        holder.tv4.text = item.locations.joinToString(",")
        holder.tv5.text = item.description
        holder.tv6.text = item.engageFrom
        holder.tv7.text = item.engageTo
        holder.tv8.text = item.vacancy
        holder.tv9.text = item.applied.size.toString()
        holder.tv10.text = item.status

        val hasNurseApplied = item.nurseApplied == "True"

        if (hasNurseApplied) holder.btnAlreadyApplied.also { it.isVisible = true }
        else holder.btnApply.also { it.isVisible = true }


        holder.btnGoDeep.setOnClickListener { onItemClicked?.invoke(item) }
        holder.btnApply.setOnClickListener { applyForJob?.invoke(item) }

    }

    fun filterList(filteredList: List<Job>) {
        list = filteredList
        notifyDataSetChanged()
    }
}