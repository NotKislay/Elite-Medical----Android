package com.elite.medical.utils

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request

class MethodsNoLongerInUse {


/*
    name = name.text.toString(),
    email = email.text.toString(),
    address = address.text.toString(),
    mobile = mobile.text.toString(),
    dob = dob.text.toString(),
    city = city.text.toString(),
    state = state.text.toString(),
    zip = zip.text.toString(),
    usImgStatus = spinnerUsImmigrationStatus.selectedItem.toString(),
    nclexStatus = spinnerNCLEXStatus.selectedItem.toString(),
    cgfnsStatus = spinnercgfnStatus.selectedItem.toString(),
    licenseType = spinnerNurseType.selectedItem.toString(),
    uploadLicence = "test",
    licenceIssueDate = licenceIssueDate.text.toString(),
    licenceExpiryDate = licenceExpiryDate.text.toString(),
    yearsExperience = yearsOfExperience.text.toString(),
    speciality = speciality.text.toString()
    */


    suspend fun createNurse(
        name: String,
        email: String,
        address: String,
        mobile: String,
        dob: String,
        city: String,
        state: String,
        zip: String,
        usImgStatus: String,
        nclexStatus: String,
        cgfnsStatus: String,
        licenseType: String,
        uploadLicence: String,
        licenceIssueDate: String,
        licenceExpiryDate: String,
        yearsExperience: String,
        speciality: String,

        ): Int {

        var code = 0

        val job = CoroutineScope(Dispatchers.IO).async {
            val client = OkHttpClient()
            val mediaType = "text/plain".toMediaType()
            val body = MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("email", email)
                .addFormDataPart("mobile", mobile)
                .addFormDataPart("dob", dob)
                .addFormDataPart("address", address)
                .addFormDataPart("city", city)
                .addFormDataPart("state", state)
                .addFormDataPart("zip", zip)
                .addFormDataPart("us_immg_status", usImgStatus)
                .addFormDataPart("nclex_status", nclexStatus)
                .addFormDataPart("cgfns_status", cgfnsStatus)
                .addFormDataPart("license_type", licenseType)
                .addFormDataPart("nurse_license", uploadLicence)
                .addFormDataPart("license_issue", licenceIssueDate)
                .addFormDataPart("license_expiry", licenceExpiryDate)
                .addFormDataPart("experience", yearsExperience)
                .addFormDataPart("speciality", speciality)
                .build()

            val request = Request.Builder()
                .url(Constants.BASE_URL+Constants.REGISTER_NURSE)
                .post(body)
                .build()
            val response = client.newCall(request).execute()
            code = response.code


            // Parse the response body regardless of the status code
            val responseBody = response.body?.string()
            val gson = Gson()
            val jsonResponse =
                gson.fromJson(responseBody, Map::class.java) as Map<String, String>?

            val status = jsonResponse?.get("status") ?: ""
            val message = jsonResponse?.get("message") ?: ""

            
            


        }

        job.await()
        return code
    }
}