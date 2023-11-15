package com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews


import com.google.gson.annotations.SerializedName

data class NurseReviewModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseReviews ")
    val nurseReviews: List<NurseReviewFromNurseReviewModel>,
    @SerializedName("status")
    val status: String
)