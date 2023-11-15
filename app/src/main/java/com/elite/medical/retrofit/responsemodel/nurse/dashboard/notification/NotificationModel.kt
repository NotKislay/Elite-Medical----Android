package com.elite.medical.retrofit.responsemodel.nurse.dashboard.notification


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("notifications")
    val notifications: List<Notification>,
    @SerializedName("status")
    val status: String
) : Parcelable {

    @Parcelize
    data class Notification(
        @SerializedName("body")
        val body: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("read")
        val read: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: String
    ) : Parcelable
}