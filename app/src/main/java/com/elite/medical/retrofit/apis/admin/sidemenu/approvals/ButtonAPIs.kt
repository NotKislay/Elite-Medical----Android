package com.elite.medical.retrofit.apis.admin.sidemenu.approvals

import com.elite.medical.EliteMedical
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
            email: String, callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.approveUser(email).enqueue(object :
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
            id: Int,
            scheduleDate: String,
            scheduleTime: String,
            callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.scheduleNurse( id, scheduleDate, scheduleTime)
                .enqueue(object :
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
            id: Int, callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.cancelNurse( id).enqueue(object :
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
            id: Int, callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.cancelClinic( id).enqueue(object :
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
            id: Int, callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.approveJob( id).enqueue(object :
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
            id: Int, callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.cancelJob( id).enqueue(object :
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
            id: Int, action: String, callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.approveEmployment( id, action).enqueue(object :
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
            id: Int, callback: ButtonsCallback
        ) {
            EliteMedical.retrofitAdmin.cancelEmployment( id).enqueue(object :
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