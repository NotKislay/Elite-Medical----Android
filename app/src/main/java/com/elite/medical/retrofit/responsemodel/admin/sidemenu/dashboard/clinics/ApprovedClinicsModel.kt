package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics

import com.google.gson.annotations.SerializedName

data class ApprovedClinicsModel (
    @SerializedName("clinics ") val clinics: List<ClinicDetailsFromApprovedClinicsModel>,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)