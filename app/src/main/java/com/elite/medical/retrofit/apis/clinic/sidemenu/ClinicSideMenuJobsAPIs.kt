package com.elite.medical.retrofit.apis.clinic.sidemenu

import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.JobRelatedDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.MyJobsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClinicSideMenuJobsAPIs {
    companion object {

        interface JobsCallback {
            fun onJobListReceived(code: Int, body: MyJobsModel?)
        }

        interface JobsDetailsCallback {
            fun onJobDetailsReceived(code: Int, body: JobRelatedDetailsModel?)
        }


        fun getJobs(callback: JobsCallback) {
            val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
            api.getJob().enqueue(object : Callback<MyJobsModel?> {
                override fun onResponse(
                    call: Call<MyJobsModel?>,
                    response: Response<MyJobsModel?>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (response.isSuccessful) callback.onJobListReceived(code, body)
                    else callback.onJobListReceived(code, null)
                }

                override fun onFailure(call: Call<MyJobsModel?>, t: Throwable) {}
            })
        }

        fun getJobsDetailsByID(id: String, callback: JobsDetailsCallback) {
            val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
            api.getJobDetailsByID(id).enqueue(object : Callback<JobRelatedDetailsModel?> {
                override fun onResponse(
                    call: Call<JobRelatedDetailsModel?>,
                    response: Response<JobRelatedDetailsModel?>
                ) {

                    val body = response.body()
                    val code = response.code()

                    if (response.isSuccessful) callback.onJobDetailsReceived(code, body)
                    else callback.onJobDetailsReceived(code, null)


                }

                override fun onFailure(call: Call<JobRelatedDetailsModel?>, t: Throwable) {}
            })
        }


    }
}