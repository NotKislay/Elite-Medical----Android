package com.elite.medical.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.databinding.ItemDetailScreenBinding
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel

class GenericDetailsAdapter(
    private val list: List<AppliedJobsModel.AppliedJob>,
) :
    RecyclerView.Adapter<GenericDetailsAdapter.ViewHolder>() {


    var onItemClicked: ((AppliedJobsModel.AppliedJob) -> Unit)? = null

    inner class ViewHolder(binding: ItemDetailScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
        val row12 = binding.row12
        val row13 = binding.row13
        val row14 = binding.row14
        val row15 = binding.row15

        var tv1: TextView = binding.tv1
        var tv2: TextView = binding.tv2
        var tv4: TextView = binding.tv4
        var tv5: TextView = binding.tv5
        var tv6: TextView = binding.tv6
        var tv7: TextView = binding.tv7
        var tv8: TextView = binding.tv8
        var tv9: TextView = binding.tv9
        var tv10: TextView = binding.tv10
        var tv11: TextView = binding.tv11
        var tv12: TextView = binding.tv12
        var tv13: TextView = binding.tv13
        var tv14: TextView = binding.tv14
        var tv15: TextView = binding.tv15

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
        var label11: TextView = binding.label11
        var label12: TextView = binding.label12
        var label13: TextView = binding.label13
        var label14: TextView = binding.label14
        var label15: TextView = binding.label15


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetailScreenBinding.inflate(
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
        holder.row10.isVisible = true
        holder.row11.isVisible = true
        holder.row12.isVisible = true
        holder.row13.isVisible = true
        holder.row14.isVisible = true
        holder.row15.isVisible = true




    }
}
