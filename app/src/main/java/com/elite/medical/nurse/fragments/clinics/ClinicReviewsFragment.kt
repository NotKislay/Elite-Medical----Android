package com.elite.medical.nurse.fragments.clinics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.databinding.FragmentClinicReviewsBinding
import com.elite.medical.nurse.adapters.clinics.ClinicReviewByNurseAdapter
import com.elite.medical.nurse.viewmodels.clinics.ClinicsViewModel
import kotlin.properties.Delegates

class ClinicReviewsFragment : Fragment() {

    private lateinit var binding: FragmentClinicReviewsBinding
    private lateinit var viewModel: ClinicsViewModel
    private var nurseID by Delegates.notNull<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentClinicReviewsBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        viewModel = ViewModelProvider(requireActivity())[ClinicsViewModel::class.java]
        nurseID = viewModel.currentClinicID.value?.toInt() ?: 0

        val reviews = viewModel.reviews.value!!

        /*val inputText = arguments?.getString("key")
        Toast.makeText(requireContext(),inputText,Toast.LENGTH_SHORT).show()*/

        if (reviews.isEmpty()) {
            binding.addReviewLayout.isVisible = true
            binding.rvClinicRevByNurse.isVisible = false

            binding.btnSubmitReview.setOnClickListener {

                viewModel.postReview(
                    nurseID,
                    binding.ratingSubmit.rating.toInt(),
                    binding.addComment.text.toString()
                )
                viewModel.onSuccessPostReviewCallback = {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
                viewModel.onErrorPostReviewCallback = {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }


        } else {

            val rvAdapter = ClinicReviewByNurseAdapter(ArrayList(reviews), requireContext())
            binding.rvClinicRevByNurse.adapter = rvAdapter
            binding.rvClinicRevByNurse.layoutManager = LinearLayoutManager(requireContext())

        }
        binding.loader.visibility = View.GONE

        return binding.root
    }

}