package com.elite.medical.nurse.fragments.dashboard

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elite.medical.R
import com.elite.medical.databinding.FragmentNotificationBinding
import com.elite.medical.nurse.adapters.dashboard.NurseNotificationAdapter
import com.elite.medical.nurse.viewmodels.dashboard.NurseDashboardVM

class NotificationsNurse : Fragment() {


    private lateinit var binding: FragmentNotificationBinding
    private lateinit var viewmodel: NurseDashboardVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[NurseDashboardVM::class.java]
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        populateNotifications()

        return binding.root
    }

    private fun populateNotifications() {
        viewmodel.getNotificationLists()
        viewmodel.notificationDetails.observe(viewLifecycleOwner) {
            binding.loader.isVisible = false

            if(it.isNotEmpty()){
                val rvAdapter = NurseNotificationAdapter(it, requireContext())
                binding.rvNotificationsNurse.layoutManager = LinearLayoutManager(requireContext())
                binding.rvNotificationsNurse.adapter = rvAdapter
            }
            else{
                binding.rvNotificationsNurse.isVisible = false
                val customdialog= Dialog(requireContext())
                customdialog.setContentView(R.layout.modal_layout_clinic_details_more)

                customdialog.findViewById<TextView>(R.id.tv_more_details).text="No Reviews !"
                customdialog.findViewById<Button>(R.id.btnReviews_modal).visibility=View.GONE
                customdialog.findViewById<Button>(R.id.btnNurseAssoc_modal).visibility=View.GONE
                customdialog.findViewById<View>(R.id.divider1).visibility=View.GONE
                customdialog.findViewById<View>(R.id.divider2).visibility=View.GONE
                val btngoback=customdialog.findViewById<Button>(R.id.btnCancel_modal)
                btngoback.text="No Notifications"
                btngoback.setOnClickListener {
                    activity?.onBackPressed()
                }
                customdialog.show()
            }
            binding.loader.isVisible = false

        }
    }

}