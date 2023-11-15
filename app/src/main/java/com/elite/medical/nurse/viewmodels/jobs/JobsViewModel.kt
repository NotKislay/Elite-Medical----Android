package com.elite.medical.nurse.viewmodels.jobs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.appliedjobs.AppliedJobsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel : ViewModel() {

    val appliedJobs: MutableLiveData<List<AppliedJobsModel.AppliedJob>?> = MutableLiveData()

    val currentJobID: MutableLiveData<String> = MutableLiveData()

    val jobDetails: MutableLiveData<AppliedJobDetailsModel.Job?> = MutableLiveData()


    init {
        getAppliedJobs()
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
                } else {
                    print("TODO")
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
                        jobDetails.postValue(job)
                    }

                }

                override fun onFailure(call: Call<AppliedJobDetailsModel?>, t: Throwable) {}
            })
    }


}