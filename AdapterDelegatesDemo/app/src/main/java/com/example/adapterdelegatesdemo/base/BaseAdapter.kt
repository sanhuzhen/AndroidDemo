package com.example.adapterdelegatesdemo.base

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

abstract class BaseAdapter<T> : ListDelegationAdapter<List<T?>?>() {
    protected val mDelegateList: MutableList<BaseAdapterDelegate<T>?> =
        ArrayList<BaseAdapterDelegate<T>?>()

    protected fun addDelegate(delegatesToAdd: MutableList<BaseAdapterDelegate<T>?>?) {
        delegatesToAdd?.let { list ->
            // 先复制一份避免并发修改
            val tempList = ArrayList(list)
            tempList.forEach { delegate ->
                delegate?.let {
                    mDelegateList.add(it)  // 先添加到主列表
                    delegatesManager.addDelegate(it)  // 再注册到delegatesManager
                }
            }
        }
    }

    protected fun removeAllDelegates() {
        mDelegateList.forEach { delegate ->
            delegate?.let {
                delegatesManager.removeDelegate(it)
            }
        }
        mDelegateList.clear()
    }

    protected fun setPosition(position: Int) {
        mDelegateList.forEach { delegate ->
            if (delegate is BaseAdapterDelegate<*>) {
                delegate.setPosition(position)
            }
        }
    }

    fun setData(list: List<T>) {
        items = list.map { it as T? }
        notifyDataSetChanged()
    }

}