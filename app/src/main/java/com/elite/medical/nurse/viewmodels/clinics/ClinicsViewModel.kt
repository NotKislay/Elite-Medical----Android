package com.elite.medical.nurse.viewmodels.clinics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.PostRequestResponseModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.Clinics
import com.elite.medical.retrofit.responsemodel.nurse.clinics.EnrolledClinicsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ReviewEnrolledClinic
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClinicsViewModel: ViewModel()  {

    var clinicList: MutableLiveData<List<Clinics>?> = MutableLiveData()

    //Enrolled clinic details part
    var enrolledClinicDetails: MutableLiveData<ClinicDetailsModel> = MutableLiveData()

    //passed data to fragments
    var currentClinicID = MutableLiveData<String>()
    var reviews : MutableLiveData<List<ReviewEnrolledClinic>?> = MutableLiveData()

    var onErrorPostReviewCallback: ((GenericSuccessErrorModel)-> Unit)? = null
    var onSuccessPostReviewCallback : ((PostRequestResponseModel)-> Unit)? = null





    fun getEnrolledClinicsList() {
        EliteMedical.retrofitNurse.getEnrolledClinics()
            .enqueue(object : Callback<EnrolledClinicsModel> {
                override fun onResponse(
                    call: Call<EnrolledClinicsModel>,
                    response: Response<EnrolledClinicsModel>
                ) {
                    if (response.isSuccessful) {
                        val clinics = response.body()?.clinics
                        clinicList.postValue(clinics)
                    }
                }

                override fun onFailure(call: Call<EnrolledClinicsModel>, t: Throwable) {
                }

            })
    }


    fun getClinicDetailsByID(id: String){
        EliteMedical.retrofitNurse.getClinicDetails(id).enqueue(object : Callback<ClinicDetailsModel>{
            override fun onResponse(
                call: Call<ClinicDetailsModel>,
                response: Response<ClinicDetailsModel>
            ) {
                if(response.isSuccessful){
                    val data = response.body()!!
                    enrolledClinicDetails.postValue(data)
                }
            }

            override fun onFailure(call: Call<ClinicDetailsModel>, t: Throwable) { }

        })
    }

    fun postReview(nurseId: Int, rating: Int, comment: String) {

        EliteMedical.retrofitNurse.storeNurseReview(nurseId, rating, comment)
            .enqueue(object : Callback<PostRequestResponseModel?> {
                override fun onResponse(
                    call: Call<PostRequestResponseModel?>,
                    response: Response<PostRequestResponseModel?>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        onSuccessPostReviewCallback?.invoke(body!!)

                    } else {
                        val errorBody = response.errorBody()!!
                        val errorModel = Gson().fromJson(
                            errorBody.charStream(), GenericSuccessErrorModel::class.java
                        )
                        onErrorPostReviewCallback?.invoke(errorModel)

                    }
                }

                override fun onFailure(call: Call<PostRequestResponseModel?>, t: Throwable) {}
            })
    }


}