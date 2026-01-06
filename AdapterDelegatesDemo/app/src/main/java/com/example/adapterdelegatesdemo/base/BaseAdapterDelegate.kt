package com.example.adapterdelegatesdemo.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

abstract class BaseAdapterDelegate<T> : AdapterDelegate<List<T?>?>() {

    private var positon = 0

    abstract fun isForType(item: T): Boolean
    abstract fun getLayoutId(): Int

    override fun isForViewType(p0: List<T?>, p1: Int): Boolean {
        if (p1 >= p0.size) {
            throw IndexOutOfBoundsException("Index out of bounds")
        }
        val data = p0[p1]
        data?.let {
            return isForType(it)
        }
        throw NullPointerException("Index out of bounds")
    }

    protected fun createHolderView(parent: ViewGroup): View {
        return View.inflate(parent.context, getLayoutId(), null)
    }


    override fun onBindViewHolder(
        p0: List<T?>,
        p1: Int,
        p2: RecyclerView.ViewHolder,
        p3: List<Any?>
    ) {
        val data = p0[p1]
        val holder = getBaseViewHolder(p2)
        holder.setPosition(p1)
        holder.bindView(data as T, p0 as List<T>?, p1)
    }

    private fun getBaseViewHolder(viewHolder: RecyclerView.ViewHolder): BaseViewHolder<T>{
        if (viewHolder is BaseViewHolder<*>) return viewHolder as BaseViewHolder<T>
        throw IllegalArgumentException("viewHolder is not a BaseViewHolder")
    }

    fun setPosition(position: Int) {
        this.positon = position
    }
}