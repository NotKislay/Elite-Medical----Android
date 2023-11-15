package com.elite.medical.retrofit.responsemodel.admin.dashboard

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ClinicReview(
    @SerializedName("clinic_register_id")
    val clinicRegisterId: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nurse_name")
    val nurseName: String,
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
        parcel.writeString(clinicRegisterId)
        parcel.writeString(comment)
        parcel.writeString(createdAt)
        parcel.writeInt(id)
        parcel.writeString(nurseName)
        parcel.writeString(nurseRegisterId)
        parcel.writeString(rating)
        parcel.writeString(timeAgo)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClinicReview> {
        override fun createFromParcel(parcel: Parcel): ClinicReview {
            return ClinicReview(
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<ClinicReview?> {
            return arrayOfNulls(size)
        }
    }
}
