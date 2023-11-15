package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs


import com.google.gson.annotations.SerializedName

data class JobRelatedDetailsModel(
    @SerializedName("job")
    val job: JobX,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurses")
    val nurses: List<Nurse>,
    @SerializedName("status")
    val status: String
)