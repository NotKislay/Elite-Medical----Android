package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimesheetDataModel(
    @SerializedName("clinic_name")val clinic_name: String,
    @SerializedName("date")val date: String,
    @SerializedName("location") val location: String,
    @SerializedName("clockin_at") val clockin_at: String,
    @SerializedName("clockout_at") val clockout_at: String
): Parcelable
