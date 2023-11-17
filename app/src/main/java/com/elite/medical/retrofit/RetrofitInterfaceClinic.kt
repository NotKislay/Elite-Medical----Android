package com.elite.medical.retrofit

import com.elite.medical.retrofit.requestmodels.LoginModel
import com.elite.medical.retrofit.requestmodels.RegisterClinicModel
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.requestmodels.clinic.PostJobRequestModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.ClinicNotificationsModel
import com.elite.medical.retrofit.responsemodel.auth.LoginResponseModel
import com.elite.medical.retrofit.responsemodel.clinic.ClinicProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.ClinicJobLocationsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.MyJobsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobsByClinicsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.NursesAppliedOnJobModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.EnrolledNursesModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.SearchNurseModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details.AvailableNurseDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.EnrolledNurseByidModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.NurseTimesheetByIdModelFromClinic
import com.elite.medical.utils.ConstantsClinic
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitInterfaceClinic {

    //    Login, Logout & Register

    @POST(ConstantsClinic.LOGIN)
    fun loginClinic(@Body user: LoginModel): Call<LoginResponseModel>

    @POST(ConstantsClinic.REGISTER_CLINIC)
    fun registerClinic(@Body user: RegisterClinicModel): Call<ResponseBody>


//    Dashboard Screen Details

    @GET(ConstantsClinic.CLINIC_DASHBOARD_DATA)
    fun getDashboardData(): Call<ClinicDashboardModel>

    @GET(ConstantsClinic.NAVIGATION_NOTIFICATIONS)
    fun getNotifications(): Call<ClinicNotificationsModel>

    @GET(ConstantsClinic.PROFILE_DATA)
    fun getClinicProfileData(): Call<ClinicProfileDetailsModel>

    @POST(ConstantsClinic.UPDATE_PROFILE)
    @FormUrlEncoded
    fun updateProfile(
        @Field("name") name: String,
        @Field("email") email: String,
    ): Call<GenericSuccessErrorModel>

    @GET(ConstantsClinic.ENROLLED_NURSES)
    fun getListOfEnrolledNurses(): Call<EnrolledNursesModel>

    @GET(ConstantsClinic.AVAILABLE_NURSES)
    fun getListOfAvailableSearchNurses(): Call<SearchNurseModel>

    @GET("${ConstantsClinic.VIEW_NURSE_BY_ID}/{id}")
    fun getEnrolledNurseByID(@Path("id") id: String): Call<EnrolledNurseByidModel>

    @GET("${ConstantsClinic.SEARCH_NURSE_BY_ID}/{id}")
    fun getAvailableNurseDetailsByID(@Path("id") id: String): Call<AvailableNurseDetailsModel>

    @POST(ConstantsClinic.POST_REVIEW)
    @FormUrlEncoded
    fun postNurseReview(
        @Field("nurseId") nurseid: String,
        @Field("comment") comment: String,
        @Field("rating") rating: Int,
    ): Call<GenericSuccessErrorModel>

    @POST(ConstantsClinic.TERMINATE_NURSE)
    @FormUrlEncoded
    fun terminateNurseByClinic(
        @Field("termination_date") termndate: String,
        @Field("nurseId") nurseid: Int
    ): Call<GenericSuccessErrorModel>

    @GET("${ConstantsClinic.NURSE_TIMESHEET_BY_ID}/{id}")
    fun getTimesheetById(@Path("id") id: String): Call<NurseTimesheetByIdModelFromClinic>

    @GET(ConstantsClinic.CLINIC_JOBS)
    fun getJob(): Call<MyJobsModel>




    @GET(ConstantsClinic.CLINIC_JOB_LOCATIONS)
    fun getJobLocations(): Call<ClinicJobLocationsModel>


    @POST(ConstantsClinic.CLINIC_CLOSE_JOB)
    @FormUrlEncoded
    fun closeJobByID(
        @Field("jobId") jobId: String,
    ): Call<GenericSuccessErrorModel>

    @GET(ConstantsClinic.CLINIC_JOB_N_APPLICANTS)
    fun getJobNApplicants(): Call<JobsByClinicsModel>

    @GET("${ConstantsClinic.CLINIC_JOBS}/{id}")
    fun getJobDetailsByID(@Path("id") id: String): Call<NursesAppliedOnJobModel>


    @POST(ConstantsClinic.POST_JOB)
    fun postJob(@Body jobDetails: PostJobRequestModel): Call<ResponseBody>

    @POST(ConstantsClinic.HIRE_ACTION)
    fun jobHireAction(@Body hiringActionDetails: JobHiringActionModel): Call<GenericSuccessErrorModel>


    @FormUrlEncoded
    @POST("clinic/search-nurse/{id}/invite")
    fun inviteNurseToJobByID(
        @Path("id") id: String,
        @Field("job_title") job_title: String
    ): Call<GenericSuccessErrorModel>

    /*    @GET("/clinic/job/{jobID}/applicants/{clinicID}")
        fun getApplicantsDetailsByClinicNJobID(
            @Path("jobID") jobID: String,
            @Path("clinicID") clinicID: String,
        ): Call<ApplicantsDetailsModel>*/


}