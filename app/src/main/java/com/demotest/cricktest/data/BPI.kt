package com.demotest.cricktest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BPI(
    @SerializedName("USD")
    var USD: Currancy,
    @SerializedName("GBP")
    var GBP: Currancy,
    @SerializedName("EUR")
    var EUR: Currancy,
) : Parcelable