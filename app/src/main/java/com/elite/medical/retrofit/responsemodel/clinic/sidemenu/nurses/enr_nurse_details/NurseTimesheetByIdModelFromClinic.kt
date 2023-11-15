package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details

import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.TimesheetDataModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class NurseTimesheetByIdModelFromClinic(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("Nurse Name") val nurseName: String,
    @SerializedName("Nurse ID") val nurseId: Int,
    @SerializedName("Clinic Name") val clinicName: String,
    @SerializedName("Employee ID") val employeeId: Int,
    @SerializedName("timesheets") val TimesheetDataModels: List<TimesheetDataModel>
):Parcelable
