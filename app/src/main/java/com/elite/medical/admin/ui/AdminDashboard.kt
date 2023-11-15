package com.elite.medical.admin.ui

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.SideMenuAdapterAdmin
import com.elite.medical.admin.ui.auth.LoginAdmin
import com.elite.medical.admin.ui.sidemenu.approvals.ApprovalsClinic
import com.elite.medical.admin.ui.sidemenu.approvals.JobApprovals
import com.elite.medical.admin.ui.sidemenu.approvals.ApprovalsNurse
import com.elite.medical.admin.ui.sidemenu.dashboard.ApprovedClinics
import com.elite.medical.admin.ui.sidemenu.dashboard.NotificationsAdmin
import com.elite.medical.admin.ui.dashboard.RecentClinicReview
import com.elite.medical.admin.ui.dashboard.recents.RecentClinics
import com.elite.medical.admin.ui.dashboard.RecentNurseReview
import com.elite.medical.admin.ui.dashboard.recents.RecentNurses
import com.elite.medical.admin.ui.sidemenu.approvals.EmploymentApprovals
import com.elite.medical.admin.ui.sidemenu.approvals.JobSearchApprovals
import com.elite.medical.admin.ui.sidemenu.dashboard.jobapplicants.ApprovedJobApplicants
import com.elite.medical.admin.ui.sidemenu.reviews.ClinicReviews
import com.elite.medical.admin.ui.sidemenu.reviews.NurseReviews
import com.elite.medical.admin.ui.sidemenu.dashboard.jobs.ApprovedJobs
import com.elite.medical.admin.ui.sidemenu.dashboard.nurses.ApprovedNurses
import com.elite.medical.databinding.ActivityDashboardAdminBinding
import com.elite.medical.retrofit.apis.admin.DDAdminAPI
import com.elite.medical.retrofit.responsemodel.admin.dashboard.AdminDashboardModel

class AdminDashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAdminBinding
    private lateinit var dashboardData: AdminDashboardModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_admin)

        setupDrawer()
        fetchAndSetData()

        binding.tvRecentClinics.setOnClickListener {
            val intent = Intent(this, RecentClinics::class.java)
            intent.putParcelableArrayListExtra("clinics", ArrayList(dashboardData.clinics.take(5)))
            startActivity(intent)
        }

        binding.tvRecentNurses.setOnClickListener {
            val intent = Intent(this, RecentNurses::class.java)
            intent.putParcelableArrayListExtra("nurse", ArrayList(dashboardData.nurses.take(5)))
            startActivity(intent)
        }

        binding.tvRecentClinicsReview.setOnClickListener {
            val intent = Intent(this, RecentClinicReview::class.java)
            intent.putParcelableArrayListExtra("reviews", ArrayList(dashboardData.clinicReviews))
            startActivity(intent)
        }

        binding.tvRecentNursesReview.setOnClickListener {
            val intent = Intent(this, RecentNurseReview::class.java)
            intent.putParcelableArrayListExtra("reviews", ArrayList(dashboardData.nurseReviews))
            startActivity(intent)
        }

        binding.avatarImageView.setOnClickListener {
            showCustomAvatarDialog()
        }

        binding.btnNavMenu.setOnClickListener {
            if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START)) binding.drawerLayout.openDrawer(
                GravityCompat.START
            )
            else binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

    }

    private fun fetchAndSetData() {
        DDAdminAPI.getDashboardData(
            object : DDAdminAPI.Companion.DashboardDataCallback {
                override fun onDataReceived(data: AdminDashboardModel?, statusCode: Int?) {
                    if (statusCode == 200) {
                        binding.tvActiveClinic.text = "Active Clinics: ${data!!.clinics.size}"
                        binding.tvActiveNurses.text = "Active Nurses: ${data!!.nurses.size}"
                        binding.tvActiveJobs.text = "Active Jobs: ${data!!.activeJobs.size}"

                        dashboardData = data
                        binding.loader.visibility = View.GONE
                    }
                }
            })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        lateinit var drawerToggle: ActionBarDrawerToggle
        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun setupDrawer() {

        val menu = binding.expandablelistview


        val sideMenuSubItems: LinkedHashMap<String, List<String>> = LinkedHashMap()
        val sideMenuItems: List<String> =
            listOf("Dashboard", "Approvals", "Reviews")       //  Menu Items
        val dashboardItems =
            listOf("Home", "NotificationsNurse", "Clinics", "Nurses", "Jobs", "Job Applicants")
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
                R.layout.header_nav,
                null,
                false
            )
        )      //  Adding Menu Header File

        val menuAdapter: ExpandableListAdapter
        menuAdapter = SideMenuAdapterAdmin(this, sideMenuItems, sideMenuSubItems)
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

                "NotificationsNurse" -> {
                    val intent = Intent(this, NotificationsAdmin::class.java)
                    startActivity(intent)
                }

                "Clinics" -> {
                    val intent = Intent(this, ApprovedClinics::class.java)
                    startActivity(intent)
                }

                "Nurses" -> {
                    val intent = Intent(this, ApprovedNurses::class.java)
                    startActivity(intent)
                }

                "Jobs" -> {
                    val intent = Intent(this, ApprovedJobs::class.java)
                    startActivity(intent)
                }

                "Job Applicants" -> {
                    val intent = Intent(this, ApprovedJobApplicants::class.java)
                    startActivity(intent)
                }

                "Nurse Approval" -> {
                    val intent = Intent(this, ApprovalsNurse::class.java)
                    startActivity(intent)
                }

                "Clinic Approval" -> {
                    val intent = Intent(this, ApprovalsClinic::class.java)
                    startActivity(intent)
                }

                "Job Approval" -> {
                    val intent = Intent(this, JobApprovals::class.java)
                    startActivity(intent)
                }

                "Employment Approval" -> {
                    val intent = Intent(this, EmploymentApprovals::class.java)
                    startActivity(intent)
                }

                "Job Search Approval" -> {
                    val intent = Intent(this, JobSearchApprovals::class.java)
                    startActivity(intent)
                }

                "Nurse Review" -> {
                    val intent = Intent(this, NurseReviews::class.java)
                    startActivity(intent)
                }

                "Clinic Review" -> {
                    val intent = Intent(this, ClinicReviews::class.java)
                    startActivity(intent)
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onBackPressed() {
        showLogoutConfirmationDialog()
    }

    private fun showCustomAvatarDialog() {
        val customDialog = Dialog(this)
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
            val intent = Intent(this, ActivityViewProfile::class.java)
            startActivity(intent)
        }

        logoutBtn.setOnClickListener {
            customDialog.dismiss()
            showLogoutConfirmationDialog()
        }
        customDialog.show()
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this, R.style.MyDialogTheme)
        builder.setTitle("Logout")
        builder.setMessage("Want to logout?")


        builder.setPositiveButton("Yes") { dialog, _ ->
            EliteMedical.updateAdminToken(null)
            dialog.dismiss()
            val intent = Intent(this, LoginAdmin::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        println(EliteMedical.AuthTokenAdmin)
    }


}