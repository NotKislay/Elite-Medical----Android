package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClinicJobLocationsModel(
    @SerializedName("clinicLocations")
    val clinicLocations: List<String>
) : Parcelable