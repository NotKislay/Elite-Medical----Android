package com.elite.medical.retrofit.apis.admin.sidemenu.approvals

import com.elite.medical.retrofit.RetrofitClient
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ButtonAPIs {
    companion object {
        interface ButtonsCallback {
            fun onSuccess(msg: String)
            fun onResponseErr(msg: String, statusCode: String)
        }

        fun approveUserRequest(
            token: String, email: String, callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .approveUser(token, email).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }
                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

        fun scheduleNurseRequest(
            token: String,
            id: Int,
            scheduleDate: String,
            scheduleTime: String,
            callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .scheduleNurse(token, id, scheduleDate, scheduleTime).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        val res = response
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }
                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }


                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

        fun cancelNurseRequest(
            token: String, id: Int, callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .cancelNurse(token!!, id).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }
                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

        fun cancelClinicRequest(
            token: String, id: Int, callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .cancelClinic(token!!, id).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }
                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

        fun approveJobRequest(
            token: String, id: Int, callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .approveJob(token, id).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }

                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

        fun cancelJobRequest(
            token: String, id: Int, callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .cancelJob(token, id).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }

                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

        fun approveEmploymentRequest(
            token: String, id: Int, action: String, callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .approveEmployment(token, id, action).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }

                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

        fun cancelEmploymentRequest(
            token: String, id: Int, callback: ButtonsCallback
        ) {
            RetrofitClient.getInstance().create(RetrofitInterfaceAdmin::class.java)
                .cancelEmployment(token, id).enqueue(object :
                    Callback<GenericSuccessErrorModel?> {
                    override fun onResponse(
                        call: Call<GenericSuccessErrorModel?>,
                        response: Response<GenericSuccessErrorModel?>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            if (message != null) {
                                callback.onSuccess(message)
                            }

                        } else {
                            val errorBody = response.errorBody()!!
                            val errorModel =
                                Gson().fromJson(
                                    errorBody.string(),
                                    GenericSuccessErrorModel::class.java
                                )
                            callback.onResponseErr(errorModel.message, response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {
                    }
                })
        }

    }
}