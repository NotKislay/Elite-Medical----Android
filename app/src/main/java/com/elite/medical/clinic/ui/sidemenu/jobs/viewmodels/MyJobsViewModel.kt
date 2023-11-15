package com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.apis.clinic.sidemenu.ClinicSideMenuJobsAPIs
import com.elite.medical.retrofit.requestmodels.clinic.PostJobRequestModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.ClinicJobLocationsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.Job
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.JobRelatedDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.MyJobsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.Nurse
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.NursesAppliedOnJobModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyJobsViewModel : ViewModel() {

    val clinicID: MutableLiveData<String> = MutableLiveData()

    private val jobList = MutableLiveData<MyJobsModel?>()
    var jobLocation: MutableLiveData<ClinicJobLocationsModel?> = MutableLiveData()

    var jobDetailsByID = MutableLiveData<NursesAppliedOnJobModel?>()
    var jobListLiveData: LiveData<MyJobsModel?> = jobList

    var jobID = MutableLiveData<Int>()
    var jobStatus = MutableLiveData<String>()

    lateinit var currentJobDetails: Job
    lateinit var currentNurseDetails: NursesAppliedOnJobModel.Nurse

    var isJobCanceled = MutableLiveData<Boolean?>()

    var isJobCreatedSuccessfully = MutableLiveData<Boolean>()


    init {
        loadJobList()
    }


    fun loadJobList() {
        ClinicSideMenuJobsAPIs.getJobs(object : ClinicSideMenuJobsAPIs.Companion.JobsCallback {
            override fun onJobListReceived(code: Int, body: MyJobsModel?) {
                if (code == 200) jobList.postValue(body)
            }
        })
    }

    fun getJobDetailsByID(id: String) {
        ClinicSideMenuJobsAPIs.getJobsDetailsByID(id,
            object : ClinicSideMenuJobsAPIs.Companion.JobsDetailsCallback {
                override fun onJobDetailsReceived(code: Int, body: NursesAppliedOnJobModel?) {
                    if (code == 200) jobDetailsByID.postValue(body)
                }
            })
    }

    fun getJobLocations() {
        val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
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

    fun closeJobByID(jobID: String) {
        val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
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
        jobID.value = id
    }

    fun updateJobStatus(status: String) {
        jobStatus.value = status
    }

    fun postJob(jobDetails: PostJobRequestModel) {
        val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
        api.postJob(jobDetails).enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    isJobCreatedSuccessfully.postValue(true)
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {}
        })
    }


}
