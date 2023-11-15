package com.elite.medical.retrofit.responsemodel.nurse.clinics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class EnrolledClinicsModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("clinics") val clinics: List<Clinics>
): Parcelable
