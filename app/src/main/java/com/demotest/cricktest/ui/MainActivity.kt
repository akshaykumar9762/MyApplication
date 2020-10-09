package com.demotest.cricktest.ui

import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.demotest.cricktest.R
import com.demotest.cricktest.data.Currancy
import com.demotest.cricktest.data.CurrancyResponse
import com.demotest.cricktest.network.CurrancyService
import com.demotest.cricktest.network.RetrofitInstance
import com.demotest.cricktest.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var picker: NumberPicker
    lateinit var curancyNameList: MutableList<String>
    lateinit var curranyList: MutableList<Currancy>
    lateinit var activity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity = MainActivity()

        this.picker = findViewById(R.id.np_currancy_name)

        picker.setOnValueChangedListener { picker, oldVal, newVal ->
            Log.e(TAG, " $oldVal $newVal ")
            tv_bt_price.text = curranyList[newVal].rate

        }
    }

    override fun onResume() {
        super.onResume()

        if (Utils.isInternetAvailable(this)) {
            getPriceData()
        } else {
            pb_prices.isVisible = false
            tv_loading_msg.text = getString(R.string.no_internet_message)
        }
    }

    private fun getPriceData() {
        val service: CurrancyService? =
            RetrofitInstance.retrofitInstance?.create(CurrancyService::class.java)

        val call: Call<CurrancyResponse> = service!!.getCurrancyData()

        call.enqueue(object : Callback<CurrancyResponse> {
            override fun onResponse(
                call: Call<CurrancyResponse>,
                response: Response<CurrancyResponse>
            ) {
                Log.i(TAG, response.body().toString())

                curranyList = mutableListOf()

                val responseValue: CurrancyResponse = response.body() as CurrancyResponse

                curranyList.add(responseValue.bpi.EUR)
                curranyList.add(responseValue.bpi.GBP)
                curranyList.add(responseValue.bpi.USD)

                picker.setMinValue(0)
                picker.setMaxValue(curranyList.size - 1)
                curancyNameList = arrayListOf()

                for (currancy in curranyList) {
                    curancyNameList.add(currancy.code)
                }

                picker.setDisplayedValues(curancyNameList.toTypedArray())
                np_currancy_name.isVisible = true
                tv_bt_price.text = curranyList[0].rate
                pb_prices.isVisible = false
                tv_loading_msg.isVisible = false

            }

            override fun onFailure(call: Call<CurrancyResponse>, t: Throwable) {
                pb_prices.isVisible = false
                tv_loading_msg.text = getString(R.string.failure_message)
                Log.e(TAG, t.message.toString())
                t.stackTrace
            }
        })
    }

}