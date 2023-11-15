package com.elite.medical.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.elite.medical.databinding.ModalLayoutClinicDetailsMoreBinding

object HelperMethods {

    private const val CAMERA_PERMISSION_CODE = 100

    fun requestCameraPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf("android.permission.CAMERA"),
            CAMERA_PERMISSION_CODE
        )
    }

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = activity.currentFocus
        if (currentFocusView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }
    }

    fun showDialog(s: String, s1: String, requireContext: Context, activity: FragmentActivity?) {

        val customDialog = Dialog(requireContext)
        val dialogBinding =
            ModalLayoutClinicDetailsMoreBinding.inflate(LayoutInflater.from(requireContext))
        customDialog.setContentView(dialogBinding.root)

        dialogBinding.tvMoreDetails.text = s
        dialogBinding.btnReviewsModal.visibility = View.GONE
        dialogBinding.btnNurseAssocModal.visibility = View.GONE
        dialogBinding.divider1.visibility = View.GONE
        dialogBinding.divider2.visibility = View.GONE
        dialogBinding.btnCancelModal.text = s1
        dialogBinding.btnCancelModal.setOnClickListener {
            activity?.onBackPressed()
            customDialog.dismiss()
        }

        customDialog.show()
    }


}