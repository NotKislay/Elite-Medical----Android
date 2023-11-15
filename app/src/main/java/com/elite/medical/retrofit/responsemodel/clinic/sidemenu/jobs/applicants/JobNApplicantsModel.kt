package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants


import com.google.gson.annotations.SerializedName

data class JobNApplicantsModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseApplicants")
    val nurseApplicants: List<NurseApplicant>,
    @SerializedName("status")
    val status: String
)