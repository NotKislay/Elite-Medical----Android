package com.elite.medical.clinic.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.SideMenuAdapterClinic
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.clinic.auth.LoginClinic
import com.elite.medical.clinic.viewmodels.ClinicViewModel
import com.elite.medical.databinding.FragmentClinicDashboardBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel

class ClinicHomeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentClinicDashboardBinding
    private lateinit var viewModel:ClinicViewModel

    private lateinit var clinicDashboardData: ClinicDashboardModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClinicDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ClinicViewModel::class.java]

        viewModel.getDashboardData()
        fetchDashboardData()

        setupDrawer()

        binding.btnNavMenu.setOnClickListener(this)
        binding.avatarImageView.setOnClickListener(this)
        binding.tvJobApplicants.setOnClickListener(this)
        binding.tvTopRatedNurse.setOnClickListener(this)


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { showLogoutConfirmationDialog() }
        return binding.root
    }


    private fun fetchDashboardData() {
        viewModel.dashboardDataCallback = {
            clinicDashboardData = it
            binding.tvActiveNurses.text = "Active Nurses: ${it.activeNurses}"
            binding.tvActiveJobs.text = "Active Jobs: ${it.activeJobCount}"
            binding.loader.visibility = View.GONE
        }
    }

    private fun setupDrawer() {
        val menu = binding.expandablelistview
        val sideMenuItems: List<String> = listOf("Dashboard", "Jobs", "Nurses")
        val sideMenuSubItems: LinkedHashMap<String, List<String>> = LinkedHashMap()

        val dashboard = listOf("Home", "Notifications", "Profile")
        val jobs = listOf("My Jobs", "Create", "Applicants")
        val nurses = listOf("Search", "Enrolled")

        sideMenuSubItems["Dashboard"] = dashboard
        sideMenuSubItems["Jobs"] = jobs
        sideMenuSubItems["Nurses"] = nurses

        menu.addHeaderView(
            layoutInflater.inflate(
                R.layout.header_nav,
                null,
                false
            )
        )      //  Adding Menu Header File


        val menuAdapter: ExpandableListAdapter
        menuAdapter = SideMenuAdapterClinic(requireContext(), sideMenuItems, sideMenuSubItems)
        menu.setAdapter(menuAdapter)    //  Setting Adapter for side menu

        menu.setOnGroupClickListener { _, _, groupPosition, _ ->        // OnClink Listeners for Menu Items
            if (menu.isGroupExpanded(groupPosition)) menu.collapseGroup(groupPosition)
            else menu.expandGroup(groupPosition)
            true // Return true to indicate that you've handled the click
        }

        menu.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->     // OnClink Listeners for Sub Menu Items
            when (sideMenuSubItems[sideMenuItems[groupPosition]]?.get(childPosition)) {
                "Home" -> {}
                "Notifications" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_clinicNotificationsFragment)
                "Profile" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_profileClinicFragment)
                "My Jobs" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_jobListFragment)
                "Create" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_createJobFragment)
                "Applicants" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_jobNApplicantsFragment)
                "Search" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_searchAvailableNursesFragment)
                "Enrolled" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_enrolledNursesFragment)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnNavMenu.id -> {
                if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START)) binding.drawerLayout.openDrawer(
                    GravityCompat.START
                )
                else binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            binding.avatarImageView.id -> showAvatarDialog()
            binding.tvJobApplicants.id -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_recentJobApplicantsFragment)
            binding.tvTopRatedNurse.id -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_topRatedNursesFragment)
        }
    }

    private fun showAvatarDialog() {
        val customDialog = Dialog(requireContext())
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        // Customize the dialog components
        val customTitle = customDialog.findViewById<TextView>(R.id.tv_more_details)
        customTitle.text = "Profile"

        val viewProfileBtn = customDialog.findViewById<Button>(R.id.btnNurseAssoc_modal)
        viewProfileBtn.text = "View Profile"
        viewProfileBtn.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)

        val logoutBtn = customDialog.findViewById<Button>(R.id.btnReviews_modal)
        logoutBtn.text = "Logout"
        logoutBtn.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)

        //Disabling the layout fields which are not required
        val cancelBtn = customDialog.findViewById<Button>(R.id.btnCancel_modal)
        cancelBtn.visibility = View.GONE
        customDialog.findViewById<View>(R.id.divider3).visibility = View.GONE

        viewProfileBtn.setOnClickListener {
            customDialog.dismiss()
            findNavController().navigate(R.id.action_clinicDashboardFragment_to_profileClinicFragment)
        }

        logoutBtn.setOnClickListener {
            customDialog.dismiss()
            showLogoutConfirmationDialog()
        }

        customDialog.show()
    }

    private fun showLogoutConfirmationDialog() {
        val customLogout = Dialog(requireContext())
        customLogout.setContentView(R.layout.modal_layout_logout)

        customLogout.findViewById<Button>(R.id.btn_ok).setOnClickListener {
            EliteMedical.updateClinicToken(null)
            customLogout.dismiss()
            val intent = Intent(requireActivity(), LoginClinic::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        customLogout.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            customLogout.dismiss()
        }
        customLogout.show()
    }

    override fun onResume() {
        super.onResume()

    }


}