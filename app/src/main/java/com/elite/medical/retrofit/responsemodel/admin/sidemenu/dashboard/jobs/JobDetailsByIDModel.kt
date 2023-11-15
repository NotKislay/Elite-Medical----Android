package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs


import com.google.gson.annotations.SerializedName

data class JobDetailsByIDModel(
    @SerializedName("job ")
    val job: JobX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)