package com.elite.medical.nurse.viewmodels.jobs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobDetailModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobList
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.SearchJobsModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel : ViewModel() {

    val appliedJobs: MutableLiveData<List<AppliedJobsModel.AppliedJob>?> = MutableLiveData()
    val appliedJobDetails: MutableLiveData<AppliedJobDetailsModel.Job?> = MutableLiveData()

    val currentJobID: MutableLiveData<String> = MutableLiveData()
    var searchJobDetail: MutableLiveData<Job?> = MutableLiveData()

    var jobListCallback: ((JobList) -> Unit)? = null
    var jobSearchRequestCallback: ((GenericSuccessErrorModel) -> Unit)? = null
    var applyJobByIDCallback: ((GenericSuccessErrorModel) -> Unit)? = null


    init {
        getAppliedJobs()

    }

    fun getJobsList() {
        EliteMedical.retrofitNurse.searchJobs().enqueue(object : Callback<JobList?> {
            override fun onResponse(call: Call<JobList?>, response: Response<JobList?>) {
                val res = response
                if (response.isSuccessful) {
                    val body = response.body()
                    jobListCallback?.invoke(body!!)
                }
            }

            override fun onFailure(call: Call<JobList?>, t: Throwable) {}
        })
    }

    fun getJobDetailsByID(id: String) {
        EliteMedical.retrofitNurse.jobDetailsByID(id)
            .enqueue(object : Callback<JobDetailModel?> {
                override fun onResponse(
                    call: Call<JobDetailModel?>,
                    response: Response<JobDetailModel?>
                ) {

                    val res = response

                    if (response.isSuccessful) {
                        val details = response.body()?.jobs
                        searchJobDetail.postValue(details)
                    }
                }

                override fun onFailure(call: Call<JobDetailModel?>, t: Throwable) {}
            })
    }


    fun jobSearchRequest() {
        EliteMedical.retrofitNurse.requestSearchJobs()
            .enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    if (response.isSuccessful) jobSearchRequestCallback?.invoke(response.body()!!)
                    else {
                        val errorBody = response.errorBody()!!
                        val errorModel =
                            Gson().fromJson(
                                errorBody.string(),
                                GenericSuccessErrorModel::class.java
                            )
                        jobSearchRequestCallback?.invoke(errorModel)
                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
            })
    }

    private fun getAppliedJobs() {
        EliteMedical.retrofitNurse.appliedJobs().enqueue(object : Callback<AppliedJobsModel?> {
            override fun onResponse(
                call: Call<AppliedJobsModel?>,
                response: Response<AppliedJobsModel?>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()?.appliedJobs
                    appliedJobs.postValue(body)
                }
            }

            override fun onFailure(call: Call<AppliedJobsModel?>, t: Throwable) {}
        })
    }

    fun getAppliedJobDetailsByID(id: String) {
        EliteMedical.retrofitNurse.appliedJobDetails(id)
            .enqueue(object : Callback<AppliedJobDetailsModel?> {
                override fun onResponse(
                    call: Call<AppliedJobDetailsModel?>,
                    response: Response<AppliedJobDetailsModel?>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        val job = body?.job
                        appliedJobDetails.postValue(job)
                    }

                }

                override fun onFailure(call: Call<AppliedJobDetailsModel?>, t: Throwable) {}
            })
    }

    fun applyJobByID(jobID: String) {
        EliteMedical.retrofitNurse.applyJobByID(jobID)
            .enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        applyJobByIDCallback?.invoke(body!!)
                    } else {
                        val error = response.errorBody()!!
                        val errorModel = Gson().fromJson(
                            error.charStream(),
                            GenericSuccessErrorModel::class.java
                        )
                        applyJobByIDCallback?.invoke(errorModel)
                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
            })
    }

    fun updateCurrentJobID(id: String) = currentJobID.postValue(id)


}