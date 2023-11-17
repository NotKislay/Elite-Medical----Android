package com.elite.medical.clinic.viewmodels.sidemenu

import androidx.lifecycle.ViewModel
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.responsemodel.GenericSuccessErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NursesViewModel : ViewModel() {

    var onSuccessPostReviewCallback: ((GenericSuccessErrorModel)-> Unit)? = null
    var onErrorPostReviewCallback: ((GenericSuccessErrorModel)-> Unit)? = null
    fun postClinicReview(nurseID: String, comment: String, rating: Int) {
        EliteMedical.retrofitClinic.postNurseReview(nurseID, comment, rating)
            .enqueue(object : Callback<GenericSuccessErrorModel?> {
                override fun onResponse(
                    call: Call<GenericSuccessErrorModel?>,
                    response: Response<GenericSuccessErrorModel?>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()!!
                        onSuccessPostReviewCallback?.invoke(body)
                    } else {
                        val errorBody = response.errorBody()!!
                        val errorModel = Gson().fromJson(
                            errorBody.charStream(),
                            GenericSuccessErrorModel::class.java
                        )

                        onErrorPostReviewCallback?.invoke(errorModel)

                    }
                }

                override fun onFailure(call: Call<GenericSuccessErrorModel?>, t: Throwable) {}
            })
    }


}