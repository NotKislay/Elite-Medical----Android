package com.elite.medical.retrofit.apis.admin.sidemenu

import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.ApprovedClinicsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.ClinicDetailsFromApprovedClinicsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.AssociatedNurseModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NurseTimesheetById
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.NursesDetailsFromAssociatedNurseModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.TimesheetDataModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.AllJobApplicant
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.JobApplicantsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobapplication.alldetailsbyid.JobClinicNurseDetailsByID
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.ApprovedJobListModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.Job
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.JobDetailsByIDModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.jobs.JobX
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.AdminNotificationsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.ApprovedNurseListModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.Nurse
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.nurses.NurseByUserIdModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardAPIs {
    companion object {

        interface ApprovedNursesCallback {
            fun onListReceived(nurses: List<Nurse>)
        }

        interface NurseByUserIdCallback {
            fun onListReceived(details: NurseByUserIdModel)
            fun onResponseErr(errorData: String, statusCode: Int)
        }

        interface ApprovedJobsCallback {
            fun onListReceived(jobs: List<Job>)
        }

        interface ApprovedJobApplicantsCallback {
            fun onListReceived(jobApplications: List<AllJobApplicant>)
        }

        interface JobApplicationsDetailsCallback {
            fun onDetailsReceived(details: JobClinicNurseDetailsByID)
        }

        interface JobDetailsByIDCallback {
            fun onDetailsReceived(jobDetails: JobX)
        }

        interface ApprovedClinicsCallback {
            fun onListReceived(clinics: List<ClinicDetailsFromApprovedClinicsModel>)
        }

        interface AssocNursesCallback {
            fun onListReceived(clinics: List<NursesDetailsFromAssociatedNurseModel>)
        }

        interface NurseTimesheetCallback {
            fun onListReceived(timesheetDataModels: List<TimesheetDataModel>)
            fun onErr(errorMsg: String)
        }

        interface NotificationsCallback {
            fun onListReceived(notification: List<NotificationDetailsFromAdminNotificationsModel>)
        }


        //        Side Menu Dashboard Section
        fun getNotifications(callback: NotificationsCallback) {
            EliteMedical.retrofitAdmin.getNotifications()
                .enqueue(object : Callback<AdminNotificationsModel> {
                    override fun onResponse(
                        call: Call<AdminNotificationsModel>,
                        response: Response<AdminNotificationsModel>
                    ) {
                        if (response.isSuccessful) {
                            val responseData = response.body()
                            val notificationList = responseData!!.notifications
                            callback.onListReceived(notificationList)
                        } else {
                            response.errorBody().toString()

                        }
                    }

                    override fun onFailure(call: Call<AdminNotificationsModel>, t: Throwable) {

                    }
                })
        }

        fun getClinics(callback: ApprovedClinicsCallback) {
            EliteMedical.retrofitAdmin.getApprovedClinics()
                .enqueue(object : Callback<ApprovedClinicsModel> {
                    override fun onResponse(
                        call: Call<ApprovedClinicsModel>, response: Response<ApprovedClinicsModel>
                    ) {
                        if (response.isSuccessful) {
                            val responseData = response.body()
                            val clinicList = responseData!!.clinics
                            callback.onListReceived(clinicList)
                        } else {
                            response.errorBody().toString()
                        }
                    }

                    override fun onFailure(call: Call<ApprovedClinicsModel>, t: Throwable) {
                    }
                })
        }

        fun getNurses(callback: ApprovedNursesCallback) {
            EliteMedical.retrofitAdmin.getApprovedNurses()
                .enqueue(object : Callback<ApprovedNurseListModel?> {
                    override fun onResponse(
                        call: Call<ApprovedNurseListModel?>,
                        response: Response<ApprovedNurseListModel?>
                    ) {

                        if (response.isSuccessful) {
                            val nurses = response.body()!!.nurses
                            callback.onListReceived(nurses)
                        }

                    }

                    override fun onFailure(call: Call<ApprovedNurseListModel?>, t: Throwable) {

                    }
                })
        }

        fun getJobs(callback: ApprovedJobsCallback) {
            EliteMedical.retrofitAdmin.getApprovedJobs()
                .enqueue(object : Callback<ApprovedJobListModel?> {
                    override fun onResponse(
                        call: Call<ApprovedJobListModel?>,
                        response: Response<ApprovedJobListModel?>
                    ) {
                        if (response.isSuccessful)
                            callback.onListReceived(response.body()!!.jobs)
                    }

                    override fun onFailure(call: Call<ApprovedJobListModel?>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }

        fun getJobApplicants(callback: ApprovedJobApplicantsCallback) {
            EliteMedical.retrofitAdmin.getApprovedJobApplicants()
                .enqueue(object : Callback<JobApplicantsModel?> {
                    override fun onResponse(
                        call: Call<JobApplicantsModel?>,
                        response: Response<JobApplicantsModel?>
                    ) {
                        if (response.isSuccessful)
                            callback.onListReceived(response.body()!!.allJobApplicants)
                    }

                    override fun onFailure(call: Call<JobApplicantsModel?>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }

        fun getNurseByUserId(userId: String, callback: NurseByUserIdCallback) {
            EliteMedical.retrofitAdmin.getNurseByUserId(userId)
                .enqueue(object : Callback<NurseByUserIdModel> {
                    override fun onResponse(
                        call: Call<NurseByUserIdModel>,
                        response: Response<NurseByUserIdModel>
                    ) {
                        if (response.isSuccessful) {

                            val details = response.body()
                            if (details != null) {
                                callback.onListReceived(details)
                            }
                        } else {
                            val errorData = response.errorBody().toString()

                            callback.onResponseErr(errorData, response.code())
                        }
                    }

                    override fun onFailure(call: Call<NurseByUserIdModel>, t: Throwable) {

                    }
                })
        }

        fun getJobDetailsByID(jobID: String, callback: JobDetailsByIDCallback) {
            EliteMedical.retrofitAdmin.getJobDetailsByID(jobID)
                .enqueue(object : Callback<JobDetailsByIDModel?> {
                    override fun onResponse(
                        call: Call<JobDetailsByIDModel?>,
                        response: Response<JobDetailsByIDModel?>
                    ) {
                        if (response.isSuccessful) {
                            callback.onDetailsReceived(response.body()!!.job)

                        }
                    }

                    override fun onFailure(call: Call<JobDetailsByIDModel?>, t: Throwable) {

                    }
                })
        }

        fun getJobApplicationDetailsByID(
            jobID: String,
            nurseID: String,
            callback: JobApplicationsDetailsCallback
        ) {
            EliteMedical.retrofitAdmin.getJobNurseClinicDetailsByID(jobID, nurseID)
                .enqueue(object : Callback<JobClinicNurseDetailsByID?> {
                    override fun onResponse(
                        call: Call<JobClinicNurseDetailsByID?>,
                        response: Response<JobClinicNurseDetailsByID?>
                    ) {
                        if (response.isSuccessful)
                            callback.onDetailsReceived(response.body()!!)
                    }

                    override fun onFailure(call: Call<JobClinicNurseDetailsByID?>, t: Throwable) {

                    }
                })
        }

        fun getAssocNursesList(userID: String, callback: AssocNursesCallback) {
            EliteMedical.retrofitAdmin.getAssocNurses(userID)
                .enqueue(object : Callback<AssociatedNurseModel> {
                    override fun onResponse(
                        call: Call<AssociatedNurseModel>, response: Response<AssociatedNurseModel>
                    ) {
                        if (response.isSuccessful) {
                            val responseData = response.body()
                            val associatedNurseList = responseData!!.nurses
                            callback.onListReceived(associatedNurseList)
                        } else {
                            response.errorBody().toString()

                        }
                    }

                    override fun onFailure(call: Call<AssociatedNurseModel>, t: Throwable) {

                    }
                })
        }

        fun getTimesheetByNurseId(id: String, callback: NurseTimesheetCallback) {
            EliteMedical.retrofitAdmin.getTimesheetById(id)
                .enqueue(object : Callback<NurseTimesheetById> {
                    override fun onResponse(
                        call: Call<NurseTimesheetById>, response: Response<NurseTimesheetById>
                    ) {


                        if (response.isSuccessful) {
                            val responseData = response.body()
                            val timeSheetList = responseData!!.newTimesheetDataModels
                            callback.onListReceived(timeSheetList)
                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onErr(errorModel.message)
                        }
                    }

                    override fun onFailure(call: Call<NurseTimesheetById>, t: Throwable) {


                    }
                })
        }

    }
}