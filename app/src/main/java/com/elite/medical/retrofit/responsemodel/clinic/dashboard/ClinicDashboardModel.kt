package com.elite.medical.retrofit.responsemodel.clinic.dashboard


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ClinicDashboardModel(
    @SerializedName("activeJobCount")
    val activeJobCount: Int,
    @SerializedName("activeNurses")
    val activeNurses: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseApplicants")
    val nurseApplicants: List<NurseApplicant>,
    @SerializedName("status")
    val status: String,
    @SerializedName("topNurses")
    val topNurses: List<TopNurse>
) : Parcelable {
    @Parcelize
    data class NurseApplicant(
        @SerializedName("job_created_at")
        val jobCreatedAt: String,
        @SerializedName("job_id")
        val jobId: Int,
        @SerializedName("job_title")
        val jobTitle: String,
        @SerializedName("job_type")
        val jobType: String,
        @SerializedName("nurses")
        val nurses: List<Nurse>
    ) : Parcelable {
        @Parcelize
        data class Nurse(
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
    }

    @Parcelize
    data class TopNurse(
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