package com.elite.medical.retrofit.requestmodels

data class RegisterClinicModel(

    var name: String,
    var email: String,
    var address: String,
    var mobile: String,
    var city: String,
    var state: String,
    var zip: String,
    var clinic_type: String,
    var locations: String,
    var clinic_license: String,
    var vat_no: String,
    var cst_no: String,
    var service_tax_no: String,
    var uin_no: String,
    var declaration: String,


)
