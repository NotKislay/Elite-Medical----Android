package com.elite.medical.retrofit.responsemodel.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: String?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("token")
    val token: String?,
)

