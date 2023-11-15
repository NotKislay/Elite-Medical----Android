package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nurse(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("mobile") val mobile: String,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val dob: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("us_immg_status") val usImmgStatus: String,
    @SerializedName("nclex_status") val nclexStatus: String,
    @SerializedName("cgfns_status") val cgfnsStatus: String,
    @SerializedName("license_type") val licenseType: String,
    @SerializedName("nurse_license") val nurseLicense: String,
    @SerializedName("license_issue") val licenseIssue: String,
    @SerializedName("license_expiry") val licenseExpiry: String,
    @SerializedName("experience") val experience: String,
    @SerializedName("speciality") val speciality: String,
    @SerializedName("schedule_status") val scheduleStatus: String,
    @SerializedName("schedule") val schedule: String,
    @SerializedName("schedule_time") val scheduleTime: String,
    @SerializedName("approval_status") val approvalStatus: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
): Parcelable