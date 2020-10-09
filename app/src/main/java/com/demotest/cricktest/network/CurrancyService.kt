package com.demotest.cricktest.network

import com.demotest.cricktest.data.CurrancyResponse
import retrofit2.Call
import retrofit2.http.GET


interface CurrancyService {

    @GET("bpi/currentprice.json")
    fun getCurrancyData(): Call<CurrancyResponse>
}