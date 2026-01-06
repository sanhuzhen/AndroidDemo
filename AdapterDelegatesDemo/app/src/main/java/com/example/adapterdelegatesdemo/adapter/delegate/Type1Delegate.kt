package com.example.adapterdelegatesdemo.adapter.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegatesdemo.R
import com.example.adapterdelegatesdemo.adapter.viewholder.Type1ViewHolder
import com.example.adapterdelegatesdemo.base.BaseAdapterDelegate
import com.example.adapterdelegatesdemo.data.Data1

class Type1Delegate() : BaseAdapterDelegate<Data1>() {
    override fun isForType(item: Data1): Boolean {
        return item.title == "Item 1"
    }

    override fun getLayoutId(): Int {
        return R.layout.rv_item1
    }

    override fun onCreateViewHolder(p0: ViewGroup): RecyclerView.ViewHolder {
        return Type1ViewHolder(createHolderView(p0))
    }
}