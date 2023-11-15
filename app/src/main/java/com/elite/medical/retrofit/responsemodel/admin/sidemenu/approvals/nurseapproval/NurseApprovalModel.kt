package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval

import com.google.gson.annotations.SerializedName

data class NurseApprovalModel(
    @SerializedName("nurse_approvals") val approvalNurseDetails: List<NurseDetailsFromNurseApprovalModel>, // ApprovalNurseDetails
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)


