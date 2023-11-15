package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses

import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.Job
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class EmpDetailsInNurseById (
    @SerializedName("id") val id: Int,
    @SerializedName("clinic_register_id") val clinic_register_id: String,
    @SerializedName("nurse_register_id") val nurse_register_id: String,
    @SerializedName("job_id") val jobId: String,
    @SerializedName("action") val requestType: String,
    @SerializedName("status") val approvalStatus: String,
    @SerializedName("trial") val trial: String,
    @SerializedName("trial_start") val trialStart: String,
    @SerializedName("trial_end") val trialEnd: String,
    @SerializedName("emp_start") val empStart: String,
    @SerializedName("emp_end") val empEnd: String,
    @SerializedName("termination_date") val terminationDate: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("clinic_name") val clinicName: String,
    @SerializedName("job_title") val jobTitle: String,
    @SerializedName("clinic_register") val clinicRegister: ClinicDetailsFromClinicApprovalModel,
    @SerializedName("job") val job: Job
):Parcelable