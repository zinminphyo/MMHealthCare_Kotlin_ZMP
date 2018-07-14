package com.padcmyanmar.mmhealthcare_kotlin_zmp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos.HealthCareVO
import com.padcmyanmar.mmhealthcare_kotlin_zmp.viewholders.BaseViewHolder

abstract class BaseRecyclerAdapter<VH, D>(context: Context) : RecyclerView.Adapter<BaseViewHolder<D>>() {

    protected var mData: MutableList<D>? = null
    protected var mLayoutInflater: LayoutInflater

    val items: List<D>
        get() {
            val data = mData
            return data ?: ArrayList()
        }


    init {
        mData = ArrayList()
        mLayoutInflater = LayoutInflater.from(context)
    }


    override fun getItemCount(): Int {
        return mData!!.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: BaseViewHolder<D>, position: Int) {
        //To change body of created functions use File | Settings | File Templates.
        holder.setData(mData!![position])
    }
    fun appendNewData(healthLists:List<D>){
        mData!!.addAll(healthLists)
        notifyDataSetChanged()
    }
}