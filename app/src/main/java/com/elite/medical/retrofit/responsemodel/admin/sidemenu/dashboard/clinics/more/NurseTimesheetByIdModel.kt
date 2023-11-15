package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NurseTimesheetById(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("Nurse Name") val nurseName: String,
    @SerializedName("Nurse ID") val nurseId: Int,
    @SerializedName("Clinic Name") val clinicName: String,
    @SerializedName("Employee ID") val employeeId: Int,
    @SerializedName("newTimesheets ") val newTimesheetDataModels: List<TimesheetDataModel>
):Parcelable

