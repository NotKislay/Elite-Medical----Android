package com.elite.medical.retrofit

import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.EnrolledClinicsModel
import com.elite.medical.retrofit.responsemodel.nurse.dashboard.notification.NotificationModel
import com.elite.medical.retrofit.responsemodel.nurse.dashboard.profile.NurseProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.home.DashboardDataNurseModel
import com.elite.medical.retrofit.responsemodel.nurse.home.NurseTimeSheetModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobDetailModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobList
import com.elite.medical.utils.Constants
import com.elite.medical.utils.ConstantsNurse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface RetrofitInterfaceNurse {


    @GET(ConstantsNurse.DASHBOARD)
    fun getNurseDashboardData(): Call<DashboardDataNurseModel>

    @GET(ConstantsNurse.TIMESHEET)
    fun getTimesheet(): Call<NurseTimeSheetModel>

    @GET(ConstantsNurse.SEARCH_JOBS)
    fun searchJobs(): Call<JobList>

    @FormUrlEncoded
    @POST(ConstantsNurse.APPLY_FOR_JOB_BY_ID)
    fun applyJobByID(@Field("job_id") jobID: String): Call<GenericSuccessErrorModel>

    @GET("${ConstantsNurse.SEARCH_JOBS_BY_ID}/{id}")
    fun jobDetailsByID(@Path("id") id: String): Call<JobDetailModel>

    @POST(ConstantsNurse.REQUEST_SEARCH_JOBS)
    fun requestSearchJobs(): Call<GenericSuccessErrorModel>


    @GET(ConstantsNurse.APPLIED_JOBS)
    fun appliedJobs(): Call<AppliedJobsModel>

    @GET("${ConstantsNurse.APPLIED_JOBS}/{id}")
    fun appliedJobDetails(@Path("id") id: String): Call<AppliedJobDetailsModel>


    @GET(ConstantsNurse.ENROLLED_CLINICS)
    fun getEnrolledClinics(): Call<EnrolledClinicsModel>

    @GET("${ConstantsNurse.CLINIC_DETAILS_BY_ID}/{id}")
    fun getClinicDetails(@Path("id") id: String): Call<ClinicDetailsModel>

    @GET(ConstantsNurse.NURSE_NOTIFICATIONS)
    fun getNurseNotifications(): Call<NotificationModel>

    @GET(ConstantsNurse.PROFILE_DATA)
    fun getProfileData(): Call<NurseProfileDetailsModel>

    @POST(Constants.UPDATE_PROFILE)
    @FormUrlEncoded
    fun updateProfile(
        @Field("name") name: String,
        @Field("email") email: String,
    ): Call<GenericSuccessErrorModel>


    @POST(ConstantsNurse.CLOCK_IN)
    @FormUrlEncoded
    fun clockIN(
        @Field("location") location: String,
    ): Call<GenericSuccessErrorModel>


    @POST(ConstantsNurse.CLOCK_OUT)
    @FormUrlEncoded
    fun nurseClockOut(
        @Field("location") location: String,
        @Field("image") image: String?
    ): Call<GenericSuccessErrorModel>


    @POST(ConstantsNurse.NURSE_REVIEW)
    @FormUrlEncoded
    fun storeNurseReview(
        @Field("clinicId") clinicId: Int,
        @Field("rating") rating: Int,
        @Field("comment") comment: String,
    ): Call<GenericSuccessErrorModel>

    @FormUrlEncoded
    @POST(ConstantsNurse.REGISTER_NURSE)
    fun registerNurse(

        @Field("name")
        name: String,
        @Field("email")
        email: String,
        @Field("address")
        address: String,
        @Field("mobile")
        mobile: String,
        @Field("dob")
        dob: String,
        @Field("city")
        city: String,
        @Field("state")
        state: String,
        @Field("zip")
        zip: String,
        @Field("nclex_status")
        nclex_status: String,
        @Field("us_immg_status")
        us_immg_status: String,
        @Field("cgfns_status")
        cgfns_status: String,
        @Field("license_type")
        license_type: String,
        @Field("nurse_license")
        nurse_license: String,
        @Field("license_issue")
        license_issue: String,
        @Field("license_expiry")
        license_expiry: String,
        @Field("experience")
        experience: String,
        @Field("speciality")
        speciality: String,

        ): Call<GenericSuccessErrorModel>


    /*    @GET(Constants.CLINIC_JOB_LOCATIONS)
        fun getJobLocations(): Call<ClinicJobLocationsModel>*/

}