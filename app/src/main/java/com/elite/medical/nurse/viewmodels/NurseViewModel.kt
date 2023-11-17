package com.elite.medical.nurse.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.nurse.home.DashboardDataNurseModel
import com.elite.medical.retrofit.responsemodel.nurse.home.NurseTimeSheetModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NurseViewModel : ViewModel() {

    var timeSheetCallback: ((List<NurseTimeSheetModel.Timesheet>?, GenericSuccessErrorModel?) -> Unit)? =
        null
    var nurseDashboardDataCallback: ((DashboardDataNurseModel) -> Unit)? = null

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

    fun getNurseDashboardData() {
        EliteMedical.retrofitNurse.getNurseDashboardData()
            .enqueue(object : Callback<DashboardDataNurseModel?> {

                override fun onResponse(
                    call: Call<DashboardDataNurseModel?>,
                    response: Response<DashboardDataNurseModel?>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        nurseDashboardDataCallback?.invoke(body!!)
                    }
                }

                override fun onFailure(call: Call<DashboardDataNurseModel?>, t: Throwable) {
                    println(t.message)
                }
            })
    }



}