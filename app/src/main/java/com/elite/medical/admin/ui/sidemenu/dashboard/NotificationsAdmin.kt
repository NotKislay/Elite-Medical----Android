package com.elite.medical.admin.ui.sidemenu.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.EliteMedical
import com.elite.medical.R
import com.elite.medical.databinding.ActivityDashboardNotificationBinding
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel
import com.elite.medical.admin.adapters.sidemenu.dashboard.NotificationAdapter
import com.elite.medical.retrofit.apis.admin.sidemenu.DashboardAPIs

class NotificationsAdmin: AppCompatActivity() {


    private lateinit var binding: ActivityDashboardNotificationBinding
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_dashboard_notification)

        recyclerView = binding.rvNotifications
        recyclerView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        populateNotifications()


        binding.btnBack.setOnClickListener { finish() }

    }

    private fun populateNotifications() {
        val token = EliteMedical.AuthTokenAdmin
        DashboardAPIs.getNotifications(object : DashboardAPIs.Companion.NotificationsCallback {
            override fun onListReceived(notification: List<NotificationDetailsFromAdminNotificationsModel>) {
                if(notification.isNotEmpty()){
                    val adapter =
                        NotificationAdapter(ArrayList(notification), this@NotificationsAdmin)
                    recyclerView.adapter = adapter
                    binding.loader.visibility = View.GONE
                }
                else{
                    binding.loader.visibility = View.GONE
                    binding.tvNoNotifn.visibility=View.VISIBLE
                }
            }
        })
    }
}