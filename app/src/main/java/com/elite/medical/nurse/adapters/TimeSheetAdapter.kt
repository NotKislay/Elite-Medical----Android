package com.elite.medical.nurse.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.databinding.CustomListItemBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.TimesheetDataModel
import com.elite.medical.retrofit.responsemodel.nurse.home.NurseTimeSheetModel

class TimeSheetAdapter(private val sheetItems: List<NurseTimeSheetModel.Timesheet>) :
    RecyclerView.Adapter<TimeSheetAdapter.ModelViewHolder>() {

    inner class ModelViewHolder(binding: CustomListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val label1 = binding.label1
        val label2 = binding.label2
        val label3 = binding.label3
        val label4 = binding.label4
        val label5 = binding.label5

        val row1 = binding.row1
        val row2 = binding.row2
        val row3 = binding.row3
        val row4 = binding.row4
        val row5 = binding.row5

        val date = binding.tv1
        val location = binding.tv2
        val clockIn = binding.tv3
        val clockOut = binding.tv4
        val attendance = binding.iv5

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = CustomListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val currentItem = sheetItems[position]

        holder.row1.visibility = View.VISIBLE
        holder.row2.visibility = View.VISIBLE
        holder.row3.visibility = View.VISIBLE
        holder.row4.visibility = View.VISIBLE
        holder.row5.visibility = View.VISIBLE

//        holder.date.textAlignment = View.TEXT_ALIGNMENT_CENTER
//        holder.location.textAlignment = View.TEXT_ALIGNMENT_CENTER
//        holder.clockIn.textAlignment = View.TEXT_ALIGNMENT_CENTER
//        holder.clockOut.textAlignment = View.TEXT_ALIGNMENT_CENTER
//        holder.attendance.textAlignment = View.TEXT_ALIGNMENT_CENTER

        holder.label1.text = "Date"
        holder.label2.text = "Location"
        holder.label3.text = "Clock In at"
        holder.label4.text = "Clock Out at"
        holder.label5.text = "Attendance"




        if(currentItem.location==""&& currentItem.clockinAt==""){
            holder.attendance.setImageResource(R.drawable.incorrect_icon)
        }
        else{
            holder.attendance.setImageResource(R.drawable.tick_mark_icon)
        }
        holder.date.text = currentItem.date.ifEmpty { "---" }
        holder.location.text = currentItem.location.ifEmpty { "---" }
        holder.clockIn.text = currentItem.clockinAt.ifEmpty { "---" }
        holder.clockOut.text = currentItem.clockoutAt.ifEmpty { "---" }
    }

    override fun getItemCount() = sheetItems.size
}


