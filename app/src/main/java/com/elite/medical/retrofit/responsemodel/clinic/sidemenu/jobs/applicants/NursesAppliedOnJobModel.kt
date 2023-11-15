package com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants


import com.google.gson.annotations.SerializedName

data class NursesAppliedOnJobModel(
    @SerializedName("job")
    val job: Job,
    @SerializedName("message")
    val message: String,
    @SerializedName("nurses")
    val nurses: List<Nurse>,
    @SerializedName("status")
    val status: String
) {
    data class Job(
        @SerializedName("applied")
        val applied: List<String>,
        @SerializedName("clinic_register")
        val clinicRegister: ClinicRegister,
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
        @SerializedName("formatted_created_at")
        val formattedCreatedAt: String,
        @SerializedName("hired")
        val hired: List<Any>,
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
    ) {
        data class ClinicRegister(
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
        )
    }

    data class Nurse(
        @SerializedName("address")
        val address: String,
        @SerializedName("approval_status")
        val approvalStatus: String,
        @SerializedName("availability")
        val availability: String,
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
    )
}