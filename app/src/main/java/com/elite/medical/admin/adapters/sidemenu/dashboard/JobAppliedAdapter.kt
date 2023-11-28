package com.elite.medical.admin.adapters.sidemenu.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.dashboard.jobs.ApprovedJobDetailsByID
import com.elite.medical.admin.ui.sidemenu.dashboard.nurses.nursedetailsfromapproved.ActivityJobApplied
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.Job

class JobAppliedAdapter(private val itemList: ArrayList<Job>,
                        val context: ActivityJobApplied,
                        private val detailed: Boolean) :
    RecyclerView.Adapter<JobAppliedAdapter.ModelViewHolder>() {
    inner class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val layout = view.findViewById(R.id.LL_job_item) as LinearLayout
        val jobTitle: TextView = view.findViewById(R.id.job_title)
        val jobType: TextView = view.findViewById(R.id.job_type)
        val approvalStatus: TextView = view.findViewById(R.id.approval_status)
        val engageDate: TextView = view.findViewById(R.id.engage_date)

        var arrow = view.findViewById(R.id.item_arrow) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_jobapplied, parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {

        val engageFromTo=itemList[position].engageFrom +" to "+ itemList[position].engageTo

        holder.jobTitle.text = "Title: ${ itemList[position].title }"
        holder.jobType.text = "Type: ${ itemList[position].type }"
        holder.engageDate.text = "Engage date: $engageFromTo"
        holder.approvalStatus.text= "Status: ${ itemList[position].approvalStatus }"

        if (detailed) {
            holder.layout.setOnClickListener {
                val intent = Intent(context, ApprovedJobDetailsByID::class.java)
                intent.putExtra("jobID", itemList[position].id)
                context.startActivity(intent)
            }
        }
        if (!detailed) {
            holder.arrow.visibility=View.GONE
        }
    }
}