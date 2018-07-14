package com.padcmyanmar.mmhealthcare_kotlin_zmp.network

import android.telecom.Call
import com.padcmyanmar.mmhealthcare_kotlin_zmp.network.responses.GetHeathCareResponse
import com.padcmyanmar.mmhealthcare_kotlin_zmp.utils.MMHealthCareConstants

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HealthCareApi {

    @FormUrlEncoded
    @POST("GetHealthcareInfo.php")
    fun loadHealthCareInfoLists(
            @Field("access_token") accessToken: String) : retrofit2.Call<GetHeathCareResponse>

}