package com.elite.medical.admin.fragments.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.admin.viewmodels.AdminViewModel
import com.elite.medical.databinding.FragmentAdminProfileBinding

class AdminProfileFragment : Fragment() {

    private lateinit var binding: FragmentAdminProfileBinding
    private lateinit var viewModel: AdminViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[AdminViewModel::class.java]
        viewModel.getAdminProfileDetails()


        hideAllItems()
        displayProfileDetails()

        viewModel.updateAdminProfileDetailsCallback = {

            if (it.status == "success") {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                activity?.onBackPressedDispatcher?.onBackPressed()

            } else {
                Toast.makeText(
                    requireContext(), it.message, Toast.LENGTH_SHORT
                ).show()
            }

        }




        binding.btnUpdateProfile.setOnClickListener { updateProfile() }
        binding.btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        return binding.root
    }

    private fun updateProfile() {
        val name = binding.etProfileName.text.toString()
        val email = binding.etProfileEmail.text.toString()
        viewModel.updateAdminProfileDetails(name, email)
    }

    private fun displayProfileDetails() {

        viewModel.getAdminProfileDetailsCallback = { data ->

            binding.btnUpdateProfile.visibility = View.VISIBLE
            binding.labelEmail.visibility = View.VISIBLE
            binding.labelName.visibility = View.VISIBLE
            binding.labelProfInfo.visibility = View.VISIBLE
            binding.etProfileName.visibility = View.VISIBLE
            binding.etProfileEmail.visibility = View.VISIBLE


            val userName = binding.etProfileName
            val userEmail = binding.etProfileEmail
            userName.setText(data.user.name)
            userEmail.setText(data.user.email)
            binding.loader.visibility = View.GONE


            userEmail.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrBlank()) {
                    userEmail.error = "This can't be blank"
                    binding.btnUpdateProfile.isEnabled = false
                } else
                    binding.btnUpdateProfile.isEnabled = true

            }

            userName.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrBlank()) {
                    userName.error = "This can't be blank"
                    binding.btnUpdateProfile.isEnabled = false
                } else
                    binding.btnUpdateProfile.isEnabled = true

            }

        }

    }

    private fun hideAllItems() {
        binding.btnUpdateProfile.isVisible = false
        binding.labelEmail.isVisible = false
        binding.labelName.isVisible = false
        binding.labelProfInfo.isVisible = false
        binding.etProfileName.isVisible = false
        binding.etProfileEmail.isVisible = false
    }

}