package com.elite.medical.utils


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.elite.medical.R
import com.elite.medical.databinding.ItemDetailScreenBinding
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel

class GenericDetailsAdapterB(
    private val labels: List<String>,
    private val data: List<String>
) :
    RecyclerView.Adapter<GenericDetailsAdapterB.ViewHolder>() {
    class ViewHolder(binding: ItemDetailScreenBinding) : RecyclerView.ViewHolder(binding.root) {

        private val titleContent: TextView = binding.label1
        private val tvContent: TextView = binding.tv1

        fun bind(label: String, data: String) {
            titleContent.text = label
            tvContent.text = data
        }
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

    override fun getItemCount(): Int {
        return minOf(labels.size, data.size, 15)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position < labels.size && position < data.size) {
            holder.bind(labels[position], data[position])
            holder.itemView.isVisible = true
        } else {
            holder.itemView.visibility = View.GONE
        }

    }

}