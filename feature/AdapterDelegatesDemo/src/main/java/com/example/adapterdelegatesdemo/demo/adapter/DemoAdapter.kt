package com.example.adapterdelegatesdemo.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegatesdemo.R
import com.example.adapterdelegatesdemo.demo.data.*
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

/**
 * 使用AdapterDelegates库的简洁Adapter实现
 * 展示库的核心功能和最佳实践
 *
 * 为了避免ViewBinding相关的问题，这里使用传统方式实现所有Delegate
 */

class DemoAdapter : ListDelegationAdapter<List<DemoItem>>() {

    private var actionClickHandler: ((ActionItem) -> Unit)? = null
    private var actionLongClickHandler: ((ActionItem) -> Boolean)? = null

    init {
        // 添加所有类型的delegate
        addDelegates()
    }

    fun setActionClickHandlers(
        click: (ActionItem) -> Unit,
        longClick: (ActionItem) -> Boolean
    ) {
        actionClickHandler = click
        actionLongClickHandler = longClick
    }

    private fun addDelegates() {
        delegatesManager.apply {
            // 1. 文本类型Delegate
            addDelegate(object : AdapterDelegate<List<DemoItem>>() {
                override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
                    return items[position] is TextItem
                }

                override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_demo_text, parent, false)
                    return TextViewHolder(view)
                }

                override fun onBindViewHolder(
                    items: List<DemoItem>,
                    position: Int,
                    holder: RecyclerView.ViewHolder,
                    payloads: List<Any>
                ) {
                    val item = items[position] as TextItem
                    (holder as TextViewHolder).bind(item)
                }
            })

            // 2. 图片类型Delegate
            addDelegate(object : AdapterDelegate<List<DemoItem>>() {
                override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
                    return items[position] is ImageItem
                }

                override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_demo_image, parent, false)
                    return ImageViewHolder(view)
                }

                override fun onBindViewHolder(
                    items: List<DemoItem>,
                    position: Int,
                    holder: RecyclerView.ViewHolder,
                    payloads: List<Any>
                ) {
                    val item = items[position] as ImageItem
                    (holder as ImageViewHolder).bind(item)
                }
            })

            // 3. 混合类型Delegate
            addDelegate(object : AdapterDelegate<List<DemoItem>>() {
                override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
                    return items[position] is MixedItem
                }

                override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_demo_mixed, parent, false)
                    return MixedViewHolder(view)
                }

                override fun onBindViewHolder(
                    items: List<DemoItem>,
                    position: Int,
                    holder: RecyclerView.ViewHolder,
                    payloads: List<Any>
                ) {
                    val item = items[position] as MixedItem
                    (holder as MixedViewHolder).bind(item)
                }
            })

            // 4. 动作类型Delegate
            addDelegate(object : AdapterDelegate<List<DemoItem>>() {
                override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
                    return items[position] is ActionItem
                }

                override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_demo_action, parent, false)
                    return ActionViewHolder(view)
                }

                override fun onBindViewHolder(
                    items: List<DemoItem>,
                    position: Int,
                    holder: RecyclerView.ViewHolder,
                    payloads: List<Any>
                ) {
                    val item = items[position] as ActionItem
                    (holder as ActionViewHolder).bind(
                        item,
                        { actionClickHandler?.invoke(it) },
                        { actionLongClickHandler?.invoke(it) ?: false }
                    )
                }
            })

            // 5. 分组类型Delegate
            addDelegate(object : AdapterDelegate<List<DemoItem>>() {
                override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
                    return items[position] is GroupItem
                }

                override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_demo_group, parent, false)
                    return GroupViewHolder(view)
                }

                override fun onBindViewHolder(
                    items: List<DemoItem>,
                    position: Int,
                    holder: RecyclerView.ViewHolder,
                    payloads: List<Any>
                ) {
                    val item = items[position] as GroupItem
                    (holder as GroupViewHolder).bind(item)
                }
            })

            // 6. 可展开类型Delegate
            addDelegate(object : AdapterDelegate<List<DemoItem>>() {
                override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
                    return items[position] is ExpandableItem
                }

                override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_demo_expandable, parent, false)
                    return ExpandableViewHolder(view)
                }

                override fun onBindViewHolder(
                    items: List<DemoItem>,
                    position: Int,
                    holder: RecyclerView.ViewHolder,
                    payloads: List<Any>
                ) {
                    val item = items[position] as ExpandableItem
                    (holder as ExpandableViewHolder).bind(item) { expandedItem ->
                        expandedItem.isExpanded = !expandedItem.isExpanded
                        notifyItemChanged(position)
                    }
                }
            })

            // 7. 卡片类型Delegate
