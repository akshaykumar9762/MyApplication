package com.demotest.cricktest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Time(
    @SerializedName("updated")
    var updated: String = "",
    @SerializedName("updatedISO")
    var updatedISO: String = "",
    @SerializedName("updateduk")
    var updateduk: String = ""
) : Parcelable