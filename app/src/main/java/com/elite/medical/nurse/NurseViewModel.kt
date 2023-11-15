package com.elite.medical.nurse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.RetrofitInterfaceNurse
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.ClinicJobLocationsModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.Job
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobDetailModel
import com.elite.medical.retrofit.responsemodel.nurse.jobs.searchjobs.JobList
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.Clinics
import com.elite.medical.retrofit.responsemodel.nurse.clinics.EnrolledClinicsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ReviewEnrolledClinic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NurseViewModel : ViewModel() {


    var jobList: MutableLiveData<List<Job>?> = MutableLiveData()
    var jobDetail: MutableLiveData<Job?> = MutableLiveData()


    var currentJobID: MutableLiveData<String> = MutableLiveData()



    fun getJobsList() {
        EliteMedical.retrofitNurse.searchJobs().enqueue(object : Callback<JobList?> {
            override fun onResponse(call: Call<JobList?>, response: Response<JobList?>) {
                val res = response
                if (response.isSuccessful) {
                    val list = response.body()?.jobs
                    jobList.postValue(list)
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

                    if (response.isSuccessful) {
                        val details = response.body()?.jobs
                        jobDetail.postValue(details)
                    }
                }

                override fun onFailure(call: Call<JobDetailModel?>, t: Throwable) {}
            })
    }


    fun updateCurrentJobID(id: String) = currentJobID.postValue(id)

}