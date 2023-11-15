package com.elite.medical.nurse.adapters.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.databinding.RvItemGenericBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel
import com.elite.medical.retrofit.responsemodel.nurse.dashboard.notification.NotificationModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job
import java.text.SimpleDateFormat
import java.util.Locale

class NurseNotificationAdapter(
    private val list: List<NotificationModel.Notification>,
    private val context: Context
) :
    RecyclerView.Adapter<NurseNotificationAdapter.ViewHolder>() {


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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list.elementAt(position)

        holder.btnGoDeep.isVisible = false
        holder.row1.isVisible = true
        holder.row2.isVisible = true
        holder.row3.isVisible = true
        holder.row4.isVisible = false
        holder.row5.isVisible = false
        holder.row6.isVisible = false
        holder.row7.isVisible = false
        holder.row8.isVisible = false
        holder.row9.isVisible = false
        holder.row10.isVisible = false

        holder.label1.text = "Date"
        holder.label2.text = "Title"
        holder.label3.text = "Body"


        holder.tv2.text = item.title
        holder.tv3.text = item.body

        //date time slicing by changing the format
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val outputTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        var datetimecontent = ""
        try {
            val date= inputFormat.parse(item.createdAt)
            val formatteddate= outputDateFormat.format(date)
            val formattedtime= outputTimeFormat.format(date)
            datetimecontent = "$formatteddate $formattedtime"
        } catch (e : Exception){
            e.printStackTrace()
        }

        holder.tv1.text = datetimecontent

    }

    override fun getItemCount() = list.size
}

