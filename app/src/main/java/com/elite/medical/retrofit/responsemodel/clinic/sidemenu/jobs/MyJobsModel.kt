package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs


import com.google.gson.annotations.SerializedName

data class MyJobsModel(
    @SerializedName("jobs")
    val jobs: List<Job>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)