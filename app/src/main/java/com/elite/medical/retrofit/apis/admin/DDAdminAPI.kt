package com.elite.medical.retrofit.apis.admin

import android.util.Log
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DDAdminAPI {
    companion object {

        interface DashboardDataCallback {
            fun onDataReceived(data: AdminDashboardModel?, statusCode: Int?)
        }

        interface ProfileInfoCallback {
            fun onInfoReceived(data: ProfileDetailsModel?, statusCode: Int?)
        }

        interface ProfileUpdateCallback {
            fun onSuccess(msg: String?, statusCode: Int?)
        }

        fun getDashboardData(callback: DashboardDataCallback) {
            val api = EliteMedical.retrofitAdmin.create(RetrofitInterfaceAdmin::class.java)
            val result = api.getAdminDashboardData()
            result.enqueue(object : Callback<AdminDashboardModel> {
                override fun onResponse(
                    call: Call<AdminDashboardModel>,
                    response: Response<AdminDashboardModel>
                ) {

                    if (!response.isSuccessful) {
                        callback.onDataReceived(null, response.code())
                    }

                    if (response.isSuccessful) {
                        val responseData = response.body()
                        callback.onDataReceived(responseData, response.code())
                    }
                }

                override fun onFailure(call: Call<AdminDashboardModel>, t: Throwable) {
                    
                }

            })

        }

        fun getProfileDetails(callback: ProfileInfoCallback) {
            EliteMedical.AuthTokenAdmin
            val api = EliteMedical.retrofitAdmin.create(RetrofitInterfaceAdmin::class.java)
            val result = api.getProfileData()
            result.enqueue(object : Callback<ProfileDetailsModel> {
                override fun onResponse(
                    call: Call<ProfileDetailsModel>,
                    response: Response<ProfileDetailsModel>
                ) {
                    if (response.isSuccessful) {
                        val resData = response.body()
                        callback.onInfoReceived(resData, response.code())
                    }
                    else {
                        callback.onInfoReceived(null, response.code())
                    }
                }

                override fun onFailure(call: Call<ProfileDetailsModel>, t: Throwable) {
                    
                }
            })
        }

        fun postUpdatedUserDetails(name: String, email: String, callback: ProfileUpdateCallback) {
            val api = EliteMedical.retrofitAdmin.create(RetrofitInterfaceAdmin::class.java)
            val result = api.updateProfile(name, email)
            result.enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        callback.onSuccess(message, response.code())!!

                    } else {
                        callback.onSuccess(null, response.code())
                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    
                }
            })
        }

    }
}