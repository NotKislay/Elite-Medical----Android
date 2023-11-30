package com.elite.medical.admin.fragments.mainscreen

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
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.SideMenuAdapterAdmin
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.admin.ui.sidemenu.approvals.ApprovalsClinic
import com.elite.medical.admin.ui.sidemenu.approvals.ApprovalsNurse
import com.elite.medical.admin.ui.sidemenu.approvals.EmploymentApprovals
import com.elite.medical.admin.ui.sidemenu.approvals.JobApprovals
import com.elite.medical.admin.ui.sidemenu.approvals.JobSearchApprovals
import com.elite.medical.admin.ui.sidemenu.dashboard.clinics.ApprovedClinics
import com.elite.medical.admin.ui.sidemenu.dashboard.NotificationsAdmin
import com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants.ApprovedJobApplicants
import com.elite.medical.admin.ui.sidemenu.dashboard.jobs.ApprovedJobs
import com.elite.medical.admin.ui.sidemenu.dashboard.nurses.ApprovedNurses
import com.elite.medical.admin.ui.sidemenu.reviews.ClinicReviews
import com.elite.medical.admin.ui.sidemenu.reviews.NurseReviews
import com.elite.medical.admin.viewmodels.AdminViewModel
import com.elite.medical.databinding.FragmentAdminDashboardBinding
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel


class AdminDashboardFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAdminDashboardBinding
    private lateinit var dashboardData: AdminDashboardModel
    private lateinit var viewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[AdminViewModel::class.java]

        initialization()



        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) { showLogoutConfirmationDialog() }
        return binding.root
    }

    private fun initialization() {
        setupDrawer()
        viewModel.getDashboardData()    // calling API to fetch dashboard data
        displayData()                   // displays all data fetched from API
        initClickListeners()            // passed all the view to onClickListeners

    }

    private fun initClickListeners() {
        binding.tvRecentClinics.setOnClickListener(this)
        binding.tvRecentNurses.setOnClickListener(this)
        binding.tvRecentClinicsReview.setOnClickListener(this)
        binding.tvRecentNursesReview.setOnClickListener(this)
        binding.avatarImageView.setOnClickListener(this)
        binding.btnNavMenu.setOnClickListener(this)
    }

    private fun setupDrawer() {

        val menu = binding.expandablelistview


        val sideMenuSubItems: LinkedHashMap<String, List<String>> = LinkedHashMap()
        val sideMenuItems: List<String> =
            listOf("Dashboard", "Approvals", "Reviews")       //  Menu Items
        val dashboardItems =
            listOf("Home", "Notifications", "Clinics", "Nurses", "Jobs", "Job Applicants")
        val approvalItems = listOf(
            "Nurse Approval",
            "Clinic Approval",
            "Job Approval",
            "Employment Approval",
            "Job Search Approval",
        )
        val reviewItems = listOf("Nurse Review", "Clinic Review")

        sideMenuSubItems["Dashboard"] = dashboardItems
        sideMenuSubItems["Approvals"] = approvalItems
        sideMenuSubItems["Reviews"] = reviewItems


        menu.addHeaderView(
            layoutInflater.inflate(
                R.layout.header_nav, null, false
            )
        )      //  Adding Menu Header File

        val menuAdapter: ExpandableListAdapter
        menuAdapter = SideMenuAdapterAdmin(requireContext(), sideMenuItems, sideMenuSubItems)
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
                    val intent = Intent(requireContext(), NotificationsAdmin::class.java)
                    startActivity(intent)
                }

                "Clinics" -> {
                    val intent = Intent(requireContext(), ApprovedClinics::class.java)
                    startActivity(intent)
                }

                "Nurses" -> {
                    val intent = Intent(requireContext(), ApprovedNurses::class.java)
                    startActivity(intent)
                }

                "Jobs" -> {
                    val intent = Intent(requireContext(), ApprovedJobs::class.java)
                    startActivity(intent)
                }

                "Job Applicants" -> {
                    val intent = Intent(requireContext(), ApprovedJobApplicants::class.java)
                    startActivity(intent)
                }

                "Nurse Approval" -> {
                    val intent = Intent(requireContext(), ApprovalsNurse::class.java)
                    startActivity(intent)
                }

                "Clinic Approval" -> {
                    val intent = Intent(requireContext(), ApprovalsClinic::class.java)
                    startActivity(intent)
                }

                "Job Approval" -> {
                    val intent = Intent(requireContext(), JobApprovals::class.java)
                    startActivity(intent)
                }

                "Employment Approval" -> {
                    val intent = Intent(requireContext(), EmploymentApprovals::class.java)
                    startActivity(intent)
                }

                "Job Search Approval" -> {
                    val intent = Intent(requireContext(), JobSearchApprovals::class.java)
                    startActivity(intent)
                }

                "Nurse Review" -> {
                    val intent = Intent(requireContext(), NurseReviews::class.java)
                    startActivity(intent)
                }

                "Clinic Review" -> {
                    val intent = Intent(requireContext(), ClinicReviews::class.java)
                    startActivity(intent)
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    private fun displayData() {

        viewModel.getDashboardDataCallback = { data ->
            dashboardData = data
            binding.tvActiveClinic.text = "Active Clinics: ${data!!.countClinics}"
            binding.tvActiveNurses.text = "Active Nurses: ${data!!.countNurses}"
            binding.tvActiveJobs.text = "Active Jobs: ${data!!.countActiveJobs}"
            binding.loader.visibility = View.GONE
            viewModel.dashboardData.postValue(data)
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
            findNavController().navigate(R.id.action_adminDashboardFragment_to_adminProfileFragment)
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
            EliteMedical.updateAdminToken(null)
            customLogout.dismiss()
            val intent = Intent(requireContext(), LoginAdmin::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        customLogout.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            customLogout.dismiss()
        }
        customLogout.show()
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            binding.btnNavMenu.id -> {
                if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START)) binding.drawerLayout.openDrawer(
                    GravityCompat.START
                )
                else binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            binding.tvRecentClinics.id -> findNavController().navigate(R.id.action_adminDashboardFragment_to_recentClinicsAdminFragment)

            binding.tvRecentNurses.id -> findNavController().navigate(R.id.action_adminDashboardFragment_to_recentNursesAdminFragment)

            binding.tvRecentClinicsReview.id -> findNavController().navigate(R.id.action_adminDashboardFragment_to_recentClinicReviewsAdminFragment)

            binding.tvRecentNursesReview.id -> findNavController().navigate(R.id.action_adminDashboardFragment_to_recentNurseReviewsAdminFragment)

            binding.avatarImageView.id -> showCustomAvatarDialog()

        }

    }


}