package com.elite.medical.admin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ProfileDetailsModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminViewModel : ViewModel() {

    val dashboardData: MutableLiveData<AdminDashboardModel> = MutableLiveData()


    var getDashboardDataCallback: ((AdminDashboardModel) -> Unit)? = null
    var getAdminProfileDetailsCallback: ((ProfileDetailsModel) -> Unit)? = null
    var updateAdminProfileDetailsCallback: ((GenericSuccessErrorModel) -> Unit)? = null


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

    fun getAdminProfileDetails() {
        EliteMedical.retrofitAdmin.getProfileData()
            .enqueue(object : Callback<ProfileDetailsModel> {
                override fun onResponse(
                    call: Call<ProfileDetailsModel>,
                    response: Response<ProfileDetailsModel>
                ) {
                    if (response.isSuccessful) {
                        val resData = response.body()!!
                        getAdminProfileDetailsCallback?.invoke(resData)
                    }
                }

                override fun onFailure(call: Call<ProfileDetailsModel>, t: Throwable) {}
            })


    }

    fun updateAdminProfileDetails(name: String, email: String) {
        EliteMedical.retrofitAdmin.updateProfile(name, email)
            .enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    val res = response
                    if (response.isSuccessful) {

                        val body = response.body()!!
                        updateAdminProfileDetailsCallback?.invoke(body)

                    } else {
                        val error = response.errorBody()!!
                        val errorModel = Gson().fromJson(
                            error.toString(),
                            GenericSuccessErrorModel::class.java
                        )
                        updateAdminProfileDetailsCallback?.invoke(errorModel)
                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
            })
    }

}