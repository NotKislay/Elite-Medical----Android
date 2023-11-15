package com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs


import com.google.gson.annotations.SerializedName

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
)