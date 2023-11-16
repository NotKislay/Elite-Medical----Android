package com.elite.medical.nurse.viewmodels.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.nurse.dashboard.notification.NotificationModel
import com.elite.medical.retrofit.responsemodel.nurse.dashboard.profile.NurseProfileDetailsModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {


    var notificationDetails: MutableLiveData<List<NotificationModel.Notification>> =
        MutableLiveData()
    var profileDetails: MutableLiveData<NurseProfileDetailsModel.User> = MutableLiveData()

    var onError: ((GenericSuccessErrorModel) -> Unit)? = null
    var onSuccess: ((GenericSuccessErrorModel) -> Unit)? = null


    fun getNotificationLists() {
        EliteMedical.retrofitNurse.getNurseNotifications()
            .enqueue(object : Callback<NotificationModel> {
                override fun onResponse(
                    call: Call<NotificationModel>, response: Response<NotificationModel>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        notificationDetails.postValue(data.notifications)
                    }
                }

                override fun onFailure(call: Call<NotificationModel>, t: Throwable) {}

            })
    }


    fun getProfileData() {
        EliteMedical.retrofitNurse.getProfileData()
            .enqueue(object : Callback<NurseProfileDetailsModel> {
                override fun onResponse(
                    call: Call<NurseProfileDetailsModel>,
                    response: Response<NurseProfileDetailsModel>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        profileDetails.postValue((data.user))
                    }
                }

                override fun onFailure(call: Call<NurseProfileDetailsModel>, t: Throwable) {}

            })
    }

    fun updateProfile(name: String, email: String) {
        EliteMedical.retrofitNurse.updateProfile(name, email)
            .enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        onSuccess?.invoke(body!!)

                    } else {
                        val errorBody = response.errorBody()!!
                        val errorModel = Gson().fromJson(
                            errorBody.string(), GenericSuccessErrorModel::class.java
                        )
                        onError?.invoke(errorModel)

                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
            })
    }

}