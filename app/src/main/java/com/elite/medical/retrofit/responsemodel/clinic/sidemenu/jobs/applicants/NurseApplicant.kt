package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants


import com.google.gson.annotations.SerializedName

data class NurseApplicant(
    @SerializedName("job_created_at")
    val jobCreatedAt: String,
    @SerializedName("job_id")
    val jobId: Int,
    @SerializedName("job_title")
    val jobTitle: String,
    @SerializedName("job_type")
    val jobType: String,
    @SerializedName("nurses")
    val nurses: List<Nurse>
)