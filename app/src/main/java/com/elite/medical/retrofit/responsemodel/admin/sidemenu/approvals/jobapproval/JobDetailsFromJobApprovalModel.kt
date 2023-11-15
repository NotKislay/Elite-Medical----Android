package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobapproval

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobDetailsFromJobApprovalModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("clinic_register_id")
    val clinic_register_id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("engage_from")
    val engage_from: String,
    @SerializedName("engage_to")
    val engage_to: String,
    @SerializedName("locations")
    val locations: List<String>,
    @SerializedName("vacancy")
    val vacancy: String,
    @SerializedName("status")
    val approvalStatus: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("clinic_name")
    val clinic_name: String,
    @SerializedName("clinic_type")
    val clinic_type: String
) : Parcelable