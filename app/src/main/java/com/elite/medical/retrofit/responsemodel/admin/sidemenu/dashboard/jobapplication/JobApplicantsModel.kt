package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobApplicantsModel(
    @SerializedName("allJobApplicants ")
    val allJobApplicants: List<AllJobApplicant>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) : Parcelable