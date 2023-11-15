package com.elite.medical.retrofit.responsemodel.admin.dashboard


import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdminDashboardModel(
    @SerializedName("activeJobs")
    val activeJobs: List<ActiveJob>,
    @SerializedName("clinicReviews")
    val clinicReviews: List<ClinicReview>,
    @SerializedName("clinics")
    val clinics: List<ClinicDetailsFromClinicApprovalModel>,
    @SerializedName("message")
    val message: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nurseReviews")
    val nurseReviews: List<NurseReview>,
    @SerializedName("nurses")
    val nurses: List<NurseDetailsFromNurseApprovalModel>,
    @SerializedName("status")
    val status: String
) : Parcelable