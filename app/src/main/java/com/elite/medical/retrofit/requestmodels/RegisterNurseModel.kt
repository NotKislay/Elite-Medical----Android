package com.elite.medical.retrofit.requestmodels

data class RegisterNurseModel(
    var name: String,
    var email: String,
    var address: String,
    var mobile: String,
    var dob: String,
    var city: String,
    var state: String,
    var zip: String,
    var nclex_status: String,
    var us_immg_status: String,
    var cgfns_status: String,
    var license_type: String,
    var nurse_license: String,
    var license_issue: String,
    var license_expiry: String,
    var experience: String,
    var speciality: String,
)