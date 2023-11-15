package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses

import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.ClinicProfileDetailsModel
import com.elite.medical.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.http.GET

@Parcelize
data class EnrolledNursesModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("nurses") val nurses: List<Nurse>
): Parcelable
