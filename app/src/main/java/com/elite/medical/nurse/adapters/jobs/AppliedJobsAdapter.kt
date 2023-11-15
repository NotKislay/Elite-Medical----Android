package com.elite.medical.nurse.adapters.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.RvItemNurseSectionAppliedjobsBinding
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel

class AppliedJobsAdapter(
    private val list: List<AppliedJobsModel.AppliedJob>,
) :
    RecyclerView.Adapter<AppliedJobsAdapter.ViewHolder>() {


    var onItemClicked: ((AppliedJobsModel.AppliedJob) -> Unit)? = null

    inner class ViewHolder(binding: RvItemNurseSectionAppliedjobsBinding) :
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
        var ratingBar: RatingBar = binding.rating
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
            RvItemNurseSectionAppliedjobsBinding.inflate(
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
        holder.row6.isVisible = true
        holder.row7.isVisible = true
        holder.row8.isVisible = true
        holder.row9.isVisible = true
        holder.row10.isVisible = false

        holder.tv5.maxLines = 2

        holder.tv1.text = item.title
        holder.tv2.text = item.type
        holder.tv9.text = "Posted Ago"

        var rating = item.clinicRating

        if (rating.isEmpty()) rating = "0"
        else rating.toFloat()

        holder.ratingBar.rating = rating.toFloat()
        holder.tv4.text = item.locations.joinToString(",")
        holder.tv5.text = item.description
        holder.tv6.text = item.vacancy
        holder.tv7.text = item.applied.size.toString()
        holder.tv8.text = item.nurseStatus

        holder.layout.setOnClickListener {
            onItemClicked?.invoke(item)
        }


    }
}