package com.elite.medical.retrofit.apis.clinic.sidemenu

import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.clinics.more.TimesheetDataModel
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.EnrolledNursesModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.SearchNurseModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details.AvailableNurseDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.avlbl_nurse_details.ClinicReviewModelFromAvlblNurseDetails
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.EmploymentFromEnrNursByid
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.EnrolledNurseByidModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.NurseFromEnrNurseDet
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.NurseTimesheetByIdModelFromClinic
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.enr_nurse_details.ReviewFromEnrNurseDet
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NursesClinicAPIs {
    companion object {


        interface NursesEnrolledCallback {
            fun onListReceived(nurses: List<Nurse>)
        }

        interface NursesAvailableCallback {
            fun onListReceived(nurses: List<Nurse>)
        }

        interface EnrolledNursesByIdCallback {
            fun onNurseDetailsReceived(nurses: NurseFromEnrNurseDet, statusCode: Int?)
            fun onReviewReceived(reviews: List<ReviewFromEnrNurseDet>, statusCode: Int?)
            fun onEmploymentDetailsReceived(empdetails: EmploymentFromEnrNursByid, statusCode: Int?)
        }

        interface avlblNurseDetailsByIdCallback {
            fun onNurseDetailsReceived(nurses: NurseFromEnrNurseDet, statusCode: Int?)
            fun onReviewReceived(
                reviews: List<ClinicReviewModelFromAvlblNurseDetails>,
                statusCode: Int?
            )

            fun onListofApprovedJobsReceived(joblist: List<String>, statusCode: Int?)
        }

        interface NurseTimesheetCallback {
            fun onListReceived(timesheetDataModels: List<TimesheetDataModel>)
            fun onErr(errorMsg: String)
        }

        interface TerminateNurseCallback {
            fun onMsgReceived(message: String, code: Int)
        }

        interface InviteNurseCallback {
            fun onSuccess(msg: GenericSuccessErrorModel)
            fun onError(msg: GenericSuccessErrorModel?)
        }

        fun inviteNurseToJobByID(id: String, jobTitle: String, callback: InviteNurseCallback) {
            val api = EliteMedical.retrofitClinic
            api.inviteNurseToJobByID(id, jobTitle)
                .enqueue(object : Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {

                        val res = response

                        val result = response.body()
                        if (response.isSuccessful) {
                            callback.onSuccess(result!!)
                        } else {
                            callback.onError(null)

                        }

                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
                })
        }


        fun getNurseEnrolled(callback: NursesEnrolledCallback) {
            val api = EliteMedical.retrofitClinic
            val result = api.getListOfEnrolledNurses()
            result.enqueue(object : Callback<EnrolledNursesModel?> {
                override fun onResponse(
                    call: Call<EnrolledNursesModel?>,
                    response: Response<EnrolledNursesModel?>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        callback.onListReceived(data.nurses)
                    } else {
                        callback.onListReceived(emptyList())
                    }
                }

                override fun onFailure(call: Call<EnrolledNursesModel?>, t: Throwable) {

                }

            })
        }

        fun getAvailableSearchNurses(callback: NursesAvailableCallback) {
            val api = EliteMedical.retrofitClinic
            val result = api.getListOfAvailableSearchNurses()
            result.enqueue(object : Callback<SearchNurseModel?> {
                override fun onResponse(
                    call: Call<SearchNurseModel?>,
                    response: Response<SearchNurseModel?>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        callback.onListReceived(data.nurses)
                    } else {
                        callback.onListReceived(emptyList())
                    }
                }

                override fun onFailure(call: Call<SearchNurseModel?>, t: Throwable) {

                }

            })
        }

        fun getNurseEnrByid(id: String, callback: EnrolledNursesByIdCallback) {
            val api = EliteMedical.retrofitClinic
            val result = api.getEnrolledNurseByID(id)
            result.enqueue(object : Callback<EnrolledNurseByidModel?> {
                override fun onResponse(
                    call: Call<EnrolledNurseByidModel?>,
                    response: Response<EnrolledNurseByidModel?>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        callback.onEmploymentDetailsReceived(data.employment, response.code())
                        callback.onNurseDetailsReceived(data.nurse, response.code())
                        callback.onReviewReceived(data.review, response.code())
                    }
                }

                override fun onFailure(call: Call<EnrolledNurseByidModel?>, t: Throwable) {

                }

            })
        }

        fun getTimesheetByNurseId(id: String, callback: NurseTimesheetCallback) {
            val nurseTimesheetAPI =
                EliteMedical.retrofitClinic
            val result = nurseTimesheetAPI.getTimesheetById(id)
            result.enqueue(object : Callback<NurseTimesheetByIdModelFromClinic> {
                override fun onResponse(
                    call: Call<NurseTimesheetByIdModelFromClinic>,
                    response: Response<NurseTimesheetByIdModelFromClinic>
                ) {


                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val timeSheetList = responseData!!.TimesheetDataModels
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

                override fun onFailure(
                    call: Call<NurseTimesheetByIdModelFromClinic>,
                    t: Throwable
                ) {


                }
            })
        }

        fun getAvlblNurseDetailsByid(id: String, callback: avlblNurseDetailsByIdCallback) {
            val api = EliteMedical.retrofitClinic
            val result = api.getAvailableNurseDetailsByID(id)
            result.enqueue(object : Callback<AvailableNurseDetailsModel?> {
                override fun onResponse(
                    call: Call<AvailableNurseDetailsModel?>,
                    response: Response<AvailableNurseDetailsModel?>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        callback.onNurseDetailsReceived(data.nurse, response.code())
                        callback.onReviewReceived(data.review, response.code())
                        callback.onListofApprovedJobsReceived(data.approvedJobs, response.code())
                    }
                }

                override fun onFailure(call: Call<AvailableNurseDetailsModel?>, t: Throwable) {

                }

            })
        }


        fun terminatenursebyid(date: String, id: Int, callback: TerminateNurseCallback) {
            var msg: String
            val terminateAPI =
                EliteMedical.retrofitClinic
            val result = terminateAPI.terminateNurseByClinic(date, id)
            result.enqueue(object : Callback<GenericSuccessErrorModel> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel>,
                    response: Response<GenericSuccessErrorModel>
                ) {
                    if (response.isSuccessful) {
                        val resdata = response.body()!!

                        msg = resdata.message
                        callback.onMsgReceived(msg, response.code())
                    } else {
                        val errorBody = response.errorBody()!!
                        val errorModel =
                            Gson().fromJson(
                                errorBody.string(),
                                GenericSuccessErrorModel::class.java
                            )
                        callback.onMsgReceived(errorModel.message, response.code())
                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel>, t: Throwable) {

                }

            })
        }

        /*fun postnursereview(nurseid: String, comment: String, rating: Int) {
            val reviewAPI = EliteMedical.retrofitClinic
            val result = reviewAPI.postNurseReview(nurseid, comment, rating)
            //todo START HERE
//            result.enqueue()
        }*/

    }

}