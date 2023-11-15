package com.elite.medical.retrofit.apis.clinic.sidemenu

import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.ClinicNotificationsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardClinicAPIs {

    companion object{
        interface NotificationsCallback {
            fun onListReceived(notification: List<NotificationDetailsFromAdminNotificationsModel>)
        }
        fun getClinicNotifications(callback: DashboardClinicAPIs.Companion.NotificationsCallback){
            val api= EliteMedical.retrofitClinic.create(RetrofitInterfaceClinic::class.java)
            val result= api.getNotifications()
            result.enqueue(object: Callback<ClinicNotificationsModel> {
                override fun onResponse(
                    call: Call<ClinicNotificationsModel>,
                    response: Response<ClinicNotificationsModel>
                ) {
                    if(response.isSuccessful){
                        val resData=response.body()!!
                        val notifnlist=resData.notifications
                        callback.onListReceived(notifnlist)
                    }
                }

                override fun onFailure(call: Call<ClinicNotificationsModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }



}