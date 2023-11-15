package com.elite.medical.retrofit.requestmodels.clinic

import com.google.gson.annotations.SerializedName

data class PostJobRequestModel(
    @SerializedName("title") val title: String,
    @SerializedName("type") val jobType: String,
    @SerializedName("engage_from") val engage_from: String,
    @SerializedName("engage_to") val engage_to: String,
    @SerializedName("vacancy") val vacancy: String,
    @SerializedName("locations") val locations: String,
    @SerializedName("description") val description: String
)
