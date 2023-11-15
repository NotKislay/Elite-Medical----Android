package com.elite.medical.nurse.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elite.medical.databinding.FragmentProfileBinding
import com.elite.medical.nurse.viewmodels.dashboard.DashboardViewModel


class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewmodel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        viewmodel = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]





        binding.btnUpdateProfile.visibility = View.GONE
        binding.labelEmail.visibility = View.GONE
        binding.labelName.visibility = View.GONE
        binding.labelProfInfo.visibility = View.GONE
        binding.etProfileName.visibility = View.GONE
        binding.etProfileEmail.visibility = View.GONE




        fetchProfileData()
        return binding.root
    }

    private fun fetchProfileData() {

        viewmodel.getProfileData()
        viewmodel.profileDetails.observe(viewLifecycleOwner) {

            binding.loader.isVisible = false
            binding.btnUpdateProfile.visibility = View.VISIBLE
            binding.labelEmail.visibility = View.VISIBLE
            binding.labelName.visibility = View.VISIBLE
            binding.labelProfInfo.visibility = View.VISIBLE
            binding.etProfileName.visibility = View.VISIBLE
            binding.etProfileEmail.visibility = View.VISIBLE


            val userName = binding.etProfileName
            val userEmail = binding.etProfileEmail
            userName.setText(it.name)
            userEmail.setText(it.email)


        }

        binding.btnUpdateProfile.setOnClickListener {
            val name = binding.etProfileName.text.toString()
            val email = binding.etProfileEmail.text.toString()
            postUpdatedCredentialsToAPI(name, email)
        }
    }

    private fun postUpdatedCredentialsToAPI(name: String, email: String) {
        viewmodel.updateProfile(name, email)
        viewmodel.onError = {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

        viewmodel.onSuccess = {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
    }


}