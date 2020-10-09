package com.demotest.cricktest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currancy(
    @SerializedName("code")
    var code: String = "",
    @SerializedName("symbol")
    var symbol: String = "",
    @SerializedName("rate")
    var rate: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("rate_float")
    var rate_float: Float = 0.0f
) : Parcelable