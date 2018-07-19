package com.example.library

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable

data class ActivityBackWrapper(val intent: Intent?, val requestCode: Int, val resultCode: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readParcelable<Intent>(Intent::class.java.classLoader),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(intent, 0)
        writeInt(requestCode)
        writeInt(resultCode)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ActivityBackWrapper> = object : Parcelable.Creator<ActivityBackWrapper> {
            override fun createFromParcel(source: Parcel): ActivityBackWrapper = ActivityBackWrapper(source)
            override fun newArray(size: Int): Array<ActivityBackWrapper?> = arrayOfNulls(size)
        }
    }
}