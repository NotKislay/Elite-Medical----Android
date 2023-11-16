package com.elite.medical.clinic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.clinic.viewmodels.ClinicViewModel
import com.elite.medical.databinding.FragmentProfileClinicBinding
import com.elite.medical.retrofit.responsemodel.clinic.ClinicProfileDetailsModel
import com.elite.medical.utils.HelperMethods


class ProfileClinicFragment : Fragment() {
    private lateinit var binding: FragmentProfileClinicBinding
    private lateinit var viewModel: ClinicViewModel
    private lateinit var profileDetails: ClinicProfileDetailsModel
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var submit: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileClinicBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ClinicViewModel::class.java]

        email = binding.etProfileEmail
        name = binding.etProfileName
        submit = binding.btnUpdateProfile

        viewModel.getProfileDetails()

        viewModel.profileDetailsCallback = {
            profileDetails = it
            setProfileData()
        }

        viewModel.updateProfileCallback = {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }




        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

    private fun setProfileData() {
        binding.loader.visibility = View.GONE
        name.setText(profileDetails.user.name)
        email.setText(profileDetails.user.email)

        email.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                email.error = "This can't be blank"
            }
            binding.btnUpdateProfile.isEnabled = !text.isNullOrBlank()
        }

        name.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                name.error = "This can't be blank"
            }
            binding.btnUpdateProfile.isEnabled = !text.isNullOrBlank()
        }

        submit.setOnClickListener { updateProfile(name.text.toString(), email.text.toString()) }


    }

    private fun updateProfile(name: String, email: String) {
        HelperMethods.hideKeyboard(requireActivity())
        viewModel.updateProfile(name, email)
    }

}