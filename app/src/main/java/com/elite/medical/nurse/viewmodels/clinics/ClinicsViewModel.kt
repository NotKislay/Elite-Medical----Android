package com.elite.medical.nurse.viewmodels.clinics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ClinicDetailsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.Clinics
import com.elite.medical.retrofit.responsemodel.nurse.clinics.EnrolledClinicsModel
import com.elite.medical.retrofit.responsemodel.nurse.clinics.ReviewEnrolledClinic
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


    fun getClinicDetailsbyID(id: String){
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
}