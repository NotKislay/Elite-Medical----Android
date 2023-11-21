package com.elite.medical.clinic.viewmodels.sidemenu

import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.SearchNurseModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchJobsViewModel : ViewModel() {

    var searchNurseCallback: ((data: SearchNurseModel?, error: GenericSuccessErrorModel?) -> Unit)? =
        null


    fun getAvailableSearchNurses() {
        EliteMedical.retrofitClinic.getListOfAvailableSearchNurses()
            .enqueue(object : Callback<SearchNurseModel?> {
                override fun onResponse(
                    call: Call<SearchNurseModel?>,
                    response: Response<SearchNurseModel?>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        searchNurseCallback?.invoke(data, null)
                    } else {
                        val errorBody = response.errorBody()!!
                        val errorModel = Gson().fromJson(
                            errorBody.charStream(),
                            GenericSuccessErrorModel::class.java
                        )
                        searchNurseCallback?.invoke(null, errorModel)
                    }
                }

                override fun onFailure(call: Call<SearchNurseModel?>, t: Throwable) {}

            })
    }


}