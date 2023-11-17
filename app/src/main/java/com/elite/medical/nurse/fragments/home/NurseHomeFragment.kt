package com.elite.medical.nurse.fragments.home

import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Files
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.databinding.FragmentMainScreenBinding
import com.elite.medical.nurse.LoginNurse
import com.elite.medical.nurse.adapters.home.MenuAdapter
import com.elite.medical.nurse.viewmodels.NurseViewModel
import com.elite.medical.retrofit.responsemodel.nurse.home.DashboardDataNurseModel
import com.elite.medical.utils.GPSLocation
import com.elite.medical.utils.HelperMethods
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class NurseHomeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var hamburger: ImageButton
    private lateinit var profileIcon: ImageView
    private lateinit var timesheet: TextView
    private lateinit var sideMenu: ExpandableListView
    private lateinit var dashboardData: DashboardDataNurseModel
    private var clockOutVisible = false
    private val viewModel by viewModels<NurseViewModel>()

    private lateinit var photoFile: File


    lateinit var location: GPSLocation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        initBindings()
        setupMenu()
        fetchDashboardData()
        HelperMethods.requestLocationPermission(requireActivity())

        /*        binding.cardClockIn.isVisible = !clockOutVisible
                binding.cardClockOut.isVisible = clockOutVisible*/




        requireActivity().onBackPressedDispatcher.addCallback(this) { callLogout() }
        return binding.root
    }

    private fun fetchDashboardData() {
        viewModel.getNurseDashboardData()
        location = HelperMethods.getLocation(requireActivity())

        viewModel.nurseDashboardDataCallback = {
            dashboardData = it
            clockOutVisible = it.latestClockType.equals("in")
        }

        viewModel.clockOUTCallback = {
            Toast.makeText(requireContext(), it?.message, Toast.LENGTH_SHORT).show()
        }


    }

    private fun setupMenu() {
        val sideMenuItems: List<String> = listOf("Dashboard", "Jobs", "Clinics")
        val sideMenuSubItems: LinkedHashMap<String, List<String>> = LinkedHashMap()

        val dashboard = listOf("Home", "Profile", "Notifications")
        val jobs = listOf("Search Jobs", "Applied Jobs")
        val clinic = listOf("Enrolled")

        sideMenuSubItems["Dashboard"] = dashboard
        sideMenuSubItems["Jobs"] = jobs
        sideMenuSubItems["Clinics"] = clinic

        sideMenu.addHeaderView(
            layoutInflater.inflate(
                R.layout.header_nav,
                null,
                false
            )
        )//  Adding Menu Header File
        val menuAdapter: ExpandableListAdapter
        menuAdapter = MenuAdapter(requireContext(), sideMenuItems, sideMenuSubItems)
        sideMenu.setAdapter(menuAdapter)    //  Setting Adapter for side menu

        sideMenu.setOnGroupClickListener { _, _, groupPosition, _ ->        // OnClink Listeners for Menu Items
            if (sideMenu.isGroupExpanded(groupPosition)) sideMenu.collapseGroup(groupPosition)
            else sideMenu.expandGroup(groupPosition)
            true // Return true to indicate that you've handled the click
        }

        sideMenu.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->     // OnClink Listeners for Sub Menu Items
            when (sideMenuSubItems[sideMenuItems[groupPosition]]?.get(childPosition)) {

                "Profile" -> findNavController().navigate(R.id.action_mainScreenFragment_to_profileTab)

                "Notifications" -> findNavController().navigate(R.id.action_mainScreenFragment_to_notificationTab)

                "Search Jobs" -> findNavController().navigate(R.id.action_mainScreenFragment_to_searchJobsTab)

                "Applied Jobs" -> findNavController().navigate(R.id.action_mainScreenFragment_to_appliedJobsTab)

                "Enrolled" -> findNavController().navigate(R.id.action_mainScreenFragment_to_enrolledFragment)

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    private fun initBindings() {
        hamburger = binding.hamburger
        sideMenu = binding.menu
        profileIcon = binding.avatarImageView
        timesheet = binding.tvTimesheet

        hamburger.setOnClickListener(this)
        profileIcon.setOnClickListener(this)
        timesheet.setOnClickListener(this)
        binding.cardClockIn.setOnClickListener(this)
        binding.cardClockOut.setOnClickListener(this)
        binding.tvTimesheet.setOnClickListener(this)
        binding.tvTopRatedNurse.setOnClickListener(this)

    }

    private fun showCustomAvatarDialog() {
        val customDialog = Dialog(requireContext())
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.modal_layout_clinic_details_more)

        // Customize the dialog components

        val customTitle = customDialog.findViewById<TextView>(R.id.tv_more_details)
        customTitle.text = "Profile"

        customTitle.setOnClickListener {
            Toast.makeText(requireContext(), location.toString(), Toast.LENGTH_SHORT).show()

        }

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
            findNavController().navigate(R.id.action_mainScreenFragment_to_profileTab)
        }

        logoutBtn.setOnClickListener {
            customDialog.dismiss()
            callLogout()
        }
        customDialog.show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.hamburger.id -> {
                if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START)) binding.drawerLayout.openDrawer(
                    GravityCompat.START
                )
                else binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            binding.avatarImageView.id -> {
                showCustomAvatarDialog()
            }

            binding.tvTimesheet.id -> {
                findNavController().navigate(R.id.action_mainScreenFragment_to_timesheetFragment)
            }

            binding.tvTitle.id -> {
                Toast.makeText(requireContext(), location.toString(), Toast.LENGTH_SHORT).show()
            }

            binding.cardClockIn.id -> {
                if (location.errorMSG.isNullOrEmpty()) {
                    doClockIN()
                } else {
                    Toast.makeText(requireContext(), location.errorMSG, Toast.LENGTH_SHORT).show()
                }
            }

            binding.cardClockOut.id -> {
                takePhoto()
                /*                Toast.makeText(requireContext(), "clicked..", Toast.LENGTH_SHORT).show()
                                binding.cardClockIn.isVisible = !binding.cardClockIn.isVisible
                                binding.cardClockOut.isVisible = !binding.cardClockOut.isVisible*/
            }

            binding.tvTopRatedNurse.id -> {
                findNavController().navigate(R.id.action_mainScreenFragment_to_topRatedClinicsFragment)
            }
        }
    }

    private fun takePhoto() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun createMultipartBodyFromImage() {
        val mediaType: String = "image/jpg"
        val fileName: String =  System.currentTimeMillis().toString() + ".jpg"

        val reqFile = photoFile.asRequestBody(mediaType.toMediaTypeOrNull())
        val profilePic = MultipartBody.Part.createFormData( "image",fileName, reqFile)
        clockOut(location, profilePic)
    }

    private fun clockOut(location: GPSLocation, profilePic: MultipartBody.Part) {
        val gps = "${location.latitude},${location.longitude}"
        val gpsS = RequestBody.create("text/plain".toMediaTypeOrNull(), gps)
        viewModel.clockOut(gpsS, profilePic)
    }

    private fun doClockIN() {
        viewModel.clockIN(location)
        viewModel.clockINCallback = {
            if (it?.status.equals("success")) {
                Toast.makeText(requireContext(), it?.message, Toast.LENGTH_SHORT).show()
                binding.cardClockIn.isVisible = !binding.cardClockIn.isVisible
                binding.cardClockOut.isVisible = !binding.cardClockOut.isVisible
            } else {
                HelperMethods.showDialog(it!!.message, "OK", requireContext(), activity)
            }
        }
    }

    private fun callLogout() {
        val dialogBtn = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.MyDialogTheme
        )
        dialogBtn.setMessage("Do you want to exit?")
        dialogBtn.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        dialogBtn.setPositiveButton("Yes") { dialog, _ ->
            dialog.dismiss()
            EliteMedical.updateNurseToken(null)
            val intent = Intent(requireContext(), LoginNurse::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        dialogBtn.show()
    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {

                photoFile = getFileFromUri(uri)!!
                createMultipartBodyFromImage()



            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    private fun getFileFromUri(uri: Uri): File? {
        val filePath = uriToFilePath(uri)
        return if (filePath != null) File(filePath) else null
    }

    private fun uriToFilePath(uri: Uri): String? {
        val context = requireContext()
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            if (columnIndex != -1) {
                it.getString(columnIndex)
            } else {
                null
            }
        }
    }

}