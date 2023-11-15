package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employment(
    @SerializedName("action")
    val action: String,
    @SerializedName("clinic_register_id")
    val clinicRegisterId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("emp_end")
    val empEnd: String,
    @SerializedName("emp_start")
    val empStart: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job_id")
    val jobId: String,
    @SerializedName("nurse_register_id")
    val nurseRegisterId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("termination_date")
    val terminationDate: String,
    @SerializedName("trial")
    val trial: String,
    @SerializedName("trial_end")
    val trialEnd: String,
    @SerializedName("trial_start")
    val trialStart: String,
    @SerializedName("updated_at")
    val updatedAt: String
):Parcelable