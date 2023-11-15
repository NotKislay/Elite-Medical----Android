package com.elite.medical.retrofit

import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.PostRequestResponseModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.ClinicJobLocationsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobDetailModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobList
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.EnrolledClinicsModel
import com.elite.medical.retrofit.responsemodel.nurse.dashboard.notification.NotificationModel
import com.elite.medical.retrofit.responsemodel.nurse.dashboard.profile.NurseProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel
import com.elite.medical.utils.Constants
import com.elite.medical.utils.ConstantsNurse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitInterfaceNurse {

    @GET(ConstantsNurse.SEARCH_JOBS)
    fun searchJobs(): Call<JobList>

    @GET("${ConstantsNurse.SEARCH_JOBS}/{id}")
    fun jobDetailsByID(@Path("id") id: String): Call<JobDetailModel>

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
    ): Call<PostRequestResponseModel>


    /*    @GET(Constants.CLINIC_JOB_LOCATIONS)
        fun getJobLocations(): Call<ClinicJobLocationsModel>*/

}