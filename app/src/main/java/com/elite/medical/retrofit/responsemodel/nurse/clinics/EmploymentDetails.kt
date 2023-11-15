package com.elite.medical.retrofit.responsemodel.nurse.clinics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class EmploymentDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("clinic_register_id") val clinicRegisterId: String,
    @SerializedName("nurse_register_id") val nurseRegisterId: String,
    @SerializedName("job_id") val jobId: String,
    @SerializedName("action") val action: String,
    @SerializedName("status") val status: String,
    @SerializedName("trial") val trial: String,
    @SerializedName("trial_start") val trialStart: String,
    @SerializedName("trial_end") val trialEnd: String,
    @SerializedName("emp_start") val empStart: String,
    @SerializedName("emp_end") val empEnd: String,
    @SerializedName("termination_date") val terminationDate: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("job_title") val jobTitle: String
): Parcelable
