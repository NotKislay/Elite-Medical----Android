package com.elite.medical.clinic.ui.dahboardscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.ClinicJobLocationsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClinicDashboardViewModel:ViewModel() {

    var jobLocation: MutableLiveData<ClinicJobLocationsModel?> = MutableLiveData()



    fun fetchJobLocations() {
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

            override fun onFailure(call: Call<ClinicJobLocationsModel?>, t: Throwable) {

            }
        })
    }




}