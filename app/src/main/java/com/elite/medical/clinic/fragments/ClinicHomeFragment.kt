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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.SideMenuAdapterClinic
import com.elite.medical.clinic.auth.LoginClinic
import com.elite.medical.clinic.ui.sidemenu.dashboard.ActivityNotificationsClinic
import com.elite.medical.clinic.ui.sidemenu.jobs.CreateJob
import com.elite.medical.clinic.ui.sidemenu.jobs.JobNApplicants
import com.elite.medical.clinic.ui.sidemenu.jobs.MyJobs
import com.elite.medical.clinic.ui.sidemenu.nurses.ActivitySearchNurses
import com.elite.medical.clinic.ui.sidemenu.nurses.EnrolledNurses
import com.elite.medical.clinic.viewmodels.ClinicViewModel
import com.elite.medical.databinding.FragmentClinicDashboardBinding
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel

class ClinicHomeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentClinicDashboardBinding
    private lateinit var viewModel: ClinicViewModel

    private lateinit var clinicDashboardData: ClinicDashboardModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClinicDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ClinicViewModel::class.java]

        setupDrawer()

        binding.btnNavMenu.setOnClickListener(this)
        binding.avatarImageView.setOnClickListener(this)
        binding.tvJobApplicants.setOnClickListener(this)
        binding.tvTopRatedNurse.setOnClickListener(this)


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { showLogoutConfirmationDialog() }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDashboardData()
        fetchDashboardData()
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
                "Home" -> {
                }

                "Notifications" -> {
                    val intent = Intent(requireContext(), ActivityNotificationsClinic::class.java)
                    startActivity(intent)
                }

                "Profile" -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_profileClinicFragment)

                "My Jobs" -> {
                    val intent = Intent(requireContext(), MyJobs::class.java)
                    startActivity(intent)
                }

                "Create" -> {
                    val intent = Intent(requireContext(), CreateJob::class.java)
                    startActivity(intent)
                }

                "Applicants" -> {
                    val intent = Intent(requireContext(), JobNApplicants::class.java)
                    startActivity(intent)
                }

                "Search" -> {
                    val intent = Intent(requireContext(), ActivitySearchNurses::class.java)
                    startActivity(intent)
                }

                "Enrolled" -> {
                    val intent = Intent(requireContext(), EnrolledNurses::class.java)
                    startActivity(intent)
                }

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

            binding.avatarImageView.id -> showCustomAvatarDialog()


            binding.tvJobApplicants.id -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_recentJobApplicantsFragment)


            binding.tvTopRatedNurse.id -> findNavController().navigate(R.id.action_clinicDashboardFragment_to_topRatedNursesFragment)

        }
    }

    private fun showCustomAvatarDialog() {
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
        val builder = AlertDialog.Builder(requireContext(), R.style.MyDialogTheme)
        builder.setTitle("Logout")
        builder.setMessage("Want to logout?")


        builder.setPositiveButton("Yes") { dialog, _ ->
            EliteMedical.updateClinicToken(null)
            dialog.dismiss()
            val intent = Intent(requireContext(), LoginClinic::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

/*    private fun postUpdatedCredentialsToAPI(name: String, email: String) {
        DDClinicAPI.postUpdatedUserDetails(
            name,
            email,
            object : DDClinicAPI.Companion.ProfileUpdateCallback {
                override fun onSuccess(msg: String?, statusCode: Int?) {

                    if (statusCode == 200) {
                        Toast.makeText(this@ActivityClinicProfile, "$msg", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                        val intent =
                            Intent(this@ActivityClinicProfile, ClinicHomeFragment::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@ActivityClinicProfile, "$msg", Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            })
    }*/


}