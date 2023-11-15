package com.elite.medical.retrofit.responsemodel.admin.dashboard

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("email_verified_at") val emailVerifiedAt: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
): Parcelable



//todo: can be used later if required

//@Parcelize
//data class Role(
//    @SerializedName("id") val id: Int,
//    @SerializedName("name") val name: String,
//    @SerializedName("guard_name") val guardName: String,
//    @SerializedName("created_at") val createdAt: String,
//    @SerializedName("updated_at") val updatedAt: String,
//    @SerializedName("pivot") val pivot: Pivot
//): Parcelable
//
//
//@Parcelize
//data class Pivot(
//    @SerializedName("model_id") val modelId: String,
//    @SerializedName("role_id") val roleId: String,
//    @SerializedName("model_type") val modelType: String
//): Parcelable






