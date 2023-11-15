package com.elite.medical.clinic.ui.dahboardscreen

import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.admin.adapters.SideMenuAdapterClinic
import com.elite.medical.clinic.auth.LoginClinic
import com.elite.medical.clinic.ui.sidemenu.dashboard.ActivityClinicProfile
import com.elite.medical.clinic.ui.sidemenu.dashboard.ActivityNotificationsClinic
import com.elite.medical.clinic.ui.sidemenu.jobs.CreateJob
import com.elite.medical.clinic.ui.sidemenu.jobs.JobNApplicants
import com.elite.medical.clinic.ui.sidemenu.jobs.MyJobs
import com.elite.medical.clinic.ui.sidemenu.nurses.ActivitySearchNurses
import com.elite.medical.clinic.ui.sidemenu.nurses.EnrolledNurses
import com.elite.medical.databinding.ActivityDashboardClinicBinding
import com.elite.medical.retrofit.apis.clinic.DDClinicAPI
import com.elite.medical.retrofit.responsemodel.clinic.dashboard.ClinicDashboardModel

class ClinicDashboard : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDashboardClinicBinding

    private lateinit var clinicDashboardData: ClinicDashboardModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_clinic)


        setupDrawer()
        fetchDashboardData()
        binding.btnNavMenu.setOnClickListener(this)
        binding.avatarImageView.setOnClickListener(this)
        binding.tvJobApplicants.setOnClickListener(this)
        binding.tvTopRatedNurse.setOnClickListener(this)


    }

    private fun fetchDashboardData() {
        DDClinicAPI.getDashboardData(object : DDClinicAPI.Companion.ResponseCallback {
            override fun onDataReceived(data: ClinicDashboardModel?, statusCode: Int?) {
                if (statusCode == 200) {
                    clinicDashboardData = data!!
                    binding.tvActiveNurses.text = "Active Nurses: ${data.activeNurses}"
                    binding.tvActiveJobs.text = "Active Jobs: ${data.activeJobCount}"
                    binding.loader.visibility = View.GONE

                } else {
                    Toast.makeText(
                        this@ClinicDashboard,
                        "Session has expired, Please login again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    EliteMedical.updateClinicToken(null)
                    finish()
                }
            }
        })
    }

    private fun setupDrawer() {
        val menu = binding.expandablelistview
        val sideMenuItems: List<String> = listOf("Dashboard", "Jobs", "Nurses")
        val sideMenuSubItems: LinkedHashMap<String, List<String>> = LinkedHashMap()

        val dashboard = listOf("Home", "NotificationsNurse", "Profile")
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
        menuAdapter = SideMenuAdapterClinic(this, sideMenuItems, sideMenuSubItems)
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
                    val intent = Intent(this, ActivityNotificationsClinic::class.java)
                    startActivity(intent)
                    //Toast.makeText(this,"NotificationsNurse",Toast.LENGTH_SHORT).show()
                }

                "Profile" -> {
                    val intent = Intent(this, ActivityClinicProfile::class.java)
                    startActivity(intent)
                    //Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
                }

                "My Jobs" -> {
                    val intent = Intent(this, MyJobs::class.java)
                    startActivity(intent)
                }

                "Create" -> {
                    val intent = Intent(this, CreateJob::class.java)
                    startActivity(intent)
                }

                "Applicants" -> {
                    val intent = Intent(this, JobNApplicants::class.java)
                    startActivity(intent)
                }

                "Search" -> {
                    val intent = Intent(this, ActivitySearchNurses::class.java)
                    startActivity(intent)
                }

                "Enrolled" -> {
                    val intent = Intent(this, EnrolledNurses::class.java)
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

            binding.avatarImageView.id -> {
                showCustomAvatarDialog()
            }

            binding.tvTopRatedNurse.id -> {
                val intent = Intent(this, TopRatedNurses::class.java)
                intent.putExtra("clinicDashboardData", clinicDashboardData)
                startActivity(intent)
            }

            binding.tvJobApplicants.id -> {
                val intent = Intent(this, RecentJobApplicants::class.java)
                intent.putExtra("clinicDashboardData", clinicDashboardData)
                startActivity(intent)
            }
        }
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
            val intent = Intent(this, ActivityClinicProfile::class.java)
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
            EliteMedical.updateClinicToken(null)
            dialog.dismiss()
            val intent = Intent(this, LoginClinic::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        showLogoutConfirmationDialog()
    }
}