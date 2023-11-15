package com.elite.medical.retrofit.responsemodel.admin.dashboard

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NurseReview(
    @SerializedName("clinic_name")
    val clinicName: String,
    @SerializedName("clinic_register_id")
    val clinicRegisterId: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nurse_register_id")
    val nurseRegisterId: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("time_ago")
    val timeAgo: String,
    @SerializedName("updated_at")
    val updatedAt: String
) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(clinicName)
        parcel.writeString(clinicRegisterId)
        parcel.writeString(comment)
        parcel.writeString(createdAt)
        parcel.writeInt(id)
        parcel.writeString(nurseRegisterId)
        parcel.writeString(rating)
        parcel.writeString(timeAgo)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NurseReview> {
        override fun createFromParcel(parcel: Parcel): NurseReview {
            return NurseReview(
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<NurseReview?> {
            return arrayOfNulls(size)
        }
    }
}
