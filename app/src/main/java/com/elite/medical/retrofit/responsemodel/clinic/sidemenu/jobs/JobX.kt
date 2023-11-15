package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs


import com.google.gson.annotations.SerializedName

data class JobX(
    @SerializedName("applied")
    val applied: List<String>,
    @SerializedName("clinic_register")
    val clinicRegister: ClinicRegister,
    @SerializedName("clinic_register_id")
    val clinicRegisterId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("engage_from")
    val engageFrom: String,
    @SerializedName("engage_to")
    val engageTo: String,
    @SerializedName("formatted_created_at")
    val formattedCreatedAt: String,
    @SerializedName("hired")
    val hired: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("locations")
    val locations: List<String>,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("vacancy")
    val vacancy: String
)