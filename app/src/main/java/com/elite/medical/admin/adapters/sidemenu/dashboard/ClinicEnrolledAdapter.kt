package com.elite.medical.admin.adapters.sidemenu.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.dashboard.nurses.nursedetailsfromapproved.ActivityClinicEnrolled
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.EmpDetailsInNurseById

class ClinicEnrolledAdapter(private val itemList: ArrayList<EmpDetailsInNurseById>,
                            val context: ActivityClinicEnrolled ) :
    RecyclerView.Adapter<ClinicEnrolledAdapter.ModelViewHolder>() {
    inner class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val layout = view.findViewById(R.id.LL_clinic_enrolled) as LinearLayout
        val clinicName: TextView = view.findViewById(R.id.job_title)
        val jobTitle: TextView = view.findViewById(R.id.job_type)
        val empStart: TextView = view.findViewById(R.id.approval_status)
        val empEnd: TextView = view.findViewById(R.id.engage_date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_clinicenrolled, parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.clinicName.text = itemList[position].clinicName
        holder.jobTitle.text = itemList[position].jobTitle
        holder.empStart.text = itemList[position].empStart
        holder.empEnd.text= itemList[position].empEnd

    }
}