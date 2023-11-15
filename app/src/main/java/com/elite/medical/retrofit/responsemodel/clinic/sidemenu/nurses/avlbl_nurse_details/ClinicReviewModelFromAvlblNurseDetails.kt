package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details

import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.ClinicRegisterFromReviewEnrNurseDet
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ClinicReviewModelFromAvlblNurseDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("nurse_register_id") val nurseRegisterId: String,
    @SerializedName("clinic_register_id") val clinicRegisterId: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("comment") val comment: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("formatted_date") val formatteddate: String,
): Parcelable
