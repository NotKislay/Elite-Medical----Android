package com.elite.medical.retrofit.apis.admin.sidemenu

import android.util.Log
import com.elite.medical.EliteMedical
import com.elite.medical.retrofit.RetrofitInterfaceAdmin
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ClinicReviewFromClinicReviewModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.ClinicReviewModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.NurseReviewFromNurseReviewModel
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.reviews.NurseReviewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewsAPIs {

    companion object {

        interface NurseReviewsCallback {
            fun onListReceived(reviews: List<NurseReviewFromNurseReviewModel>?, statusCode: Int)
        }

        interface ClinicReviewsCallback {
            fun onListReceived(reviews: List<ClinicReviewFromClinicReviewModel>)
        }

        fun fetchNursesReview(callback: NurseReviewsCallback) {
            val fetchReviewAPI =
                EliteMedical.retrofitAdmin.create(RetrofitInterfaceAdmin::class.java)
            val result = fetchReviewAPI.getNurseReviews()
            result.enqueue(object : Callback<NurseReviewModel> {
                override fun onResponse(
                    call: Call<NurseReviewModel>, response: Response<NurseReviewModel>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val reviewList = responseData!!.nurseReviews
                        callback.onListReceived(reviewList, response.code())
                    } else {
                        val errorData = response.errorBody().toString()
                        callback.onListReceived(null, response.code())
                    }
                }

                override fun onFailure(call: Call<NurseReviewModel>, t: Throwable) {
                }
            })
        }

        fun fetchClinicsReview(callback: ClinicReviewsCallback) {
            val fetchReviewAPI =
                EliteMedical.retrofitAdmin.create(RetrofitInterfaceAdmin::class.java)
            val result = fetchReviewAPI.getClinicReviews()
            result.enqueue(object : Callback<ClinicReviewModel> {
                override fun onResponse(
                    call: Call<ClinicReviewModel>, response: Response<ClinicReviewModel>
                ) {

                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val reviewList = responseData!!.clinicReviews
                        callback.onListReceived(reviewList)
                    } else {
                        val errorData = response.errorBody().toString()
                    }
                }

                override fun onFailure(call: Call<ClinicReviewModel>, t: Throwable) {
                }
            })
        }


    }

}