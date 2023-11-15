package com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs


import com.google.gson.annotations.SerializedName

data class JobList(
    @SerializedName("jobLocations")
    val jobLocations: List<String>,
    @SerializedName("jobTypes")
    val jobTypes: List<String>,
    @SerializedName("jobs")
    val jobs: List<Job>,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseDetail")
    val nurseDetail: NurseDetail,
    @SerializedName("status")
    val status: String
)

data class JobDetailModel(
    @SerializedName("job")
    val jobs: Job,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)