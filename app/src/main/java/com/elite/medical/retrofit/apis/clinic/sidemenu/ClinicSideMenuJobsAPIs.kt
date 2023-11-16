package com.elite.medical.retrofit.apis.clinic.sidemenu

import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.MyJobsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.NursesAppliedOnJobModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClinicSideMenuJobsAPIs {
    companion object {

        interface JobsCallback {
            fun onJobListReceived(code: Int, body: MyJobsModel?)
        }

        interface JobsDetailsCallback {
            fun onJobDetailsReceived(code: Int, body: NursesAppliedOnJobModel?)
        }


        fun getJobs(callback: JobsCallback) {
            val api = EliteMedical.retrofitClinic
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
            val api = EliteMedical.retrofitClinic
            api.getJobDetailsByID(id).enqueue(object : Callback<NursesAppliedOnJobModel?> {
                override fun onResponse(
                    call: Call<NursesAppliedOnJobModel?>,
                    response: Response<NursesAppliedOnJobModel?>
                ) {

                    val body = response.body()
                    val code = response.code()

                    if (response.isSuccessful) callback.onJobDetailsReceived(code, body)
                    else callback.onJobDetailsReceived(code, null)


                }

                override fun onFailure(call: Call<NursesAppliedOnJobModel?>, t: Throwable) {}
            })
        }


    }
}