package com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews

import com.google.gson.annotations.SerializedName

data class NavigationNurseReviews(
    @SerializedName("nurseReviews ") val nursereviews: List<ReviewDetails>,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)