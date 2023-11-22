package com.elite.medical.retrofit.apis.admin.sidemenu.approvals

import android.util.Log
import com.elite.medical.retrofit.RetrofitClient
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobapproval.JobApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.clinicapproval.ClinicDetailsFromClinicApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval.EmploymentApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.employmentapproval.EmploymentDetailsFromEmploymentApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobapproval.JobDetailsFromJobApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.nurseapproval.NurseDetailsFromNurseApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.JobSearchApprovalModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.jobsearchapproval.NurseDetailJobSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApprovalAPIs {
    companion object {
        interface NurseApprovalCallback {
            fun onListReceived(nurses: List<NurseDetailsFromNurseApprovalModel>)
            fun onResponseErr(msg: String, statusCode: String)
        }

        interface ClinicApprovalCallback {
            fun onListReceived(clinics: List<ClinicDetailsFromClinicApprovalModel>)
            fun onResponseErr(msg: String, statusCode: String)
        }

        interface JobApprovalCallback {
            fun onListReceived(jobs: List<JobDetailsFromJobApprovalModel>)
            fun onResponseErr(msg: String, statusCode: String)
        }

        interface EmploymentApprovalCallback{
            fun onListReceived(jobs: List<EmploymentDetailsFromEmploymentApprovalModel>)
            fun onResponseErr(msg: String, statusCode: String)
        }

        interface JobSearchApprovalCallback {
            fun onListReceived(jobs: List<NurseDetailJobSearch>)
            fun onResponseErr(msg: String, statusCode: String)
        }

        fun fetchNurseApprovalList(
            authToken: String, callback: NurseApprovalCallback
        ) {
            val fetchNurseApprovalListAPI =
                RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
            val result = fetchNurseApprovalListAPI.getNurseApprovalList("Bearer $authToken")
            result.enqueue(object : Callback<NurseApprovalModel> {
                override fun onResponse(
                    call: Call<NurseApprovalModel>, response: Response<NurseApprovalModel>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val nurseList = responseData!!.approvalNurseDetails

                        callback.onListReceived(nurseList)
                    } else {
                        val statusCode=response.code().toString()
                        val errorData = response.errorBody().toString()
                        
                        callback.onResponseErr(errorData,statusCode)
                    }
                }
                override fun onFailure(call: Call<NurseApprovalModel>, t: Throwable) {
                    
                }
            })
        }

        fun fetchClinicApprovalList(
            authToken: String, callback: ClinicApprovalCallback
        ) {
            val fetchClinicApprovalListAPI =
                RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
            val result = fetchClinicApprovalListAPI.getClinicApprovalList("Bearer $authToken")
            result.enqueue(object : Callback<ClinicApprovalModel> {
                override fun onResponse(
                    call: Call<ClinicApprovalModel>, response: Response<ClinicApprovalModel>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val clinicList = responseData!!.clinicApprovals
                        for (clinic in clinicList) {
                            
                        }
                        callback.onListReceived(clinicList)
                    } else {
                        val statusCode=response.code().toString()
                        val errorData = response.errorBody().toString()
                        
                        callback.onResponseErr(errorData,statusCode)
                    }
                }

                override fun onFailure(call: Call<ClinicApprovalModel>, t: Throwable) {
                    
                }
            })
        }

        fun fetchJobApprovalList(
            authToken: String, callback: JobApprovalCallback
        ) {
            val fetchJobApprovalListAPI =
                RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
            val result = fetchJobApprovalListAPI.getJobApprovalList("Bearer $authToken")
            result.enqueue(object : Callback<JobApprovalModel> {
                override fun onResponse(
                    call: Call<JobApprovalModel>, response: Response<JobApprovalModel>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val jobList = responseData!!.jobApprovals
                        for (job in jobList) {
                            
                        }
                        callback.onListReceived(jobList)
                    } else {
                        val statusCode=response.code().toString()
                        val errorData = response.errorBody().toString()
                        
                        callback.onResponseErr(errorData,statusCode)
                    }
                }

                override fun onFailure(call: Call<JobApprovalModel>, t: Throwable) {
                    
                }
            })
        }

        fun fetchEmploymentApprovalList(
            authToken: String,
            callback: EmploymentApprovalCallback
        ){
            val api = RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
            val result = api.getEmploymentApprovalList("Bearer $authToken")
            result.enqueue(object : Callback<EmploymentApprovalModel?> {
                override fun onResponse(
                    call: Call<EmploymentApprovalModel?>,
                    response: Response<EmploymentApprovalModel?>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val employmentDetails = responseData!!.employmentDetails
                        for (employment in employmentDetails) {
                            
                        }
                        callback.onListReceived(employmentDetails)
                    }else {
                        val statusCode=response.code().toString()
                        val errorData = response.errorBody().toString()
                        
                        callback.onResponseErr(errorData,statusCode)
                    }
                }
                override fun onFailure(call: Call<EmploymentApprovalModel?>, t: Throwable) {
                    
                }
            })
        }


        fun fetchJobSearchApprovalList(
            authToken: String,
            callback: JobSearchApprovalCallback
        ){
            val api = RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
            val result = api.getJobSearchApprovalList("Bearer $authToken")
            result.enqueue(object : Callback<JobSearchApprovalModel?> {
                override fun onResponse(
                    call: Call<JobSearchApprovalModel?>,
                    response: Response<JobSearchApprovalModel?>
                ) {

                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val employmentDetails = responseData!!.nurseDetails
                        for (employment in employmentDetails) {
                            
                        }
                        callback.onListReceived(employmentDetails)
                    } else {
                        val statusCode=response.code().toString()
                        val errorData = response.errorBody().toString()
                        
                        callback.onResponseErr(errorData,statusCode)
                    }
                }
                override fun onFailure(call: Call<JobSearchApprovalModel?>, t: Throwable) {
                    

                }
            })
        }
    }
}