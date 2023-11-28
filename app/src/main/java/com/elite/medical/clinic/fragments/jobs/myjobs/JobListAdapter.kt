package com.elite.medical.clinic.fragments.jobs.myjobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.CustomListItemBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.myjobs.MyJobsListModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel

class JobListAdapter(
    private val items: List<MyJobsListModel.Job>,
    private val viewModel: MyJobsViewModel
) :
    RecyclerView.Adapter<JobListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CustomListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnGoDeep.visibility = View.VISIBLE
        }

        val layout = binding.layout

        var row1: TableRow = binding.row1
        var row2: TableRow = binding.row2
        var row3: TableRow = binding.row3
        var row4: TableRow = binding.row4
        var row5: TableRow = binding.row5
        var row6: TableRow = binding.row6
        var row7: TableRow = binding.row7

        var tv1: TextView = binding.tv1
        var tv2: TextView = binding.tv2
        var tv3: TextView = binding.tv3
        var tv4: TextView = binding.tv4
        var tv6: TextView = binding.tv6
        var tv7: TextView = binding.tv7

        var label1: TextView = binding.label1
        var label2: TextView = binding.label2
        var label3: TextView = binding.label3
        var label4: TextView = binding.label4
        var label6: TextView = binding.label6
        var label7: TextView = binding.label7

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.row6.visibility = View.VISIBLE
        holder.row7.visibility = View.VISIBLE



        holder.label1.text = "Job Title"
        holder.label2.text = "Job Type"
        holder.label3.text = "Description"
        holder.label4.text = "Applied"
        holder.label6.text = "Job Status"
        holder.label7.text = "Date Created"

        holder.tv1.text = items[position].title
        holder.tv2.text = items[position].type
        holder.tv3.text = items[position].description
        holder.tv4.text = items[position].applied.size.toString()
        holder.tv6.text = items[position].status
        holder.tv7.text = items[position].formattedCreatedAt

        holder.tv3.maxLines = 2

        holder.layout.setOnClickListener { onItemClicked?.invoke(items.elementAt(position)) }
    }

    var onItemClicked: ((MyJobsListModel.Job) -> Unit)? = null


}