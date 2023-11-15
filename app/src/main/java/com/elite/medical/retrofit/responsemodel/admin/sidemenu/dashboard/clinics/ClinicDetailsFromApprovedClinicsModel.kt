package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics

import android.os.Parcel
import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.ClinicReviewsFromClinicDetailsModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ClinicDetailsFromApprovedClinicsModel(
     @SerializedName("name") val name: String,
     @SerializedName("email") val email: String,
     @SerializedName("clinic_type") val clinictype: String,
     @SerializedName("mobile") val contactno: Long,
     @SerializedName("address") val clinicaddress: String,
     @SerializedName("vat_no") val vattinno: String,
     @SerializedName("cst_no") val cstno: String,
     @SerializedName("service_tax_no") val servicetaxno: String,
     @SerializedName("uin_no") val clinicuin: String,
     @SerializedName("declaration") val declaration: String,
     @SerializedName("user_id") val userId: String,
     @SerializedName("id") val id: Int,
     @SerializedName("reviews") val reviewlist: List<ClinicReviewsFromClinicDetailsModel>
): Parcelable

