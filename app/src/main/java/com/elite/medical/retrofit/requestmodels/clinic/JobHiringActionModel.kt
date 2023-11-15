package com.elite.medical.retrofit.requestmodels.clinic

import com.google.gson.annotations.SerializedName

data class JobHiringActionModel(
    @SerializedName("nurseId") val nurseId: String,
    @SerializedName("clinicId") val clinicId: String,
    @SerializedName("jobId") val jobId: String,
    @SerializedName("clinicHireAction") val clinicHireAction: String,
)
