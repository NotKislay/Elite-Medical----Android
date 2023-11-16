package com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobNApplicantsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.JobsByClinicsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.Nurse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobNApplicantsViewModel : ViewModel() {
    var jobsList: MutableLiveData<List<JobsByClinicsModel.NurseApplicant>?> = MutableLiveData()


    var currentJobDetails: MutableLiveData<Int> = MutableLiveData()

    var currentClinicID: MutableLiveData<Int> = MutableLiveData()
    var currentJobID: MutableLiveData<Int> = MutableLiveData()


    var currentNurseList: MutableLiveData<List<JobsByClinicsModel.NurseApplicant.Nurse>> =
        MutableLiveData()
    var currentNurseDetails: MutableLiveData<JobsByClinicsModel.NurseApplicant.Nurse> =
        MutableLiveData()

    var hiringAction: MutableLiveData<Boolean> = MutableLiveData()

    var HireActionCallback: ((GenericSuccessErrorModel) -> Unit)? = null

    fun getJobsList() {
        val api = EliteMedical.retrofitClinic
        api.getJobNApplicants().enqueue(object : Callback<JobsByClinicsModel?> {
            override fun onResponse(
                call: Call<JobsByClinicsModel?>,
                response: Response<JobsByClinicsModel?>
            ) {
                if (response.isSuccessful) {
                    jobsList.postValue(response.body()?.nurseApplicants)
                    currentClinicID.postValue(response.body()?.clinicId)
                }
            }

            override fun onFailure(call: Call<JobsByClinicsModel?>, t: Throwable) {}
        })
    }

    /*fun getApplicantsDetailsByID(jobID: String, clinicID: String) {
        val api = EliteMedical.retrofitClinic
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
        println(hiringDetails.toString())
        val api = EliteMedical.retrofitClinic
        api.jobHireAction(hiringDetails).enqueue(object : Callback<GenericSuccessErrorModel?> {
            override fun onResponse(
                call: Call<GenericSuccessErrorModel?>,
                response: Response<GenericSuccessErrorModel?>
            ) {
                val res = response
                if (response.isSuccessful) {
                    HireActionCallback?.invoke(response.body()!!)
                } else {
                    val error = response.errorBody()
                    val errorModel =
                        Gson().fromJson(error?.charStream(), GenericSuccessErrorModel::class.java)
                    HireActionCallback?.invoke(errorModel)
                }
            }

            override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
        })
    }

}