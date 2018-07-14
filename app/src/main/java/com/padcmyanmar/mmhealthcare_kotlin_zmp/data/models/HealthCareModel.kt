package com.padcmyanmar.mmhealthcare_kotlin_zmp.data.models

import android.util.Log
import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos.HealthCareVO
import com.padcmyanmar.mmhealthcare_kotlin_zmp.events.FailHealthCareList
import com.padcmyanmar.mmhealthcare_kotlin_zmp.events.SuccessGetHealthCareEvent
import com.padcmyanmar.mmhealthcare_kotlin_zmp.network.RetrofitDataAgentImpl
import com.padcmyanmar.mmhealthcare_kotlin_zmp.utils.MMHealthCareConstants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HealthCareModel {

    companion object {
        private var INSTANE: HealthCareModel? = null

        fun getInstance(): HealthCareModel {
            if (INSTANE == null) {
                INSTANE = HealthCareModel()

            }
            val i = INSTANE
            return i!!
        }
    }

    private constructor() {
        EventBus.getDefault().register(this)
    }

    private var mHealthCareLists: HashMap<Int, HealthCareVO> = HashMap()

    fun loadHealthCare() {
        RetrofitDataAgentImpl.getInStance().loadHealthCareInfoLists(MMHealthCareConstants.ACCESS_TOKEN)
    }

    fun forceLoadHealthCare(){
        mHealthCareLists=HashMap()
        RetrofitDataAgentImpl.getInStance().loadHealthCareInfoLists(MMHealthCareConstants.ACCESS_TOKEN)
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onLoadedHealthCareLists(healthListEvent: SuccessGetHealthCareEvent.HealthCareEvent) {
        for (healthCareLists: HealthCareVO in healthListEvent.loadHealthCareLists) {
            mHealthCareLists[healthCareLists.healthCareId] = healthCareLists
        }
        Log.d("healthCareListsMap","MapSize"+mHealthCareLists.size)

    }


}