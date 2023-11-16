package com.elite.medical.retrofit.responsemodel.nurse.home


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DashboardDataNurseModel(
    @SerializedName("currentEmployeeId")
    val currentEmployeeId: Int,
    @SerializedName("latestClockType")
    val latestClockType: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("topClinics")
    val topClinics: List<TopClinic>
) : Parcelable {
    @Parcelize
    data class TopClinic(
        @SerializedName("address")
        val address: String,
        @SerializedName("approval_status")
        val approvalStatus: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("clinic_license")
        val clinicLicense: String,
        @SerializedName("clinic_review")
        val clinicReview: List<ClinicReview>,
        @SerializedName("clinic_type")
        val clinicType: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("cst_no")
        val cstNo: String,
        @SerializedName("declaration")
        val declaration: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("locations")
        val locations: List<String>,
        @SerializedName("mobile")
        val mobile: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("service_tax_no")
        val serviceTaxNo: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("uin_no")
        val uinNo: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("vat_no")
        val vatNo: String,
        @SerializedName("zip")
        val zip: String
    ) : Parcelable {
        @Parcelize
        data class ClinicReview(
            @SerializedName("clinic_register_id")
            val clinicRegisterId: String,
            @SerializedName("comment")
            val comment: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("nurse_register_id")
            val nurseRegisterId: String,
            @SerializedName("rating")
            val rating: String,
            @SerializedName("updated_at")
            val updatedAt: String
        ) : Parcelable
    }
}