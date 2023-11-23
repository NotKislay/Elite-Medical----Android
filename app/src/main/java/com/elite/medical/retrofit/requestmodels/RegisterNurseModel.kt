package com.elite.medical.retrofit.requestmodels

import okhttp3.MultipartBody
import retrofit2.http.Part

data class RegisterNurseModel(
    @Part
    var name: String,
    @Part
    var email: String,
    @Part
    var address: String,
    @Part
    var mobile: String,
    @Part
    var dob: String,
    @Part
    var city: String,
    @Part
    var state: String,
    @Part
    var zip: String,
    @Part
    var nclex_status: String,
    @Part
    var us_immg_status: String,
    @Part
    var cgfns_status: String,
    @Part
    var license_type: String,
    @Part
    var nurse_license: MultipartBody.Part,
    @Part
    var license_issue: String,
    @Part
    var license_expiry: String,
    @Part
    var experience: String,
    @Part
    var speciality: String,
)