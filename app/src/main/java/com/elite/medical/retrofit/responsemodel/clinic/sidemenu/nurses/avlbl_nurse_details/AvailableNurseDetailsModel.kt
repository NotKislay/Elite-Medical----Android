package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details

import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.EmploymentFromEnrNursByid
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.NurseFromEnrNurseDet
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.ReviewFromEnrNurseDet
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AvailableNurseDetailsModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("nurse") val nurse: NurseFromEnrNurseDet,
    @SerializedName("nurseReviews") val review: List<ClinicReviewModelFromAvlblNurseDetails>,
    @SerializedName("approvedJobs") val approvedJobs: List<String>

):Parcelable
