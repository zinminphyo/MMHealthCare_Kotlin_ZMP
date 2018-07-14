package com.padcmyanmar.mmhealthcare_kotlin_zmp.adapters

import android.content.Context
import android.view.ViewGroup
import com.padcmyanmar.mmhealthcare_kotlin_zmp.R
import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos.HealthCareVO
import com.padcmyanmar.mmhealthcare_kotlin_zmp.viewholders.BaseViewHolder
import com.padcmyanmar.mmhealthcare_kotlin_zmp.viewholders.HealthCareViewHolder

class HealthCareAdapter(context: Context) : BaseRecyclerAdapter<HealthCareViewHolder, HealthCareVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthCareViewHolder {
        val mHealthCareView = mLayoutInflater.inflate(
                R.layout.view_holder_healthcare, parent, false)
        return HealthCareViewHolder(mHealthCareView)
    }
}