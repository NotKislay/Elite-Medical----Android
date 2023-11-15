package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ClinicDetailsFromClinicApprovalModel(
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
) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(approvalStatus)
        parcel.writeString(city)
        parcel.writeString(clinicLicense)
        parcel.writeString(clinicType)
        parcel.writeString(createdAt)
        parcel.writeString(cstNo)
        parcel.writeString(declaration)
        parcel.writeString(email)
        parcel.writeInt(id)
        parcel.writeStringList(locations)
        parcel.writeString(mobile)
        parcel.writeString(name)
        parcel.writeString(serviceTaxNo)
        parcel.writeString(state)
        parcel.writeString(uinNo)
        parcel.writeString(updatedAt)
        parcel.writeString(userId)
        parcel.writeString(vatNo)
        parcel.writeString(zip)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClinicDetailsFromClinicApprovalModel> {
        override fun createFromParcel(parcel: Parcel): ClinicDetailsFromClinicApprovalModel {
            return ClinicDetailsFromClinicApprovalModel(
                address = parcel.readString() ?: "",
                approvalStatus = parcel.readString() ?: "",
                city = parcel.readString() ?: "",
                clinicLicense = parcel.readString() ?: "",
                clinicType = parcel.readString() ?: "",
                createdAt = parcel.readString() ?: "",
                cstNo = parcel.readString() ?: "",
                declaration = parcel.readString() ?: "",
                email = parcel.readString() ?: "",
                id = parcel.readInt(),
                locations = parcel.createStringArrayList() ?: emptyList(),
                mobile = parcel.readString() ?: "",
                name = parcel.readString() ?: "",
                serviceTaxNo = parcel.readString() ?: "",
                state = parcel.readString() ?: "",
                uinNo = parcel.readString() ?: "",
                updatedAt = parcel.readString() ?: "",
                userId = parcel.readString() ?: "",
                vatNo = parcel.readString() ?: "",
                zip = parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<ClinicDetailsFromClinicApprovalModel?> {
            return arrayOfNulls(size)
        }
    }
}
