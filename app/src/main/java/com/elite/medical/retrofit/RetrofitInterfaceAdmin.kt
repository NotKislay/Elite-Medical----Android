package com.elite.medical.retrofit

import com.elite.medical.retrofit.requestmodels.LoginModel
import com.elite.medical.retrofit.requestmodels.RegisterClinicModel
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval.EmploymentApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobapproval.JobApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.JobSearchApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.ApprovedClinicsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.AssociatedNurseModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NurseTimesheetById
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.JobApplicantsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid.JobClinicNurseDetailsByID
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.ApprovedJobListModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.JobDetailsByIDModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.AdminNotificationsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.ApprovedNurseListModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.NurseByUserIdModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ClinicReviewModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.NurseReviewModel
import com.elite.medical.retrofit.responsemodel.auth.LogoutModel
import com.elite.medical.retrofit.testing.ImageUploadModel
import com.elite.medical.utils.Constants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface RetrofitInterfaceAdmin {

    //    Login, Logout & Register

    @POST(Constants.LOGIN)
    fun login(@Body user: LoginModel): Call<ResponseBody>

    @GET(Constants.LOGOUT)
    fun logout(
        @Header("Authorization") authorization: String
    ): Call<LogoutModel>



    @POST(Constants.REGISTER_CLINIC)
    fun registerClinic(@Body user: RegisterClinicModel): Call<ResponseBody>


    @GET(Constants.ADMIN_DASHBOARD_DATA)
    fun getAdminDashboardData(): Call<AdminDashboardModel>


    @GET(Constants.PROFILE_DATA)
    fun getProfileData(): Call<ProfileDetailsModel>

    @POST(Constants.UPDATE_PROFILE)
    @FormUrlEncoded
    fun updateProfile(
        @Field("name") name: String,
        @Field("email") email: String,
    ): Call<GenericSuccessErrorModel>


    @GET(Constants.APPROVALS_LIST_NURSES)
    fun getNurseApprovalList(
        @Header("Authorization") authorization: String
    ): Call<NurseApprovalModel>

    @GET(Constants.APPROVALS_LIST_CLINICS)
    fun getClinicApprovalList(
        @Header("Authorization") authorization: String
    ): Call<ClinicApprovalModel>

    @GET(Constants.APPROVALS_LIST_JOBS)
    fun getJobApprovalList(
        @Header("Authorization") authorization: String
    ): Call<JobApprovalModel>

    @GET(Constants.APPROVALS_LIST_EMPLOYMENT)
    fun getEmploymentApprovalList(
        @Header("Authorization") authorization: String
    ): Call<EmploymentApprovalModel>

    @GET(Constants.APPROVALS_LIST_JOB_SEARCH)
    fun getJobSearchApprovalList(
        @Header("Authorization") authorization: String
    ): Call<JobSearchApprovalModel>


    @POST(Constants.APPROVE_USER)
    @FormUrlEncoded
    fun approveUser(
        @Header("Authorization") authorization: String,
        @Field("email") email: String
    ): Call<GenericSuccessErrorModel>

    @POST(Constants.APPROVE_JOB)
    @FormUrlEncoded
    fun approveJob(
        @Header("Authorization") authorization: String,
        @Field("job_id") id: Int
    ): Call<GenericSuccessErrorModel>


    @POST(Constants.APPROVE_EMPLOYMENT)
    @FormUrlEncoded
    fun approveEmployment(
        @Header("Authorization") authorization: String,
        @Field("emp_id") id: Int,
        @Field("emp_action") empAction: String
    ): Call<GenericSuccessErrorModel>


    @POST(Constants.SCHEDULE_NURSE)
    @FormUrlEncoded
    fun scheduleNurse(
        @Header("Authorization") authorization: String,
        @Field("id") id: Int,
        @Field("schedule") scheduleDate: String,
        @Field("schedule_time") scheduleTime: String
    ): Call<GenericSuccessErrorModel>


    @POST(Constants.CANCEL_JOB)
    @FormUrlEncoded
    fun cancelJob(
        @Header("Authorization") authorization: String,
        @Field("job_id") id: Int
    ): Call<GenericSuccessErrorModel>

    @POST(Constants.CANCEL_CLINIC)
    @FormUrlEncoded
    fun cancelClinic(
        @Header("Authorization") authorization: String,
        @Field("id") id: Int
    ): Call<GenericSuccessErrorModel>

    @POST(Constants.CANCEL_NURSE)
    @FormUrlEncoded
    fun cancelNurse(
        @Header("Authorization") authorization: String,
        @Field("id") id: Int
    ): Call<GenericSuccessErrorModel>

    @POST(Constants.CANCEL_EMPLOYMENT)
    @FormUrlEncoded
    fun cancelEmployment(
        @Header("Authorization") authorization: String,
        @Field("emp_id") id: Int,
    ): Call<GenericSuccessErrorModel>


//    Side Menu Review Section

    @GET(Constants.NAVIGATION_NURSE_REVIEWS)
    fun getNurseReviews(): Call<NurseReviewModel>

    @GET(Constants.NAVIGATION_CLINIC_REVIEWS)
    fun getClinicReviews(): Call<ClinicReviewModel>


//    Side Menu All Approved Items List     ->  Side menu   ->  Dashboard

    @GET(Constants.NAVIGATION_CLINICS)
    fun getApprovedClinics(): Call<ApprovedClinicsModel>

    @GET("${Constants.NAVIGATION_CLINICS}/{id}")
    fun getAssocNurses(@Path("id") id: String): Call<AssociatedNurseModel>

    @GET(Constants.NAVIGATION_NOTIFICATIONS)
    fun getNotifications(): Call<AdminNotificationsModel>

    @GET(Constants.NAVIGATION_NURSES)
    fun getApprovedNurses(): Call<ApprovedNurseListModel>

    @GET("${Constants.NAVIGATION_NURSES}/{userid}")
    fun getNurseByUserId(@Path("userid") userid: String): Call<NurseByUserIdModel>

    @GET("${Constants.TIMESHEET}/{id}")
    fun getTimesheetById(
        @Path("id") id: String
    ): Call<NurseTimesheetById>

    @GET(Constants.NAVIGATION_JOBS)
    fun getApprovedJobs(): Call<ApprovedJobListModel>


    @GET("${Constants.NAVIGATION_JOBS}/{id}")
    fun getJobDetailsByID(@Path("id") id: String): Call<JobDetailsByIDModel>

    @GET(Constants.NAVIGATION_JOB_APPLICANTS)
    fun getApprovedJobApplicants(): Call<JobApplicantsModel>

    @GET("job-applicants/{job_id}/nurse/{nurse_id}")
    fun getJobNurseClinicDetailsByID(
        @Path("job_id") jobId: String,
        @Path("nurse_id") nurseId: String
    ): Call<JobClinicNurseDetailsByID>

    @POST("test-image")
    @FormUrlEncoded
    fun uploadImageTest(
        @Field("base_64") base_64: String,
    ): Call<ImageUploadModel>


}