package com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobNApplicantsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.Nurse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobNApplicantsViewModel : ViewModel() {
    var jobsList: MutableLiveData<JobNApplicantsModel?> = MutableLiveData()

    var currentJobID: MutableLiveData<Int> = MutableLiveData()
    var currentJobDetails: MutableLiveData<Int> = MutableLiveData()
    var currentClinicID: MutableLiveData<Int> = MutableLiveData()
    var currentNurseList: MutableLiveData<List<Nurse>> = MutableLiveData()
    var currentNurseDetails: MutableLiveData<Nurse> = MutableLiveData()

    var hiringAction: MutableLiveData<Boolean> = MutableLiveData()

    fun getJobsList() {
        val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
        api.getJobNApplicants().enqueue(object : Callback<JobNApplicantsModel?> {
            override fun onResponse(
                call: Call<JobNApplicantsModel?>,
                response: Response<JobNApplicantsModel?>
            ) {
                if (response.isSuccessful) jobsList.postValue(response.body())
            }

            override fun onFailure(call: Call<JobNApplicantsModel?>, t: Throwable) {}
        })
    }

    /*fun getApplicantsDetailsByID(jobID: String, clinicID: String) {
        val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
        api.getApplicantsDetailsByClinicNJobID(jobID, clinicID).enqueue(object : Callback<ApplicantsDetailsModel?> {
            override fun onResponse(
                call: Call<ApplicantsDetailsModel?>,
                response: Response<ApplicantsDetailsModel?>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    currentNurseDetails.postValue(body.nurseWithExtraDetails)
                }

            }

            override fun onFailure(call: Call<ApplicantsDetailsModel?>, t: Throwable) {

            }
        })
    }*/


    fun callHireAction(hiringDetails: JobHiringActionModel) {
        val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
        api.jobHireAction(hiringDetails).enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) hiringAction.postValue(true)
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {}
        })
    }

}