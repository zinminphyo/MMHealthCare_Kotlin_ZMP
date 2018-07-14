package com.padcmyanmar.mmhealthcare_kotlin_zmp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos.HealthCareVO
import kotlinx.android.synthetic.main.view_holder_healthcare.view.*

class HealthCareViewHolder(itemView: View) : BaseViewHolder<HealthCareVO>(itemView) {
    override fun setData(data: HealthCareVO) {
        mData = data
        Glide.with(itemView.context)
                .load(data.healthCareImage)
                .into(itemView.iv_healthCare_photo)

        itemView.tv_healthCare_title!!.text=data.healthCareTitle

    }

    override fun onClick(p0: View?) {

    }
}