package com.elite.medical.retrofit.responsemodel.clinic.dashboard


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClinicDashboardModel(
    @SerializedName("activeJobCount")
    val activeJobCount: Int,
    @SerializedName("activeNurses")
    val activeNurses: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseApplicants")
    val nurseApplicants: List<NurseApplicant>,
    @SerializedName("status")
    val status: String,
    @SerializedName("topNurses")
    val topNurses: List<TopNurse>
) : Parcelable