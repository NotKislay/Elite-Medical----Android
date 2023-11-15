package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses

import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class SearchNurseModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("nurses") val nurses: List<Nurse>,
    @SerializedName("cities") val cities: List<String>
): Parcelable
