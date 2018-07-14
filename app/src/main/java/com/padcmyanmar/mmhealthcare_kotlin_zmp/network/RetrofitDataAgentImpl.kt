package com.padcmyanmar.mmhealthcare_kotlin_zmp.network

import com.google.gson.Gson
import com.padcmyanmar.mmhealthcare_kotlin_zmp.events.FailHealthCareList
import com.padcmyanmar.mmhealthcare_kotlin_zmp.events.SuccessGetHealthCareEvent
import com.padcmyanmar.mmhealthcare_kotlin_zmp.network.responses.GetHeathCareResponse
import com.padcmyanmar.mmhealthcare_kotlin_zmp.utils.MMHealthCareConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgentImpl {

    companion object {
        private var INSTANCE: RetrofitDataAgentImpl? = null

        fun getInStance(): RetrofitDataAgentImpl {
            if (INSTANCE == null) {
                INSTANCE = RetrofitDataAgentImpl()
            }
            val i = INSTANCE
            return i!!
        }
    }

    private val mTheApi: HealthCareApi

    private constructor() {

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(MMHealthCareConstants.HEALTHCARE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()

        mTheApi = retrofit.create(HealthCareApi::class.java)
    }

    fun loadHealthCareInfoLists(accessToken: String) {
        val heathCareResponse = mTheApi.loadHealthCareInfoLists(accessToken)
        heathCareResponse.enqueue(object : Callback<GetHeathCareResponse> {
            override fun onFailure(call: Call<GetHeathCareResponse>?, t: Throwable?) {
                EventBus.getDefault().post(FailHealthCareList.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<GetHeathCareResponse>?, response: Response<GetHeathCareResponse>?) {

                val healthCareResponse: GetHeathCareResponse? = response!!.body()
                if (healthCareResponse != null && healthCareResponse.isResponseOk()) {
                    val dataLoadEvent = SuccessGetHealthCareEvent.HealthCareEvent(healthCareResponse.getHealthCareLists())
                    EventBus.getDefault().post(dataLoadEvent)
                } else
                    if (healthCareResponse != null) {
                        EventBus.getDefault().post(SuccessGetHealthCareEvent.ErreorEvent(healthCareResponse.getMessage()))
                    } else {
                        EventBus.getDefault().post(SuccessGetHealthCareEvent.ErreorEvent())
                    }

            }
        })
    }
}