package com.elite.medical.clinic.ui.sidemenu.jobs.fragments.myjobs

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
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.Nurse
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.NursesAppliedOnJobModel


class NurseListAdapter(val itemList: MutableList<NursesAppliedOnJobModel.Nurse>, private val viewModel: MyJobsViewModel) :
    RecyclerView.Adapter<NurseListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CustomListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnGoDeep.visibility = View.VISIBLE
        }

        var row1: TableRow = binding.row1
        var row2: TableRow = binding.row2
        var row3: TableRow = binding.row3
        var row4: TableRow = binding.row4
        var row5: TableRow = binding.row5
        var row6: TableRow = binding.row6
        var row7: TableRow = binding.row7
        var row8: TableRow = binding.row8

        var txt1: TextView = binding.tv1
        var txt2: TextView = binding.tv2
        var txt3: TextView = binding.tv3
        var txt4: TextView = binding.tv4
        var txt6: TextView = binding.tv6
        var txt7: TextView = binding.tv7
        var txt8: TextView = binding.tv8

        var label1: TextView = binding.label1
        var label2: TextView = binding.label2
        var label3: TextView = binding.label3
        var label4: TextView = binding.label4
        var label6: TextView = binding.label6
        var label7: TextView = binding.label7
        var label8: TextView = binding.label8

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.row6.visibility = View.VISIBLE
        holder.row7.visibility = View.VISIBLE

        holder.label1.text = "Name"
        holder.label2.text = "Licence Type"
        holder.label3.text = "License Expiry"
        holder.label4.text = "Experience (In Years)"
        holder.label6.text = "Status"
        holder.label7.text = "Availability"

        holder.txt1.text = itemList[position].name
        holder.txt2.text = itemList[position].licenseType
        holder.txt3.text = itemList[position].licenseExpiry
        holder.txt4.text = itemList[position].experience
        holder.txt6.text = itemList[position].hiringStatus
        holder.txt7.text = itemList[position].availability

        holder.binding.root.setOnClickListener {

            viewModel.currentNurseDetails = itemList[position]
            it.findNavController().navigate(R.id.action_nurseListFragment_to_nurseDetailsFragment2)

        }


    }
}