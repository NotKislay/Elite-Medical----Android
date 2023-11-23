package com.elite.medical.retrofit.testing


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ImageUploadModel(
    @SerializedName("path")
    val path: String
) : Parcelable