package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses

import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.Job
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.ClinicReviewsFromClinicDetailsModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NurseByUserIdModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurse")
    val nurse: Nurse,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("appliedJobs")
    val appliedJobs: List<Job>,
    @SerializedName("nurseReviews")
    val nurseReviews: List<ClinicReviewsFromClinicDetailsModel>,
    @SerializedName("emp_details")
    val empDetails: List<EmpDetailsInNurseById>
): Parcelable