package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NurseDetailsFromNurseApprovalModel(

    @SerializedName("address")
    val address: String,
    @SerializedName("approval_status")
    val approvalStatus: String,
    @SerializedName("cgfns_status")
    val cgfnsStatus: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("experience")
    val experience: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("license_expiry")
    val licenseExpiry: String,
    @SerializedName("license_issue")
    val licenseIssue: String,
    @SerializedName("license_type")
    val licenseType: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nclex_status")
    val nclexStatus: String,
    @SerializedName("nurse_license")
    val nurseLicense: String,
    @SerializedName("schedule")
    val schedule: String,
    @SerializedName("schedule_status")
    val scheduleStatus: String,
    @SerializedName("schedule_time")
    val scheduleTime: String,
    @SerializedName("speciality")
    val speciality: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("us_immg_status")
    val usImmgStatus: String,
    @SerializedName("user_id")
    val userId: String,

    @SerializedName("zip")
    val zip: String
) : Parcelable