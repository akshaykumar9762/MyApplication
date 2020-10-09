package com.demotest.cricktest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrancyResponse(
    @SerializedName("time")
    var time: Time = Time(),
    @SerializedName("disclaimer")
    var disclaimer: String = "",
    @SerializedName("chartName")
    var chartName: String = "",
    @SerializedName("bpi")
    var bpi: BPI = BPI(Currancy(), Currancy(), Currancy())
) : Parcelable