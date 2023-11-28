package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ClinicJobApplicantsModel(
    @SerializedName("clinic_id")
    val clinicId: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurseApplicants")
    val nurseApplicants: List<NurseApplicant>,
    @SerializedName("status")
    val status: String
) : Parcelable {
    @Parcelize
    data class NurseApplicant(
        @SerializedName("job_created_at")
        val jobCreatedAt: String,
        @SerializedName("job_id")
        val jobId: Int,
        @SerializedName("job_title")
        val jobTitle: String,
        @SerializedName("job_type")
        val jobType: String,
        @SerializedName("job_status")
        val jobStatus: String,
        @SerializedName("nurses")
        val nurses: List<Nurse>
    ) : Parcelable {
        @Parcelize
        data class Nurse(
            @SerializedName("address")
            val address: String,
            @SerializedName("approval_status")
            val approvalStatus: String,
            @SerializedName("availability")
            val availability: String,
            @SerializedName("button_status")
            val buttonStatus: ButtonStatus,
            @SerializedName("cgfns_status")
            val cgfnsStatus: String,
            @SerializedName("city")
            val city: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("dob")
            val dob: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("experience")
            val experience: String,
            @SerializedName("hiring_status")
            val hiringStatus: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("license_expiry")
            val licenseExpiry: String,
            @SerializedName("license_issue")
            val licenseIssue: String,
            @SerializedName("license_type")
            val licenseType: String,
            @SerializedName("mobile")
            val mobile: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("nclex_status")
            val nclexStatus: String,
            @SerializedName("nurse_license")
            val nurseLicense: String,
            @SerializedName("reviews")
            val reviews: List<Review>,
            @SerializedName("schedule")
            val schedule: String,
            @SerializedName("schedule_status")
            val scheduleStatus: String,
            @SerializedName("schedule_time")
            val scheduleTime: String,
            @SerializedName("speciality")
            val speciality: String,
            @SerializedName("state")
            val state: String,
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("us_immg_status")
            val usImmgStatus: String,
            @SerializedName("user_id")
            val userId: String,
            @SerializedName("zip")
            val zip: String
        ) : Parcelable {
            @Parcelize
            data class ButtonStatus(
                @SerializedName("employment")
                val employment: Employment,
                @SerializedName("status")
                val status: Boolean
            ) : Parcelable {
                @Parcelize
                data class Employment(
                    @SerializedName("action")
                    val action: String,
                    @SerializedName("clinic_register_id")
                    val clinicRegisterId: String,
                    @SerializedName("created_at")
                    val createdAt: String,
                    @SerializedName("emp_end")
                    val empEnd: String,
                    @SerializedName("emp_start")
                    val empStart: String,
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("job_id")
                    val jobId: String,
                    @SerializedName("nurse_register_id")
                    val nurseRegisterId: String,
                    @SerializedName("status")
                    val status: String,
                    @SerializedName("termination_date")
                    val terminationDate: String,
                    @SerializedName("trial")
                    val trial: String,
                    @SerializedName("trial_end")
                    val trialEnd: String,
                    @SerializedName("trial_start")
                    val trialStart: String,
                    @SerializedName("updated_at")
                    val updatedAt: String
                ) : Parcelable
            }

            @Parcelize
            data class Review(
                @SerializedName("clinic_name")
                val clinicName: String,
                @SerializedName("clinic_register_id")
                val clinicRegisterId: String,
                @SerializedName("comment")
                val comment: String,
                @SerializedName("created_at")
                val createdAt: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("nurse_register_id")
                val nurseRegisterId: String,
                @SerializedName("rating")
                val rating: String,
                @SerializedName("updated_at")
                val updatedAt: String
            ) : Parcelable
        }
    }
}