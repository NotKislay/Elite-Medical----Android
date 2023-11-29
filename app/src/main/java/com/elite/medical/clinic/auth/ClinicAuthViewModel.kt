package com.elite.medical.clinic.auth

import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitClient
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.requestmodels.RegisterClinicModel
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClinicAuthViewModel : ViewModel() {

    var registerClinicCallback: ((GenericSuccessErrorModel?) -> Unit)? = null


    fun registerClinic1(
        name: String,
        email: String,
        address: String,
        mobile: String,
        dob: String,
        city: String,
        state: String,
        zip: String,
        spinnerNCLEXStatus: String,
        spinnerUsImmigrationStatus: String,
        spinnercgfnStatus: String,
        spinnerNurseType: String,
        nurseLicenceImg: String,
        licenceIssueDate: String,
        licenceExpiryDate: String,
        yearsOfExperience: String,
        speciality: String
    ) {
        EliteMedical.retrofitNurse.registerNurse(
            name,
            email,
            address,
            mobile,
            dob,
            city,
            state,
            zip,
            spinnerNCLEXStatus,
            spinnerUsImmigrationStatus,
            spinnercgfnStatus,
            spinnerNurseType,
            nurseLicenceImg,
            licenceIssueDate,
            licenceExpiryDate,
            yearsOfExperience,
            speciality
        ).enqueue(object : Callback<GenericSuccessErrorModel> {
            override fun onResponse(
                call: Call<GenericSuccessErrorModel>,
                response: Response<GenericSuccessErrorModel>
            ) {
                response
                if (response.isSuccessful) {
                    val body = response.body()
                    registerClinicCallback?.invoke(body!!)
                } else {
                    val errorBody = response.errorBody()
                    val errorModel = Gson().fromJson(
                        errorBody?.charStream(),
                        GenericSuccessErrorModel::class.java
                    )
                    registerClinicCallback?.invoke(errorModel)
                }
            }

            override fun onFailure(call: Call<GenericSuccessErrorModel>, t: Throwable) {
                println(t.message)
            }
        })


    }

    fun registerClinic(
        clinicDetails: RegisterClinicModel
    ) {
        EliteMedical.retrofitAdmin.registerClinic(clinicDetails)
            .enqueue(object : Callback<GenericSuccessErrorModel> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel>,
                    response: Response<GenericSuccessErrorModel>
                ) {
                    response
                    if (response.isSuccessful) {
                        val body = response.body()
                        registerClinicCallback?.invoke(body!!)
                    } else {
                        val errorBody = response.errorBody()
                        val errorModel = Gson().fromJson(
                            errorBody?.charStream(),
                            GenericSuccessErrorModel::class.java
                        )
                        registerClinicCallback?.invoke(errorModel)
                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel>, t: Throwable) {
                    println(t.message)
                }
            })


    }


}