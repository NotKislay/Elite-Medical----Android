package com.elite.medical.admin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminViewModel : ViewModel() {

    val dashboardData: MutableLiveData<AdminDashboardModel> = MutableLiveData()
    var getDashboardDataCallback: ((AdminDashboardModel) -> Unit)? = null


    fun getDashboardData() {
        EliteMedical.retrofitAdmin.getAdminDashboardData()
            .enqueue(object : Callback<AdminDashboardModel> {
                override fun onResponse(
                    call: Call<AdminDashboardModel>,
                    response: Response<AdminDashboardModel>
                ) {

                    if (response.isSuccessful) {
                        val body = response.body()!!
                        getDashboardDataCallback?.invoke(body)
                    }
                }

                override fun onFailure(call: Call<AdminDashboardModel>, t: Throwable) {}

            })

    }

}