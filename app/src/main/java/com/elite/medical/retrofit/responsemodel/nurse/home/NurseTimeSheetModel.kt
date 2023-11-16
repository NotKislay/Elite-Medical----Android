package com.elite.medical.retrofit.responsemodel.nurse.home


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class NurseTimeSheetModel(
    @SerializedName("Clinic Name")
    val clinicName: String,
    @SerializedName("Employee ID")
    val employeeID: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("Nurse Name")
    val nurseName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("timesheets")
    val timesheets: List<Timesheet>
) : Parcelable {
    @Parcelize
    data class Timesheet(
        @SerializedName("clinic_name")
        val clinicName: String,
        @SerializedName("clockin_at")
        val clockinAt: String,
        @SerializedName("clockout_at")
        val clockoutAt: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("location")
        val location: String
    ) : Parcelable
}