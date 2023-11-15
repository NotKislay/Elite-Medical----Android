package com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications

import com.google.gson.annotations.SerializedName

data class NotificationDetailsFromAdminNotificationsModel(
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("created_at") val created_at: String,
)