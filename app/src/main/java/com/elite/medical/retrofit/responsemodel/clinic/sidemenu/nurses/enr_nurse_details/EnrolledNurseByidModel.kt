package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EnrolledNurseByidModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("nurse") val nurse: NurseFromEnrNurseDet,
    @SerializedName("hasReviewed") val hasreviewed: String,
    @SerializedName("review") val review: List<ReviewFromEnrNurseDet>,
    @SerializedName("employment") val employment: EmploymentFromEnrNursByid
):Parcelable
