package com.elite.medical.clinic.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.ClinicNotificationsModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel
import com.elite.medical.retrofit.responsemodel.clinic.ClinicProfileDetailsModel
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClinicViewModel : ViewModel() {

    var dashboardDataCallback: ((ClinicDashboardModel) -> Unit)? = null
    var profileDetailsCallback: ((ClinicProfileDetailsModel) -> Unit)? = null
    var updateProfileCallback: ((GenericSuccessErrorModel) -> Unit)? = null
    var clinicNotificationCallback: ((List<NotificationDetailsFromAdminNotificationsModel>) -> Unit)? =
        null

    var recentJobApplicants: MutableLiveData<List<ClinicDashboardModel.NurseApplicant>> =
        MutableLiveData()

    var topNurses: MutableLiveData<List<ClinicDashboardModel.TopNurse>> = MutableLiveData()


    fun getDashboardData() {
        EliteMedical.retrofitClinic.getDashboardData()
            .enqueue(object : Callback<ClinicDashboardModel> {
                override fun onResponse(
                    call: Call<ClinicDashboardModel>,
                    response: Response<ClinicDashboardModel>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        dashboardDataCallback?.invoke(body!!)
                        recentJobApplicants.postValue(body!!.nurseApplicants)
                        topNurses.postValue(body.topNurses)
                    }
                }

                override fun onFailure(call: Call<ClinicDashboardModel>, t: Throwable) {}
            })
    }

    fun getProfileDetails() {
        EliteMedical.retrofitClinic
            .getClinicProfileData()
            .enqueue(object : Callback<ClinicProfileDetailsModel> {
                override fun onResponse(
                    call: Call<ClinicProfileDetailsModel>,
                    response: Response<ClinicProfileDetailsModel>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        profileDetailsCallback?.invoke(body!!)
                    }
                }

                override fun onFailure(call: Call<ClinicProfileDetailsModel>, t: Throwable) {}
            })
    }

    fun updateProfile(name: String, email: String) {
        EliteMedical.retrofitClinic.updateProfile(name, email)
            .enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    if (response.isSuccessful) {
                        updateProfileCallback?.invoke(response.body()!!)
                    } else {
                        val errorBody = response.errorBody()
                        val responseModel = Gson().fromJson(
                            errorBody?.charStream(),
                            GenericSuccessErrorModel::class.java
                        )
                        updateProfileCallback?.invoke(responseModel)
                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
            })
    }

    fun getClinicNotifications() {
        EliteMedical.retrofitClinic.getNotifications()
            .enqueue(object : Callback<ClinicNotificationsModel> {
                override fun onResponse(
                    call: Call<ClinicNotificationsModel>,
                    response: Response<ClinicNotificationsModel>
                ) {
                    if (response.isSuccessful) {
                        val resData = response.body()!!
                        val notifications = resData.notifications
                        clinicNotificationCallback?.invoke(notifications)
                    }
                }

                override fun onFailure(call: Call<ClinicNotificationsModel>, t: Throwable) {}

            })
    }


}