package com.example.adapterdelegatesdemo.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {

    // 在list的位置
    private var mPosition: Int = 0
    private var mData: T? = null
    private var mDataList: List<T>? = null

    private val mContextWeakReference: WeakReference<Context?> =
        WeakReference<Context?>(itemView.context)

    fun setPosition(position: Int) {
        mPosition = position
    }

    fun getMPosition(): Int {
        return mPosition
    }

    fun bindView(data: T, dataList: List<T>?, position: Int) {
        mData = data
        mDataList = dataList
        onBindView(data, position)
    }

    protected open fun onBindView(data: T, position: Int) {
        mData = data
    }


}