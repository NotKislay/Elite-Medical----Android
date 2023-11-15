package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval

import com.google.gson.annotations.SerializedName

data class EmploymentApprovalModel(
    @SerializedName("employment_approvals") val employmentDetails: List<EmploymentDetailsFromEmploymentApprovalModel>,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)

