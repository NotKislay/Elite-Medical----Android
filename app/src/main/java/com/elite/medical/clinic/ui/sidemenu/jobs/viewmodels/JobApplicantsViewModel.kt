package com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.requestmodels.clinic.JobHiringActionModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.applicants.ClinicJobApplicantsModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobApplicantsViewModel : ViewModel() {

    var jobApplicantsList: MutableLiveData<List<ClinicJobApplicantsModel.NurseApplicant>?> =
        MutableLiveData()

    var applicantsList: MutableLiveData<List<ClinicJobApplicantsModel.NurseApplicant.Nurse>> =
        MutableLiveData()

    var currentJobDetails: MutableLiveData<Int> = MutableLiveData()

    var currentClinicID: MutableLiveData<Int> = MutableLiveData()
    val currentJobID: MutableLiveData<Int> = MutableLiveData()
    var currentNurseID: MutableLiveData<Int> = MutableLiveData()


    var isCurrentJobClosed: MutableLiveData<Boolean> = MutableLiveData()


    var currentNurseDetails: MutableLiveData<ClinicJobApplicantsModel.NurseApplicant.Nurse> =
        MutableLiveData()

    var hireActionCallback: ((GenericSuccessErrorModel) -> Unit)? = null



    fun getJobsList() {
        EliteMedical.retrofitClinic.getJobApplicantsList()
            .enqueue(object : Callback<ClinicJobApplicantsModel?> {
                override fun onResponse(
                    call: Call<ClinicJobApplicantsModel?>,
                    response: Response<ClinicJobApplicantsModel?>
                ) {
                    val res = response
                    if (response.isSuccessful) {
                        jobApplicantsList.postValue(response.body()?.nurseApplicants)
                        currentClinicID.postValue(response.body()?.clinicId)
                    }
                }

                override fun onFailure(call: Call<ClinicJobApplicantsModel?>, t: Throwable) {
                    println(t.message)
                }
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