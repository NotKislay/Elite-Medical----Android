package com.elite.medical.retrofit.responsemodel

import com.google.gson.annotations.SerializedName

data class GenericSuccessErrorModel(
    @SerializedName("status")
    val success: String,

    @SerializedName("message")
    val message: String,

)

