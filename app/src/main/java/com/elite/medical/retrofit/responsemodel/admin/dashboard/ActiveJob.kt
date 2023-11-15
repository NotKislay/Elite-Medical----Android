package com.elite.medical.retrofit.responsemodel.admin.dashboard

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ActiveJob(
    @SerializedName("applied")
    val applied: List<String>,
    @SerializedName("clinic_register_id")
    val clinicRegisterId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("engage_from")
    val engageFrom: String,
    @SerializedName("engage_to")
    val engageTo: String,
    @SerializedName("hired")
    val hired: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("locations")
    val locations: List<String>,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("vacancy")
    val vacancy: String
) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(applied)
        parcel.writeString(clinicRegisterId)
        parcel.writeString(createdAt)
        parcel.writeString(description)
        parcel.writeString(engageFrom)
        parcel.writeString(engageTo)
        parcel.writeStringList(hired)
        parcel.writeInt(id)
        parcel.writeStringList(locations)
        parcel.writeString(status)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(updatedAt)
        parcel.writeString(vacancy)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ActiveJob> {
        override fun createFromParcel(parcel: Parcel): ActiveJob {
            return ActiveJob(
                applied = parcel.createStringArrayList() ?: emptyList(),
                clinicRegisterId = parcel.readString() ?: "",
                createdAt = parcel.readString() ?: "",
                description = parcel.readString() ?: "",
                engageFrom = parcel.readString() ?: "",
                engageTo = parcel.readString() ?: "",
                hired = parcel.createStringArrayList() ?: emptyList(),
                id = parcel.readInt(),
                locations = parcel.createStringArrayList() ?: emptyList(),
                status = parcel.readString() ?: "",
                title = parcel.readString() ?: "",
                type = parcel.readString() ?: "",
                updatedAt = parcel.readString() ?: "",
                vacancy = parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<ActiveJob?> {
            return arrayOfNulls(size)
        }
    }
}
