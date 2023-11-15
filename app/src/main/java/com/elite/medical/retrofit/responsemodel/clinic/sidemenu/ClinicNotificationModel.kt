package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications

import com.google.gson.annotations.SerializedName

data class ClinicNotificationsModel (
    @SerializedName("notifications") val notifications: List<NotificationDetailsFromAdminNotificationsModel>,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
)