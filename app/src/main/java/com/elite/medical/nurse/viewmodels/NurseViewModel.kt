package com.elite.medical.nurse.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.nurse.home.NurseTimeSheetModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NurseViewModel : ViewModel() {

    var timeSheetCallback: ((List<NurseTimeSheetModel.Timesheet>?, GenericSuccessErrorModel?) -> Unit)? =
        null

    fun getTimeSheets() {
        EliteMedical.retrofitNurse.getTimesheet()
            .enqueue(object : Callback<NurseTimeSheetModel?> {
                override fun onResponse(
                    call: Call<NurseTimeSheetModel?>,
                    response: Response<NurseTimeSheetModel?>
                ) {
                    var result: NurseTimeSheetModel? = null
                    var errorModel: GenericSuccessErrorModel? = null
                    if (response.isSuccessful) {
                        result = response.body()!!
                    } else {
                        val errorBody = response.errorBody()
                        errorModel = Gson().fromJson(
                            errorBody?.charStream(),
                            GenericSuccessErrorModel::class.java
                        )
                    }

                    timeSheetCallback?.invoke(result?.timesheets, errorModel)


                }

                override fun onFailure(call: Call<NurseTimeSheetModel?>, t: Throwable) {}
            })
    }

}