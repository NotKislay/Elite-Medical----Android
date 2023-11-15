package com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews

import com.google.gson.annotations.SerializedName

data class NavigationClinicReviews(
    @SerializedName("nurseReviews ") val clinicreviews: List<ReviewDetails>,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)