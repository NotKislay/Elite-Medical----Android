package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval

import com.google.gson.annotations.SerializedName

data class ClinicApprovalModel(
    @SerializedName("clinic_approvals") val clinicApprovals: List<ClinicDetailsFromClinicApprovalModel>,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)