package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval


import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NurseDetailJobSearch(
    @SerializedName("availability")
    val availability: String,
    @SerializedName("availability_approval")
    val availabilityApproval: String,
    @SerializedName("availablility_request")
    val availablilityRequest: String,
    @SerializedName("booked_from")
    val bookedFrom: String,
    @SerializedName("booked_to")
    val bookedTo: String,
    @SerializedName("clinic")
    val clinic: ClinicDetailsFromClinicApprovalModel,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("employment")
    val employment: Employment,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job")
    val job: Job,
    @SerializedName("nurse")
    val nurse: NurseDetailsFromNurseApprovalModel,
    @SerializedName("nurse_register_id")
    val nurseRegisterId: String,
    @SerializedName("updated_at")
    val updatedAt: String
):Parcelable