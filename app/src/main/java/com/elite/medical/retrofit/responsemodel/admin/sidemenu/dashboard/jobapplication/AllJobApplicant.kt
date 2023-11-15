package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllJobApplicant(
    @SerializedName("clinic_name")
    val clinicName: String?,
    @SerializedName("job_created_at")
    val jobCreatedAt: String?,
    @SerializedName("job_id")
    val jobId: Int,
    @SerializedName("job_title")
    val jobTitle: String?,
    @SerializedName("job_type")
    val jobType: String?,
    @SerializedName("nurses")
    val nurses: ArrayList<NurseFromApprovedJobApplication>?
) : Parcelable