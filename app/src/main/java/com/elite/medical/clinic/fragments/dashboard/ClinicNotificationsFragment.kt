package com.elite.medical.clinic.fragments.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.admin.adapters.sidemenu.dashboard.NotificationAdapter
import com.elite.medical.clinic.viewmodels.ClinicViewModel
import com.elite.medical.databinding.FragmentClinicNotificationsBinding
import com.elite.medical.retrofit.apis.clinic.sidemenu.DashboardClinicAPIs
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel


class ClinicNotificationsFragment : Fragment() {

    private lateinit var binding: FragmentClinicNotificationsBinding
    private lateinit var viewModel: ClinicViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClinicNotificationsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ClinicViewModel::class.java]

        recyclerView = binding.rvNotifications

        viewModel.getClinicNotifications()

        viewModel.clinicNotificationCallback = { populateNotifications(it) }





        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        return binding.root
    }


    private fun populateNotifications(notifications: List<NotificationDetailsFromAdminNotificationsModel>) {
        if (notifications.isNotEmpty()) {
            val adapter =
                NotificationAdapter(
                    ArrayList(notifications),
                    requireContext()
                )
            recyclerView.adapter = adapter
            binding.loader.visibility = View.GONE
        } else {
            binding.loader.visibility = View.GONE
            binding.tvNoNotifn.visibility = View.VISIBLE
        }
    }


}