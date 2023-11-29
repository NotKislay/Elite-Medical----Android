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
import com.elite.medical.utils.endpoints.ConstantsAdmin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitInterfaceAdmin {

    //    Login, Logout & Register

    @POST(ConstantsAdmin.LOGIN)
    fun login(@Body user: LoginModel): Call<ResponseBody>

    @GET(ConstantsAdmin.LOGOUT)
    fun logout(): Call<LogoutModel>


    @POST(ConstantsAdmin.REGISTER_CLINIC)
    fun registerClinic(@Body user: RegisterClinicModel): Call<GenericSuccessErrorModel>


    @GET(ConstantsAdmin.ADMIN_DASHBOARD_DATA)
    fun getAdminDashboardData(): Call<AdminDashboardModel>


    @GET(ConstantsAdmin.PROFILE_DATA)
    fun getProfileData(): Call<ProfileDetailsModel>

    @POST(ConstantsAdmin.UPDATE_PROFILE)
    @FormUrlEncoded
    fun updateProfile(
        @Field("name") name: String,
        @Field("email") email: String,
    ): Call<GenericSuccessErrorModel>


    @GET(ConstantsAdmin.APPROVALS_LIST_NURSES)
    fun getNurseApprovalList(): Call<NurseApprovalModel>

    @GET(ConstantsAdmin.APPROVALS_LIST_CLINICS)
    fun getClinicApprovalList(): Call<ClinicApprovalModel>

    @GET(ConstantsAdmin.APPROVALS_LIST_JOBS)
    fun getJobApprovalList(): Call<JobApprovalModel>

    @GET(ConstantsAdmin.APPROVALS_LIST_EMPLOYMENT)
    fun getEmploymentApprovalList(): Call<EmploymentApprovalModel>

    @GET(ConstantsAdmin.APPROVALS_LIST_JOB_SEARCH)
    fun getJobSearchApprovalList(): Call<JobSearchApprovalModel>


    @POST(ConstantsAdmin.APPROVE_USER)
    @FormUrlEncoded
    fun approveUser(
        @Field("email") email: String
    ): Call<GenericSuccessErrorModel>

    @POST(ConstantsAdmin.APPROVE_JOB)
    @FormUrlEncoded
    fun approveJob(
        @Field("job_id") id: Int
    ): Call<GenericSuccessErrorModel>


    @POST(ConstantsAdmin.APPROVE_EMPLOYMENT)
    @FormUrlEncoded
    fun approveEmployment(
        @Field("emp_id") id: Int,
        @Field("emp_action") empAction: String
    ): Call<GenericSuccessErrorModel>


    @POST(ConstantsAdmin.SCHEDULE_NURSE)
    @FormUrlEncoded
    fun scheduleNurse(
        @Field("id") id: Int,
        @Field("schedule") scheduleDate: String,
        @Field("schedule_time") scheduleTime: String
    ): Call<GenericSuccessErrorModel>


    @POST(ConstantsAdmin.CANCEL_JOB)
    @FormUrlEncoded
    fun cancelJob(
        @Field("job_id") id: Int
    ): Call<GenericSuccessErrorModel>

    @POST(ConstantsAdmin.CANCEL_CLINIC)
    @FormUrlEncoded
    fun cancelClinic(
        @Field("id") id: Int
    ): Call<GenericSuccessErrorModel>

    @POST(ConstantsAdmin.CANCEL_NURSE)
    @FormUrlEncoded
    fun cancelNurse(
        @Field("id") id: Int
    ): Call<GenericSuccessErrorModel>

    @POST(ConstantsAdmin.CANCEL_EMPLOYMENT)
    @FormUrlEncoded
    fun cancelEmployment(
        @Field("emp_id") id: Int,
    ): Call<GenericSuccessErrorModel>


//    Side Menu Review Section

    @GET(ConstantsAdmin.NAVIGATION_NURSE_REVIEWS)
    fun getNurseReviews(): Call<NurseReviewModel>

    @GET(ConstantsAdmin.NAVIGATION_CLINIC_REVIEWS)
    fun getClinicReviews(): Call<ClinicReviewModel>


//    Side Menu All Approved Items List     ->  Side menu   ->  Dashboard

    @GET(ConstantsAdmin.NAVIGATION_CLINICS)
    fun getApprovedClinics(): Call<ApprovedClinicsModel>

    @GET("${ConstantsAdmin.NAVIGATION_CLINICS}/{id}")
    fun getAssocNurses(@Path("id") id: String): Call<AssociatedNurseModel>

    @GET(ConstantsAdmin.NAVIGATION_NOTIFICATIONS)
    fun getNotifications(): Call<AdminNotificationsModel>

    @GET(ConstantsAdmin.NAVIGATION_NURSES)
    fun getApprovedNurses(): Call<ApprovedNurseListModel>

    @GET("${ConstantsAdmin.NAVIGATION_NURSES}/{userid}")
    fun getNurseByUserId(@Path("userid") userid: String): Call<NurseByUserIdModel>

    @GET("${ConstantsAdmin.TIMESHEET}/{id}")
    fun getTimesheetById(
        @Path("id") id: String
    ): Call<NurseTimesheetById>

    @GET(ConstantsAdmin.NAVIGATION_JOBS)
    fun getApprovedJobs(): Call<ApprovedJobListModel>


    @GET("${ConstantsAdmin.NAVIGATION_JOBS}/{id}")
    fun getJobDetailsByID(@Path("id") id: String): Call<JobDetailsByIDModel>

    @GET(ConstantsAdmin.NAVIGATION_JOB_APPLICANTS)
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