package com.elite.medical.nurse.auth

import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.elite.medical.retrofit.testing.ImageUploadModel
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NurseAuthViewModel : ViewModel() {

    var registerNurseCallback: ((GenericSuccessErrorModel?) -> Unit)? = null
    var imageUploadNurseCallback: ((String?) -> Unit)? = null


    fun registerNurse(
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
                    registerNurseCallback?.invoke(body!!)
                } else {
                    val errorBody = response.errorBody()
                    val errorModel = Gson().fromJson(
                        errorBody?.charStream(),
                        GenericSuccessErrorModel::class.java
                    )
                    registerNurseCallback?.invoke(errorModel)
                }
            }

            override fun onFailure(call: Call<GenericSuccessErrorModel>, t: Throwable) {
                println(t.message)
            }
        })

        fun uploadImage(img: String) {
            EliteMedical.retrofitAdmin.create(RetrofitInterfaceAdmin::class.java)
                .uploadImageTest(img).enqueue(object : Callback<ImageUploadModel?> {
                    override fun onResponse(
                        call: Call<ImageUploadModel?>,
                        response: Response<ImageUploadModel?>
                    ) {

                        response

                        if (response.isSuccessful) {
                            imageUploadNurseCallback?.invoke(response.body()?.path)
                        }

                    }

                    override fun onFailure(call: Call<ImageUploadModel?>, t: Throwable) {
                        println(t.message)
                    }
                })
        }


    }
}
