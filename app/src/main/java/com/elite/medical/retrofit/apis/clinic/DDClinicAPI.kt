package com.elite.medical.retrofit.apis.clinic

import android.util.Log
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitClient
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.apis.admin.DDAdminAPI
import com.elite.medical.retrofit.responsemodel.admin.dashboard.ProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.approvals.PostRequestResponseModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.AdminNotificationsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.ClinicNotificationsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.Nurse
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.ClinicProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.nurses.EnrolledNursesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DDClinicAPI {
    companion object {

        interface ResponseCallback {
            fun onDataReceived(data: ClinicDashboardModel?, statusCode: Int?)
        }

        interface ProfileInfoCallback {
            fun onInfoReceived(data: ClinicProfileDetailsModel?, statusCode: Int?)
        }

        interface ProfileUpdateCallback {
            fun onSuccess(msg: String?, statusCode: Int?)
        }


        fun getDashboardData(callback: ResponseCallback) {
            val fetchDashboardDetails =
                EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
            val result = fetchDashboardDetails.getDashboardData()
            result.enqueue(object : Callback<ClinicDashboardModel> {
                override fun onResponse(
                    call: Call<ClinicDashboardModel>,
                    response: Response<ClinicDashboardModel>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (response.isSuccessful) callback.onDataReceived(body, code)
                    else callback.onDataReceived(null, response.code())
                }

                override fun onFailure(call: Call<ClinicDashboardModel>, t: Throwable) {}
            })
        }

        fun getProfileDetails(callback: ProfileInfoCallback) {
            val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
            val result = api.getClinicProfileData()
            result.enqueue(object : Callback<ClinicProfileDetailsModel> {
                override fun onResponse(
                    call: Call<ClinicProfileDetailsModel>,
                    response: Response<ClinicProfileDetailsModel>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (response.isSuccessful) callback.onInfoReceived(body, code)
                    else callback.onInfoReceived(null, response.code())
                }
                override fun onFailure(call: Call<ClinicProfileDetailsModel>, t: Throwable) {}
            })
        }

        fun postUpdatedUserDetails(name: String, email: String, callback: ProfileUpdateCallback) {
            val api = EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
            val result = api.updateProfile(name, email)
            result.enqueue(object : Callback<PostRequestResponseModel?> {
                override fun onResponse(
                    call: Call<PostRequestResponseModel?>,
                    response: Response<PostRequestResponseModel?>
                ) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        callback.onSuccess(message, response.code())
                    } else callback.onSuccess(null, response.code())
                }
                override fun onFailure(call: Call<PostRequestResponseModel?>, t: Throwable) {}
            })
        }




    }
}