package com.elite.medical.clinic.fragments.jobs.myjobs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback

import androidx.lifecycle.ViewModelProvider
import com.elite.medical.R
import com.elite.medical.clinic.adapter.NurseReviewsAdapterMyJobs
import com.elite.medical.clinic.adapter.NurseReviewsMyJobsAdapter
import com.elite.medical.clinic.ui.sidemenu.jobs.viewmodels.MyJobsViewModel
import com.elite.medical.databinding.FragmentNurseRatingBinding
import com.elite.medical.retrofit.responsemodel.clinic.sidemenu.jobs.myjobs.MyJobDetailsByIDModel
import com.elite.medical.utils.HelperMethods


class NurseRatingFragment : Fragment() {
    private lateinit var binding: FragmentNurseRatingBinding
    private lateinit var viewModel: MyJobsViewModel
    private lateinit var nurseID: String
    private lateinit var jobID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNurseRatingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MyJobsViewModel::class.java]

        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        nurseID = viewModel.currentNurseID.value.toString()
        jobID = viewModel.currentJobID.value.toString()

        viewModel.getNurseDetailsMyJobs(jobID, nurseID)
        setReviews(viewModel.currentNurseDetails.value!!.reviews)
        return binding.root
    }

    private fun setReviews(reviews: List<MyJobDetailsByIDModel.Nurse.Review>) {


        if (reviews.isNotEmpty()) {
            val adapterNR = NurseReviewsAdapterMyJobs(reviews, requireContext())
            binding.rvNurseRevMyJobs.adapter = adapterNR
        } else {
//            HelperMethods.showDialog(
//                "No Review found!",
//                "Go back",
//                requireContext(),
//                requireActivity(),
//                activity?.onBackPressedDispatcher?.onBackPressed()
//            )
            //Toast.makeText(requireActivity(), "No reviews", Toast.LENGTH_SHORT).show()
            displayDialogWhenNoReviews()

        }
        binding.loader.isVisible = false


    }
    private fun displayDialogWhenNoReviews(){
        val customDialog = Dialog(requireContext())
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        // Customize the dialog components
        val modaltitle= customDialog.findViewById<TextView>(R.id.tv_more_details)
        modaltitle.text="No Reviews !"
        val okBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
        okBtn.text = "Ok"
        val nurseAssocBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
        nurseAssocBtn.visibility=View.GONE
        val reviewsBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)
        reviewsBtn.visibility=View.GONE
        customDialog.findViewById<View>(R.id.divider1).visibility=View.GONE
        customDialog.findViewById<View>(R.id.divider2).visibility=View.GONE

        okBtn.setOnClickListener {
            customDialog.dismiss()
            requireActivity().onBackPressed()
        }
        customDialog.show()

    }


}