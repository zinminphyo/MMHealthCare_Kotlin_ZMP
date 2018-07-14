package com.padcmyanmar.mmhealthcare_kotlin_zmp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos.HealthCareVO
import java.util.ArrayList

class GetHeathCareResponse {

    @SerializedName("code")
    private val code: Int = 0

    @SerializedName("message")
    private val message: String? = null

    @SerializedName("healthcare-info")
    private var healthCareLists: List<HealthCareVO>? = null

    fun getCode(): Int {
        return code
    }

    fun getMessage(): String? {
        return message
    }

    fun getHealthCareLists(): List<HealthCareVO> {
        if (healthCareLists == null) {
            healthCareLists = ArrayList()
        }
        val healthListVar = healthCareLists
        return healthListVar!!
    }

    fun isResponseOk(): Boolean {
        if (code == 200 && healthCareLists != null) {
            return true
        }
        return false
    }
}