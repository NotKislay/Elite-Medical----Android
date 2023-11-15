package com.elite.medical.retrofit.responsemodel.clinic.dashboard


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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