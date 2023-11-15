package com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs


import com.google.gson.annotations.SerializedName

data class AppliedJobDetailsModel(
    @SerializedName("job")
    val job: Job,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
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
        val hired: List<String>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("locations")
        val locations: List<String>,
        @SerializedName("nurse_status")
        val nurseStatus: String,
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
    )
}