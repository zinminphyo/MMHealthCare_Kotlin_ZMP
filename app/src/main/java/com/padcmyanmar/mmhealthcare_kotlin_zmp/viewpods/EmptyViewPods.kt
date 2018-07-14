package com.padcmyanmar.mmhealthcare_kotlin_zmp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_pod_empty.view.*
import java.util.jar.Attributes

class EmptyViewPods : RelativeLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(emptyData: Int, emptyMsg: String) {
        iv_empty_place_holder!!.setImageResource(emptyData)
        tv_Empty!!.text = emptyMsg
    }

    fun setEmptyData(emptyMsg: String) {
        tv_Empty!!.text = emptyMsg
    }
}