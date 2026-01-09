package com.example.adapterdelegatesdemo.adapter

import com.example.adapterdelegatesdemo.adapter.delegate.Type1Delegate
import com.example.adapterdelegatesdemo.adapter.delegate.Type2Delegate
import com.example.adapterdelegatesdemo.base.BaseAdapter
import com.example.adapterdelegatesdemo.data.Data1

class MainRvDelegateAdapter(list: List<Data1>) : BaseAdapter<Data1>() {
    init {
        removeAllDelegates()
        addDelegate(
            mutableListOf(
                Type1Delegate(),
                Type2Delegate()
            )
        )
        setData(list)
    }
}