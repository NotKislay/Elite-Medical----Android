package com.elite.medical.retrofit.responsemodel.nurse.clinics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ClinicDetailsModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("clinic") val clinic: Clinics,
    @SerializedName("hasReviewed") val hasReviewed: String,
    @SerializedName("review") val review: List<ReviewEnrolledClinic>,
    @SerializedName("employment") val employment: EmploymentDetails
): Parcelable
