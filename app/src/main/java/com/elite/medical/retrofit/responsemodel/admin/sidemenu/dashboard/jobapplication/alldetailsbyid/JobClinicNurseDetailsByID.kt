package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class JobClinicNurseDetailsByID(
    @SerializedName("clinic")
    val clinic: Clinic,
    @SerializedName("job")
    val job: Job,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurse")
    val nurse: Nurse,
    @SerializedName("status")
    val status: String
) : Parcelable