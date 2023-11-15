package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs


import com.google.gson.annotations.SerializedName

data class ApprovedJobListModel(
    @SerializedName("jobs ")
    val jobs: List<Job>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)