//            addDelegate(object : AdapterDelegate<List<DemoItem>>() {
//                override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
//                    return items[position] is CardItem
//                }
//
//                override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
//                    val view = LayoutInflater.from(parent.context)
//                        .inflate(R.layout.item_demo_card, parent, false)
//                    return CardViewHolder(view)
//                }
//
//                override fun onBindViewHolder(
//                    items: List<DemoItem>,
//                    position: Int,
//                    holder: RecyclerView.ViewHolder,
//                    payloads: List<Any>
//                ) {
//                    val item = items[position] as CardItem
//                    (holder as CardViewHolder).bind(item)
//                }
//            })
            addDelegate(
                adapterDelegate<CardItem, DemoItem>(
                    layout = R.layout.item_demo_card,
                    on = { item: DemoItem, _, _ ->
                        item is CardItem
                    }
                ) {
                    val cardView: CardView = itemView.findViewById(R.id.card_view)
                    val title: TextView = itemView.findViewById(R.id.card_title)
                    val content: TextView = itemView.findViewById(R.id.card_content)
                    val footer: TextView = itemView.findViewById(R.id.card_footer)

                    bind { _ ->
                        cardView.setCardBackgroundColor(item.backgroundColor)
                        title.text = item.cardTitle
                        content.text = item.cardContent
                        footer.text = item.footer
                    }
                })
        }
    }

    // 以下是各种ViewHolder的定义（为了简化，直接放在同一个文件中）

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.item_title)
        private val subtitle: TextView = itemView.findViewById(R.id.item_subtitle)

        fun bind(item: TextItem) {
            title.text = item.title
            subtitle.text = item.subtitle ?: ""
            subtitle.visibility = if (item.subtitle.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.item_image)
        private val description: TextView = itemView.findViewById(R.id.item_description)

        fun bind(item: ImageItem) {
            image.setImageResource(item.imageUrl)
            description.text = item.description
        }
    }

    class MixedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val header: TextView = itemView.findViewById(R.id.item_header)
        private val content: TextView = itemView.findViewById(R.id.item_content)
        private val icon: ImageView = itemView.findViewById(R.id.item_icon)

        fun bind(item: MixedItem) {
            header.text = item.header
            content.text = item.content
            icon.setImageResource(item.icon)
        }
    }

    class ActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actionName: TextView = itemView.findViewById(R.id.item_action_name)
        private val actionIcon: ImageView = itemView.findViewById(R.id.item_action_icon)
        private val description: TextView = itemView.findViewById(R.id.item_action_description)
        private val container: ConstraintLayout = itemView.findViewById(R.id.action_container)

        fun bind(
            item: ActionItem,
            clickListener: (ActionItem) -> Unit,
            longClickListener: (ActionItem) -> Boolean
        ) {
            actionName.text = item.actionName
            actionIcon.setImageResource(item.actionIcon)
            description.text = item.description

            container.setOnClickListener { clickListener(item) }
            container.setOnLongClickListener { longClickListener(item) }
        }
    }

    class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val groupName: TextView = itemView.findViewById(R.id.group_name)
        private val itemCount: TextView = itemView.findViewById(R.id.item_count)

        fun bind(item: GroupItem) {
            groupName.text = item.groupName
            itemCount.text = "${item.items.size} items"
        }
    }

    class ExpandableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.expandable_title)
        private val indicator: ImageView = itemView.findViewById(R.id.expandable_indicator)
        private val detailsContainer: ConstraintLayout =
            itemView.findViewById(R.id.details_container)
        private val detailsText: TextView = itemView.findViewById(R.id.expandable_details)

        fun bind(item: ExpandableItem, expandListener: (ExpandableItem) -> Unit) {
            title.text = item.title
            indicator.setImageResource(
                if (item.isExpanded) R.drawable.ic_launcher_foreground
                else R.drawable.ic_launcher_background
            )

            detailsContainer.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            if (item.isExpanded) {
                detailsText.text = item.details.joinToString("\n")
            }

            itemView.setOnClickListener { expandListener(item) }
        }
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.card_view)
        private val title: TextView = itemView.findViewById(R.id.card_title)
        private val content: TextView = itemView.findViewById(R.id.card_content)
        private val footer: TextView = itemView.findViewById(R.id.card_footer)

        fun bind(item: CardItem) {
            title.text = item.cardTitle
            content.text = item.cardContent
            footer.text = item.footer
            cardView.setCardBackgroundColor(item.backgroundColor)
        }
    }
}