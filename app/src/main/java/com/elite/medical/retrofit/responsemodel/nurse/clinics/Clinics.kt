package com.elite.medical.retrofit.responsemodel.nurse.clinics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Clinics(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("mobile") val mobile: String,
    @SerializedName("email") val email: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("zip") val zip: String,
    @SerializedName("clinic_type") val clinicType: String,
    @SerializedName("clinic_license") val clinicLicense: String,
    @SerializedName("vat_no") val vatNo: String,
    @SerializedName("cst_no") val cstNo: String,
    @SerializedName("service_tax_no") val serviceTaxNo: String,
    @SerializedName("uin_no") val uinNo: String,
    @SerializedName("declaration") val declaration: String,
    @SerializedName("locations") val locations: List<String>,
    @SerializedName("approval_status") val approvalStatus: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("laravel_through_key") val laravelThroughKey: String
): Parcelable
