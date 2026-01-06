package com.example.adapterdelegatesdemo.demo.adapter.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegatesdemo.R
import com.example.adapterdelegatesdemo.demo.data.*
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

/**
 * 各种类型的AdapterDelegate示例
 * 展示AdapterDelegate库的多种使用方式
 *
 * 注意：实际项目中推荐使用DemoAdapter.kt中的实现方式
 * 这里提供的是独立的Delegate类示例
 */

// 1. 文本类型Delegate
class TextDelegate : AdapterDelegate<List<DemoItem>>() {
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
}

// 2. 图片类型Delegate
class ImageDelegate : AdapterDelegate<List<DemoItem>>() {
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
}

// 3. 混合类型Delegate
class MixedDelegate : AdapterDelegate<List<DemoItem>>() {
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
}

// 4. 动作类型Delegate（带参数）
class ActionDelegate(
    private val clickListener: (ActionItem) -> Unit,
    private val longClickListener: (ActionItem) -> Boolean
) : AdapterDelegate<List<DemoItem>>() {
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
        (holder as ActionViewHolder).bind(item, clickListener, longClickListener)
    }
}

// 5. 分组类型Delegate
class GroupDelegate : AdapterDelegate<List<DemoItem>>() {
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
}

// 6. 可展开类型Delegate
class ExpandableDelegate(
    private val expandListener: (ExpandableItem) -> Unit
) : AdapterDelegate<List<DemoItem>>() {
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
        (holder as ExpandableViewHolder).bind(item, expandListener)
    }
}

// 7. 卡片类型Delegate
class CardDelegate : AdapterDelegate<List<DemoItem>>() {
    override fun isForViewType(items: List<DemoItem>, position: Int): Boolean {
        return items[position] is CardItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_demo_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<DemoItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        val item = items[position] as CardItem
        (holder as CardViewHolder).bind(item)
    }
}

// ViewHolder类定义
class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<android.widget.TextView>(R.id.item_title)
    private val subtitle = itemView.findViewById<android.widget.TextView>(R.id.item_subtitle)

    fun bind(item: TextItem) {
        title.text = item.title
        subtitle.text = item.subtitle ?: ""
        subtitle.visibility = if (item.subtitle.isNullOrEmpty()) View.GONE else View.VISIBLE
    }
}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image = itemView.findViewById<android.widget.ImageView>(R.id.item_image)
    private val description = itemView.findViewById<android.widget.TextView>(R.id.item_description)

    fun bind(item: ImageItem) {
        image.setImageResource(item.imageUrl)
        description.text = item.description
    }
}

class MixedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val header = itemView.findViewById<android.widget.TextView>(R.id.item_header)
    private val content = itemView.findViewById<android.widget.TextView>(R.id.item_content)
    private val icon = itemView.findViewById<android.widget.ImageView>(R.id.item_icon)

    fun bind(item: MixedItem) {
        header.text = item.header
        content.text = item.content
        icon.setImageResource(item.icon)
    }
}

class ActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val actionName = itemView.findViewById<android.widget.TextView>(R.id.item_action_name)
    private val actionIcon = itemView.findViewById<android.widget.ImageView>(R.id.item_action_icon)
    private val description = itemView.findViewById<android.widget.TextView>(R.id.item_action_description)
    private val container = itemView.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.action_container)

    fun bind(item: ActionItem, clickListener: (ActionItem) -> Unit, longClickListener: (ActionItem) -> Boolean) {
        actionName.text = item.actionName
        actionIcon.setImageResource(item.actionIcon)
        description.text = item.description

        container.setOnClickListener { clickListener(item) }
        container.setOnLongClickListener { longClickListener(item) }
    }
}

class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val groupName = itemView.findViewById<android.widget.TextView>(R.id.group_name)
    private val itemCount = itemView.findViewById<android.widget.TextView>(R.id.item_count)

    fun bind(item: GroupItem) {
        groupName.text = item.groupName
        itemCount.text = "${item.items.size} items"
    }
}

class ExpandableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<android.widget.TextView>(R.id.expandable_title)
    private val indicator = itemView.findViewById<android.widget.ImageView>(R.id.expandable_indicator)
    private val detailsContainer = itemView.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.details_container)
    private val detailsText = itemView.findViewById<android.widget.TextView>(R.id.expandable_details)

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
    private val cardView = itemView.findViewById<androidx.cardview.widget.CardView>(R.id.card_view)
    private val title = itemView.findViewById<android.widget.TextView>(R.id.card_title)
    private val content = itemView.findViewById<android.widget.TextView>(R.id.card_content)
    private val footer = itemView.findViewById<android.widget.TextView>(R.id.card_footer)

    fun bind(item: CardItem) {
        title.text = item.cardTitle
        content.text = item.cardContent
        footer.text = item.footer
        cardView.setCardBackgroundColor(item.backgroundColor)
    }
}