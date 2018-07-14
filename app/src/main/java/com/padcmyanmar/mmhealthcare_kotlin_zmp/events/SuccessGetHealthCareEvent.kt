package com.padcmyanmar.mmhealthcare_kotlin_zmp.events

import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos.HealthCareVO

class SuccessGetHealthCareEvent {

    class HealthCareEvent(val loadHealthCareLists: List<HealthCareVO>)

    class ErreorEvent(val errorMsg: String? = "Empty data response")
}