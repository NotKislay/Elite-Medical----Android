package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class NurseDetailsNReviewsModel(
    @SerializedName("clinicId")
    val clinicId: Int,
    @SerializedName("jobId")
    val jobId: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurse")
    val nurse: Nurse,
    @SerializedName("status")
    val status: String
) : Parcelable {
    @Parcelize
    data class Nurse(
        @SerializedName("address")
        val address: String,
        @SerializedName("approval_status")
        val approvalStatus: String,
        @SerializedName("availability")
        val availability: String,
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
        @SerializedName("hiring_status")
        val hiringStatus: String,
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
        @SerializedName("nurse_review")
        val nurseReview: List<NurseReview>,
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
    ) : Parcelable {
        @Parcelize
        data class NurseReview(
            @SerializedName("clinic_name")
            val clinicName: String,
            @SerializedName("clinic_register_id")
            val clinicRegisterId: String,
            @SerializedName("comment")
            val comment: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("nurse_register_id")
            val nurseRegisterId: String,
            @SerializedName("rating")
            val rating: String,
            @SerializedName("updated_at")
            val updatedAt: String
        ) : Parcelable
    }
}