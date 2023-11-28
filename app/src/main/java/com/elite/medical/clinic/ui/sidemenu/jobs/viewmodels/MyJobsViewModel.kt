package com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.requestmodels.clinic.PostJobRequestModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.ClinicJobLocationsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.NurseDetailsNReviewsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.myjobs.MyJobDetailsByIDModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.myjobs.MyJobsListModel
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyJobsViewModel : ViewModel() {


    var jobLocation: MutableLiveData<ClinicJobLocationsModel?> = MutableLiveData()

    var nurseListFromCurrentJob = MutableLiveData<List<MyJobDetailsByIDModel.Nurse>>()

    var nurseReviews: MutableLiveData<List<NurseDetailsNReviewsModel.Nurse.NurseReview>?> =
        MutableLiveData()

    var currentJobID = MutableLiveData<Int>()
    var currentClinicID: MutableLiveData<Int> = MutableLiveData()
    var currentNurseID = MutableLiveData<Int>()

    var currentJobDetails: MutableLiveData<MyJobDetailsByIDModel.Job> = MutableLiveData()

    var currentNurseDetails: MutableLiveData<MyJobDetailsByIDModel.Nurse> = MutableLiveData()

    var isJobCanceled = MutableLiveData<Boolean?>()

    var isJobCreatedSuccessfully = MutableLiveData<Boolean>()


    var myJobsListCallback: ((MyJobsListModel) -> Unit)? = null
    var getJobDetailsByIDCallback: ((MyJobDetailsByIDModel) -> Unit)? = null
    var hireActionCallback: ((GenericSuccessErrorModel) -> Unit)? = null



    fun fetchMyJobsList() {
        EliteMedical.retrofitClinic.getMyJobsList()
            .enqueue(object : Callback<MyJobsListModel?> {
                override fun onResponse(
                    call: Call<MyJobsListModel?>,
                    response: Response<MyJobsListModel?>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()!!
                        body.jobs.elementAt(0)

                        myJobsListCallback?.invoke(body)

                    }
                }

                override fun onFailure(call: Call<MyJobsListModel?>, t: Throwable) {
                    println(t.message)
                }
            })
    }

    public fun getJobDetailsByID(jobID: String) {
        val api = EliteMedical.retrofitClinic
        api.getJobDetailsByID(jobID).enqueue(object : Callback<MyJobDetailsByIDModel?> {
            override fun onResponse(
                call: Call<MyJobDetailsByIDModel?>,
                response: Response<MyJobDetailsByIDModel?>
            ) {


                if (response.isSuccessful) {
                    val body = response.body()!!
                    getJobDetailsByIDCallback?.invoke(body)
                }


            }

            override fun onFailure(call: Call<MyJobDetailsByIDModel?>, t: Throwable) {
                println(t.message)
            }
        })
    }

    fun getNurseDetailsInJobApplications(){

    }


    fun getJobLocations() {
        val api = EliteMedical.retrofitClinic
        api.getJobLocations().enqueue(object : Callback<ClinicJobLocationsModel?> {
            override fun onResponse(
                call: Call<ClinicJobLocationsModel?>,
                response: Response<ClinicJobLocationsModel?>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    jobLocation.postValue(responseBody)
                }
            }

            override fun onFailure(call: Call<ClinicJobLocationsModel?>, t: Throwable) {}
        })
    }

    fun getNurseDetailsMyJobs(jobID: String, nurseID: String) {
        val api = EliteMedical.retrofitClinic
        api.getNurseDetailsMyJobs(jobID, nurseID)
            .enqueue(object : Callback<NurseDetailsNReviewsModel?> {
                override fun onResponse(
                    call: Call<NurseDetailsNReviewsModel?>,
                    response: Response<NurseDetailsNReviewsModel?>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()!!
                        val data = responseBody.nurse
                        nurseReviews.postValue(data.nurseReview)
                    }
                }

                override fun onFailure(call: Call<NurseDetailsNReviewsModel?>, t: Throwable) {}
            })
    }

    fun closeJobByID(jobID: String) {
        val api = EliteMedical.retrofitClinic
        api.closeJobByID(jobID)
            .enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    if (response.isSuccessful)
                        isJobCanceled.postValue(true)
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
            })


    }

    fun updateJobID(id: Int) {
        currentJobID.value = id
    }


    fun postJob(jobDetails: PostJobRequestModel) {
        val api = EliteMedical.retrofitClinic
        api.postJob(jobDetails).enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    isJobCreatedSuccessfully.postValue(true)
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {}
        })
    }


    fun callHireAction(hiringDetails: JobHiringActionModel) {
        println(hiringDetails.toString())
        val api = EliteMedical.retrofitClinic
        api.jobHireAction(hiringDetails).enqueue(object : Callback<GenericSuccessErrorModel?> {
            override fun onResponse(
                call: Call<GenericSuccessErrorModel?>,
                response: Response<GenericSuccessErrorModel?>
            ) {
                val res = response
                if (response.isSuccessful) {
                    hireActionCallback?.invoke(response.body()!!)
                } else {
                    val error = response.errorBody()
                    val errorModel =
                        Gson().fromJson(error?.charStream(), GenericSuccessErrorModel::class.java)
                    hireActionCallback?.invoke(errorModel)
                }
            }

            override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
        })
    }


}
