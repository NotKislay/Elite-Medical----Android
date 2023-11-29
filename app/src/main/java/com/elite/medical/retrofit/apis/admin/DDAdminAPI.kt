package com.elite.medical.retrofit.apis.admin

import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ProfileDetailsModel
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
            EliteMedical.retrofitAdmin.getAdminDashboardData()
                .enqueue(object : Callback<AdminDashboardModel> {
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
            EliteMedical.retrofitAdmin.getProfileData()
                .enqueue(object : Callback<ProfileDetailsModel> {
                    override fun onResponse(
                        call: Call<ProfileDetailsModel>,
                        response: Response<ProfileDetailsModel>
                    ) {
                        if (response.isSuccessful) {
                            val resData = response.body()
                            callback.onInfoReceived(resData, response.code())
                        } else {
                            callback.onInfoReceived(null, response.code())
                        }
                    }

                    override fun onFailure(call: Call<ProfileDetailsModel>, t: Throwable) {

                    }
                })
        }

        fun postUpdatedUserDetails(name: String, email: String, callback: ProfileUpdateCallback) {
            EliteMedical.retrofitAdmin.updateProfile(name, email)
                .enqueue(object : Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            callback.onSuccess(message, response.code())

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