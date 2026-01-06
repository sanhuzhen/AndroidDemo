package com.example.adapterdelegatesdemo.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.adapterdelegatesdemo.R
import com.example.adapterdelegatesdemo.base.BaseViewHolder
import com.example.adapterdelegatesdemo.data.Data1

class Type1ViewHolder(itemView: View) : BaseViewHolder<Data1>(itemView) {
    private var textView: TextView? = null
    private var imageView: ImageView? = null

    init {
        textView = itemView.findViewById(R.id.textView)
        imageView = itemView.findViewById(R.id.imageView)
    }

    override fun onBindView(data: Data1, position: Int) {
        super.onBindView(data, position)
        textView?.text = data.title
        imageView?.setImageResource(data.imageSource)
    }
}