package com.elite.medical.retrofit.responsemodel.admin.dashboard

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDetailsModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("user") val user: UserDetails,

): Parcelable
