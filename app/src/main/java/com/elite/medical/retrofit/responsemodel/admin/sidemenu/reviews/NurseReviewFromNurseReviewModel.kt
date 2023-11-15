package com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews


import com.google.gson.annotations.SerializedName

data class NurseReviewFromNurseReviewModel(
    @SerializedName("clinic_name")
    val clinicName: String,
    @SerializedName("clinic_register_id")
    val clinicRegisterId: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("formatted_date")
    val formattedDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nurse_name")
    val nurseName: String,
    @SerializedName("nurse_register_id")
    val nurseRegisterId: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("time_ago")
    val timeAgo: String,
    @SerializedName("updated_at")
    val updatedAt: String
)