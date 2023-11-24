package com.elite.medical.retrofit.apis

import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitClient
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.RetrofitInterfaceClinic
import com.elite.medical.retrofit.requestmodels.LoginModel
import com.elite.medical.retrofit.requestmodels.RegisterClinicModel
import com.elite.medical.retrofit.requestmodels.RegisterNurseModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.responsemodel.auth.LoginResponse
import com.elite.medical.retrofit.responsemodel.auth.LoginResponseModel
import com.elite.medical.retrofit.responsemodel.auth.LogoutModel
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthAPI {
    companion object {

        interface AuthLoginCallback {
            fun onLoginResult(loginResponse: LoginResponse)

        }

        interface LoginClinicCallback {
            fun onClinicLogin(message: String?)
        }

        interface AuthSignUpCallbackNurse {
            fun onSignUpSuccess(message: String)
        }

        interface AuthLogoutCallback {
            fun onLogoutSuccess(message: String)
        }

        fun loginGeneric(
            email: String,
            password: String,
            callback: AuthLoginCallback
        ) {
            val loginApi = EliteMedical.retrofitAdmin.create(RetrofitInterfaceAdmin::class.java)
            val userDetails = LoginModel(email, password)
            val result = loginApi.login(userDetails)
            result.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()?.string()
                        val gson = Gson()
                        val jsonResponse =
                            gson.fromJson(responseBody, Map::class.java) as Map<String, String>?

                        val status = jsonResponse?.get("status") ?: ""
                        val message = jsonResponse?.get("message") ?: ""
                        val token = jsonResponse?.get("token") ?: ""

                        val callBackData = LoginResponse(status, message, token)
                        callback.onLoginResult(callBackData)

                    } else {

                        val responseBody = response.errorBody()?.string()
                        val gson = Gson()
                        val jsonResponse =
                            gson.fromJson(responseBody, Map::class.java) as Map<String, String>?

                        val status = jsonResponse?.get("status") ?: ""
                        val message = jsonResponse?.get("message") ?: ""

                        val callBackData = LoginResponse(status, message, null)






                        callback.onLoginResult(callBackData)


                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            })
        }

        fun loginClinic(
            email: String,
            password: String,
            callback: LoginClinicCallback
        ) {
            val loginApi = EliteMedical.retrofitAdmin.create(RetrofitInterfaceClinic::class.java)
            val userDetails = LoginModel(email, password)
            val result = loginApi.loginClinic(userDetails)
            result.enqueue(object : Callback<LoginResponseModel?> {
                override fun onResponse(
                    call: Call<LoginResponseModel?>,
                    response: Response<LoginResponseModel?>
                ) {

                    if (response.isSuccessful) {
                        val body = response.body()!!
                        EliteMedical.updateClinicToken(body.token)
                        callback.onClinicLogin(null)
                    } else if (response.code() == 400) {

                        val errorBody = response.errorBody()!!
                        val errorModel =
                            Gson().fromJson(
                                errorBody.string(),
                                GenericSuccessErrorModel::class.java
                            )
                        callback.onClinicLogin(errorModel.message)
                    }

                }

                override fun onFailure(call: Call<LoginResponseModel?>, t: Throwable) {

                }
            })
        }




        fun logout(
            token: String,
            callback: AuthLogoutCallback
        ) {
            val logoutAPI = RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
            val result = logoutAPI.logout("Bearer $token")
            result.enqueue(object : Callback<LogoutModel?> {
                override fun onResponse(
                    call: Call<LogoutModel?>,
                    response: Response<LogoutModel?>
                ) {

                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        callback.onLogoutSuccess(message!!)
                    }
                }

                override fun onFailure(call: Call<LogoutModel?>, t: Throwable) {

                }
            })
        }


    }
}