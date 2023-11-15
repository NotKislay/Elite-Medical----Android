package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval


import com.google.gson.annotations.SerializedName

data class JobSearchApprovalModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseDetails")
    val nurseDetails: List<NurseDetailJobSearch>,
    @SerializedName("status")
    val status: String
)