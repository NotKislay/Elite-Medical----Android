package com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewDetails(
    @SerializedName("formatted_date") val formatted_date: String,
    @SerializedName("nurse_name") val nurse_name: String,
    @SerializedName("clinic_name") val clinic_name: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("time_ago") val time_ago: String,
    @SerializedName("comment") val comment: String,
): Parcelable