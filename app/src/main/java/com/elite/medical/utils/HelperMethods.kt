package com.elite.medical.utils

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object HelperMethods {

    private const val CAMERA_PERMISSION_CODE = 100
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var location = GPSLocation(null, null, null)


    fun requestCameraPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf("android.permission.CAMERA"),
            CAMERA_PERMISSION_CODE
        )
    }

    fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            CAMERA_PERMISSION_CODE
        )
    }

    fun getLocation(activity: Activity): GPSLocation {
        liveLocation(activity)
        return location
    }


    private fun liveLocation(activity: Activity) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission(activity)
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {



            if (it != null) {
                location.latitude = it.latitude.toString()
                location.longitude = it.longitude.toString()
                location.errorMSG = null
            } else {
                location.latitude = null
                location.longitude = null
                location.errorMSG = "Unable to Fetch GPS Location"

            }


        }


    }

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = activity.currentFocus
        if (currentFocusView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }
    }

    fun showDialog(title: String, cancelText: String, requireContext: Context, activity: FragmentActivity?) {

        val customDialog = Dialog(requireContext)
        val dialogBinding =
            ModalLayoutClinicDetailsMoreBinding.inflate(LayoutInflater.from(requireContext))
        customDialog.setContentView(dialogBinding.root)

        dialogBinding.tvMoreDetails.text = title
        dialogBinding.btnReviewsModal.visibility = View.GONE
        dialogBinding.btnNurseAssocModal.visibility = View.GONE
        dialogBinding.divider1.visibility = View.GONE
        dialogBinding.divider2.visibility = View.GONE
        dialogBinding.btnCancelModal.text = cancelText
        dialogBinding.btnCancelModal.setOnClickListener {
//            activity?.onBackPressed()
            customDialog.dismiss()
        }

        customDialog.show()
    }


}