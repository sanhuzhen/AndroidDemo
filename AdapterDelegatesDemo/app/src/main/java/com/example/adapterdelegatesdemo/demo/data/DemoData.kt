package com.example.adapterdelegatesdemo.demo.data

import com.example.adapterdelegatesdemo.R

/**
 * Demo数据类 - 展示AdapterDelegate的多种使用场景
 */

// 基础数据接口
interface DemoItem {
    val id: String
    val type: Int
}

// 文本类型数据
data class TextItem(
    override val id: String,
    val title: String,
    val subtitle: String? = null
) : DemoItem {
    override val type: Int = 1
}

// 图片类型数据
data class ImageItem(
    override val id: String,
    val imageUrl: Int, // 使用资源ID模拟图片
    val description: String
) : DemoItem {
    override val type: Int = 2
}

// 混合类型数据（标题+内容）
data class MixedItem(
    override val id: String,
    val header: String,
    val content: String,
    val icon: Int = R.drawable.ic_launcher_background
) : DemoItem {
    override val type: Int = 3
}

// 功能按钮数据
data class ActionItem(
    override val id: String,
    val actionName: String,
    val actionIcon: Int,
    val description: String
) : DemoItem {
    override val type: Int = 4
}

// 示例数据集合
val demoDataList = listOf(
    TextItem("1", "欢迎使用AdapterDelegate", "这是一个强大的RecyclerView适配器库"),
    ImageItem("2", R.drawable.ic_launcher_foreground, "图片展示示例"),
    TextItem("3", "多类型支持", "轻松处理多种ViewHolder类型"),
    MixedItem("4", "混合布局", "支持复杂的组合布局模式"),
    ActionItem("5", "点击操作", R.drawable.ic_launcher_background, "支持点击事件处理"),
    ExpandableItem("6", "可展开项目", listOf("这是展开后的详细信息", "可以显示更多内容", "支持多行文本"), false),
    TextItem("7", "高性能", "优化的ViewHolder复用机制"),
    CardItem("8", "卡片布局", "这是一个卡片样式的item，可以显示更复杂的内容", "卡片底部信息", 0xFFE3F2FD.toInt()),
    ActionItem("9", "长按操作", R.drawable.ic_launcher_background, "支持长按事件"),
    GroupItem("10", "分组示例", listOf(
        TextItem("10-1", "组内项目1", "属于分组的内容"),
        TextItem("10-2", "组内项目2", "属于分组的内容")
    )),
    TextItem("11", "灵活配置", "可以自定义各种样式和行为"),
    ImageItem("12", R.drawable.ic_launcher_foreground, "更多图片展示"),
    ExpandableItem("13", "另一个可展开项", listOf("细节1", "细节2", "细节3", "细节4"), true),
    CardItem("14", "彩色卡片", "卡片可以自定义背景色", "不同颜色展示", 0xFFFFF3E0.toInt()),
    ActionItem("15", "组合操作", R.drawable.ic_launcher_background, "多种操作组合"),
    MixedItem("16", "最终示例", "展示了所有基础功能")
)

// 分组数据示例
data class GroupItem(
    override val id: String,
    val groupName: String,
    val items: List<DemoItem>
) : DemoItem {
    override val type: Int = 5
}

// 带有状态的数据
data class ExpandableItem(
    override val id: String,
    val title: String,
    val details: List<String>,
    var isExpanded: Boolean = false
) : DemoItem {
    override val type: Int = 6
}

// 复杂的嵌套数据
data class CardItem(
    override val id: String,
    val cardTitle: String,
    val cardContent: String,
    val footer: String,
    val backgroundColor: Int = 0xFFF5F5F5.toInt()
) : DemoItem {
    override val type: Int = 7
}