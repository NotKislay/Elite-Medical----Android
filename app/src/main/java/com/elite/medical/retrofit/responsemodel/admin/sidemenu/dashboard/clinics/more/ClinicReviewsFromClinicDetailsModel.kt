package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClinicReviewsFromClinicDetailsModel (
    @SerializedName("id") val id: Int,
    @SerializedName("clinic_register_id") val clinicRegisterId: String,
    @SerializedName("nurse_register_id") val nurseRegisterId: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("comment") val comment: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("nurse_name") val nurseName: String?,
    @SerializedName("formatted_date") val formattedDate: String
): Parcelable