package com.elite.medical.retrofit.responsemodel.admin.dashboard


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class AdminDashboardModel(
    @SerializedName("activeJobs")
    val activeJobs: List<ActiveJob>,
    @SerializedName("clinicReviews")
    val clinicReviews: List<ClinicReview>,
    @SerializedName("clinics")
    val clinics: List<Clinic>,
    @SerializedName("count_activeJobs")
    val countActiveJobs: Int,
    @SerializedName("count_clinics")
    val countClinics: Int,
    @SerializedName("count_nurses")
    val countNurses: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nurseReviews")
    val nurseReviews: List<NurseReview>,
    @SerializedName("nurses")
    val nurses: List<Nurse>,
    @SerializedName("status")
    val status: String
) : Parcelable {
    @Parcelize
    data class ActiveJob(
        @SerializedName("applied")
        val applied: List<String>,
        @SerializedName("clinic_register_id")
        val clinicRegisterId: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("engage_from")
        val engageFrom: String,
        @SerializedName("engage_to")
        val engageTo: String,
        @SerializedName("hired")
        val hired: List<String>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("locations")
        val locations: List<String>,
        @SerializedName("status")
        val status: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("vacancy")
        val vacancy: String
    ) : Parcelable

    @Parcelize
    data class ClinicReview(
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
        @SerializedName("nurse_name")
        val nurseName: String,
        @SerializedName("nurse_register_id")
        val nurseRegisterId: String,
        @SerializedName("rating")
        val rating: String,
        @SerializedName("time_ago")
        val timeAgo: String,
        @SerializedName("updated_at")
        val updatedAt: String
    ) : Parcelable

    @Parcelize
    data class Clinic(
        @SerializedName("address")
        val address: String,
        @SerializedName("approval_status")
        val approvalStatus: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("clinic_license")
        val clinicLicense: String,
        @SerializedName("clinic_type")
        val clinicType: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("cst_no")
        val cstNo: String,
        @SerializedName("declaration")
        val declaration: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("locations")
        val locations: List<String>,
        @SerializedName("mobile")
        val mobile: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("service_tax_no")
        val serviceTaxNo: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("uin_no")
        val uinNo: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("vat_no")
        val vatNo: String,
        @SerializedName("zip")
        val zip: String
    ) : Parcelable

    @Parcelize
    data class NurseReview(
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
        @SerializedName("nurse_name")
        val nurseName: String,
        @SerializedName("nurse_register_id")
        val nurseRegisterId: String,
        @SerializedName("rating")
        val rating: String,
        @SerializedName("time_ago")
        val timeAgo: String,
        @SerializedName("updated_at")
        val updatedAt: String
    ) : Parcelable

    @Parcelize
    data class Nurse(
        @SerializedName("address")
        val address: String,
        @SerializedName("approval_status")
        val approvalStatus: String,
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
    ) : Parcelable
}