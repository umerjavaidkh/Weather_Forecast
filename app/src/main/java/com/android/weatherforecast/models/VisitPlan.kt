package com.android.weatherforecast.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject

@Entity(tableName = "visitPlan")
class VisitPlan(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long,

    var name: String? = "",

    @SerializedName("description")
    var description: String? = "",

    var isSelected: Boolean = false,

    var isCompleted: Boolean = false,

    var listingType: Int = 0,

    @SerializedName("categoryType")
    var categoryType: String? = "",

    @SerializedName("logo")
    var logo: String? = "",

    @SerializedName("storeId")
    var storeId: Long = 0,

    var timeStamp: Long = 0L) : Parcelable {

    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        name = parcel.readString(),
        description = parcel.readString(),
        isSelected = parcel.readByte() != 0.toByte(),
        isCompleted = parcel.readByte() != 0.toByte(),
        listingType = parcel.readInt(),
        categoryType = parcel.readString(),
        logo = parcel.readString(),
        storeId = parcel.readLong(),
        timeStamp = parcel.readLong()
    )

    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject) : this(
        id = jsonObject.getLong("id")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeByte(if (isCompleted) 1 else 0)
        parcel.writeInt(listingType)
        parcel.writeString(categoryType)
        parcel.writeString(logo)
        parcel.writeLong(storeId)
        parcel.writeLong(timeStamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VisitPlan> {
        override fun createFromParcel(parcel: Parcel): VisitPlan {
            return VisitPlan(parcel)
        }

        override fun newArray(size: Int): Array<VisitPlan?> {
            return arrayOfNulls(size)
        }
    }
}