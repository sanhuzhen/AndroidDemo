package com.example.adapterdelegatesdemo.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegatesdemo.R
import com.example.adapterdelegatesdemo.data.Data1

enum class ItemType {
    ITEM1,
    ITEM2,
    ITEM3
}

class MainRvAdapter(val list: List<Data1>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return if (list[position].title == "Item 1") {
            ItemType.ITEM1.ordinal;
        } else {
            ItemType.ITEM2.ordinal;
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == ItemType.ITEM1.ordinal) {
            MainRvViewHolder(View.inflate(parent.context, R.layout.rv_item1, null))
        } else {
            MainRvViewHolder2(View.inflate(parent.context, R.layout.rv_item2, null))
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is MainRvViewHolder) {
            holder.bind(list[position])
        } else if (holder is MainRvViewHolder2) {
            holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MainRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(data1: Data1) {
            tv.text = data1.title
            imageView.setImageResource(data1.imageSource)
        }
    }

    class MainRvViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        fun bind(data1: Data1) {
            tv.text = data1.title
            imageView.setImageResource(data1.imageSource)
        }
    }
}