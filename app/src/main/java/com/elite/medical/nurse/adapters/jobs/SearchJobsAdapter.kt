package com.elite.medical.nurse.adapters.jobs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.databinding.RvItemGenericBinding
import com.elite.medical.nurse.NurseViewModel
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job

class SearchJobsAdapter(
    private var list: List<Job>,
    private val viewModel: NurseViewModel,
    private val context: Context?
) :
    RecyclerView.Adapter<SearchJobsAdapter.ViewHolder>() {


    var onItemClikced: ((Job) -> Unit)? = null

    inner class ViewHolder(binding: RvItemGenericBinding) :
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

        var tv1: TextView = binding.tv1
        var tv2: TextView = binding.tv2
        var tv3: TextView = binding.tv3
        var tv4: TextView = binding.tv4
        var tv5: TextView = binding.tv5
        var tv6: TextView = binding.tv6
        var tv7: TextView = binding.tv7
        var tv8: TextView = binding.tv8
        var tv9: TextView = binding.tv9
        var tv10: TextView = binding.tv10

        var label1: TextView = binding.label1
        var label2: TextView = binding.label2
        var label3: TextView = binding.label3
        var label4: TextView = binding.label4
        var label5: TextView = binding.label5
        var label6: TextView = binding.label6
        var label7: TextView = binding.label7
        var label8: TextView = binding.label8
        var label9: TextView = binding.label9
        var label10: TextView = binding.label10

        val btnGoDeep = binding.btnGoDeep

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemGenericBinding.inflate(
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

        holder.layout.setOnClickListener { onItemClikced?.invoke(item) }

    }

    fun filterList(filteredList: List<Job>) {
        list = filteredList
        notifyDataSetChanged()
    }
}