package com.elite.medical.retrofit.responsemodel.clinic


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ClinicProfileDetailsModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("user")
    val user: User
) : Parcelable {
    @Parcelize
    data class User(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("roles")
        val roles: List<Role>,
        @SerializedName("updated_at")
        val updatedAt: String
    ) : Parcelable {
        @Parcelize
        data class Role(
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("guard_name")
            val guardName: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("pivot")
            val pivot: Pivot,
            @SerializedName("updated_at")
            val updatedAt: String
        ) : Parcelable {
            @Parcelize
            data class Pivot(
                @SerializedName("model_id")
                val modelId: String,
                @SerializedName("model_type")
                val modelType: String,
                @SerializedName("role_id")
                val roleId: String
            ) : Parcelable
        }
    }
}