package ywxt.tools.common.models

import android.os.Parcel
import android.os.Parcelable

data class ClickingGameData(
    val subTitle:String
) :Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subTitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClickingGameData> {
        override fun createFromParcel(parcel: Parcel): ClickingGameData {
            return ClickingGameData(parcel)
        }

        override fun newArray(size: Int): Array<ClickingGameData?> {
            return arrayOfNulls(size)
        }
    }

}