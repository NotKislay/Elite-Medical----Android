package com.elite.medical.admin.adapters.sidemenu.dashboard
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.admin.ui.sidemenu.dashboard.clinics.more.ActivityNurseDetailsFromAssocNurse
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NursesDetailsFromAssociatedNurseModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ReviewDetails

class AssocNursesAdapter(private val nurseDet: List<NursesDetailsFromAssociatedNurseModel>, private val context: Context) :
    RecyclerView.Adapter<AssocNursesAdapter.ModelViewHolder>() {

    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namecontent: TextView = itemView.findViewById(R.id.assoc_nurse_name)
        val licensecontent: TextView = itemView.findViewById(R.id.assoc_nurse_license_type)
        val expirystatus: TextView = itemView.findViewById(R.id.assoc_nurse_expiry)
        val status: TextView = itemView.findViewById(R.id.assoc_nurse_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_assoc_nurse, parent, false)
        return ModelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val currentItem = nurseDet[position]
        holder.namecontent.text = "Name: "+currentItem.name
        holder.licensecontent.text= "License Type: "+currentItem.licenseType
        holder.expirystatus.text= "License Expiry: "+currentItem.licenseExpiry
        holder.status.text= "Schedule Status: ${currentItem.schedule} at ${currentItem.scheduleTime} "
        holder.itemView.setOnClickListener {

            val nurseDetails = NursesDetailsFromAssociatedNurseModel(
                id = currentItem.id,
                userId = currentItem.userId,
                name = currentItem.name,
                mobile = currentItem.mobile,
                email = currentItem.email,
                dob = currentItem.dob,
                address = currentItem.address,
                city = currentItem.city,
                state = currentItem.state,
                zip = currentItem.zip,
                usImmgStatus = currentItem.usImmgStatus,
                nclexStatus = currentItem.nclexStatus,
                cgfnsStatus = currentItem.cgfnsStatus,
                licenseType = currentItem.licenseType,
                nurseLicense = currentItem.nurseLicense,
                licenseIssue = currentItem.licenseIssue,
                licenseExpiry = currentItem.licenseExpiry,
                experience = currentItem.experience,
                speciality = currentItem.speciality,
                scheduleStatus = currentItem.scheduleStatus,
                schedule = currentItem.schedule,
                scheduleTime = currentItem.scheduleTime,
                approvalStatus = currentItem.approvalStatus,
                createdAt = currentItem.createdAt,
                updatedAt = currentItem.updatedAt,
                laravelThroughKey = currentItem.laravelThroughKey,
                shift = currentItem.shift,
                hasReviews = currentItem.hasReviews, // Replace with the appropriate value
                review = currentItem.review.map { ReviewDetails("","","",it.rating,"",it.comment) }
            )



            val intent = Intent(context, ActivityNurseDetailsFromAssocNurse::class.java)
            intent.putExtra("specific_nurse_details", nurseDetails)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = nurseDet.size
}

