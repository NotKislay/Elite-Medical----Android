package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses


import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel
import com.google.gson.annotations.SerializedName

data class ApprovedNurseListModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("nurses ")
    val nurses: List<Nurse>,
    @SerializedName("status")
    val status: String
)