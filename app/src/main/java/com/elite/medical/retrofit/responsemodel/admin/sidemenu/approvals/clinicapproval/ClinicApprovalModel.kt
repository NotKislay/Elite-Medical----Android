package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ClinicApprovalModel(
    @SerializedName("clinic_approvals")
    val clinicApprovals: List<ClinicApproval>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) : Parcelable {
    @Parcelize
    data class ClinicApproval(
        @SerializedName("address")
        val address: String,
        @SerializedName("approval_status")
        val approvalStatus: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("clinic_license")
        val clinicLicense: String,
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
    ) : Parcelable
}