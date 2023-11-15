package com.elite.medical.retrofit.responsemodel.auth


import com.google.gson.annotations.SerializedName

data class LogoutModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)