package com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews


import com.google.gson.annotations.SerializedName

data class ClinicReviewModel(
    @SerializedName("clinicReviews ")
    val clinicReviews: List<ClinicReviewFromClinicReviewModel>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)