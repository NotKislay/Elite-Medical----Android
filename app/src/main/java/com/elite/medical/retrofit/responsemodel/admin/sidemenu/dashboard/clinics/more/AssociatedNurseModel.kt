package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more

import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NursesDetailsFromAssociatedNurseModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ClinicReviewFromClinicReviewModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AssociatedNurseModel (
    @SerializedName("nurses") val nurses: List<NursesDetailsFromAssociatedNurseModel>,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)