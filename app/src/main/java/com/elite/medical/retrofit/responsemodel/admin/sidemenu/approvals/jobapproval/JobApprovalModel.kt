package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobapproval

import com.google.gson.annotations.SerializedName

data class JobApprovalModel (
    @SerializedName("job_approvals") val jobApprovals: List<JobDetailsFromJobApprovalModel>, // ApprovalNurseDetails
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)