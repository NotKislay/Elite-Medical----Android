package com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SearchJobsModel(
    @SerializedName("jobLocations")
    val jobLocations: List<String>,
    @SerializedName("jobTypes")
    val jobTypes: List<String>,
    @SerializedName("jobs")
    val jobs: List<Job>,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseDetail")
    val nurseDetail: NurseDetail,
    @SerializedName("status")
    val status: String
) : Parcelable {
    @Parcelize
    data class Job(
        @SerializedName("applied")
        val applied: List<String>,
        @SerializedName("clinic_name")
        val clinicName: String,
        @SerializedName("clinic_rating")
        val clinicRating: String,
        @SerializedName("clinic_register_id")
        val clinicRegisterId: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("engage_from")
        val engageFrom: String,
        @SerializedName("engage_to")
        val engageTo: String,
        @SerializedName("hired")
        val hired: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("locations")
        val locations: List<String>,
        @SerializedName("nurse_applied")
        val nurseApplied: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("vacancy")
        val vacancy: String
    ) : Parcelable

    @Parcelize
    data class NurseDetail(
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
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("nurse_register_id")
        val nurseRegisterId: String,
        @SerializedName("updated_at")
        val updatedAt: String
    ) : Parcelable
}