package com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval

import android.os.Parcel
import android.os.Parcelable
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.Job
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel
import com.google.gson.annotations.SerializedName

data class EmploymentDetailsFromEmploymentApprovalModel(
    @SerializedName("id") val id: Int,
    @SerializedName("clinic_register_id") val clinic_register_id: String,
    @SerializedName("nurse_register_id") val nurse_register_id: String,
    @SerializedName("job_id") val job_id: String,
    @SerializedName("action") val request_type: String,
    @SerializedName("status") val approvalStatus: String,
    @SerializedName("trial") val trial: String,
    @SerializedName("trial_start") val trialStart: String,
    @SerializedName("trial_end") val trialEnd: String,
    @SerializedName("emp_start") val empStart: String,
    @SerializedName("emp_end") val empEnd: String,
    @SerializedName("termination_date") val terminationDate: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("job") val job: Job,
    @SerializedName("clinicRegister") val clinic: ClinicDetailsFromClinicApprovalModel,
    @SerializedName("nurseRegister") val nurse: NurseDetailsFromNurseApprovalModel
) : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(clinic_register_id)
        parcel.writeString(nurse_register_id)
        parcel.writeString(job_id)
        parcel.writeString(request_type)
        parcel.writeString(approvalStatus)
        parcel.writeString(trial)
        parcel.writeString(trialStart)
        parcel.writeString(trialEnd)
        parcel.writeString(empStart)
        parcel.writeString(empEnd)
        parcel.writeString(terminationDate)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeParcelable(job, flags)
        parcel.writeParcelable(clinic, flags)
        parcel.writeParcelable(nurse, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmploymentDetailsFromEmploymentApprovalModel> {
        override fun createFromParcel(parcel: Parcel): EmploymentDetailsFromEmploymentApprovalModel {
            return EmploymentDetailsFromEmploymentApprovalModel(
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readParcelable(Job::class.java.classLoader)!!,
                parcel.readParcelable(ClinicDetailsFromClinicApprovalModel::class.java.classLoader)!!,
                parcel.readParcelable(NurseDetailsFromNurseApprovalModel::class.java.classLoader)!!
            )
        }

        override fun newArray(size: Int): Array<EmploymentDetailsFromEmploymentApprovalModel?> {
            return arrayOfNulls(size)
        }
    }
}
