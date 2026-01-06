package com.example.adapterdelettesdemo.demo

import com.example.adapterdelegatesdemo.demo.data.*

/**
 * 简单的测试函数，用于验证数据结构的正确性
 * 可以在开发过程中运行这些测试
 */

fun main() {
    println("=== AdapterDelegate Demo 数据测试 ===")
    
    // 测试数据创建
    val textItem = TextItem("1", "测试标题", "测试副标题")
    println("TextItem: $textItem")
    
    val imageItem = ImageItem("2", 123, "图片描述")
    println("ImageItem: $imageItem")
    
    val mixedItem = MixedItem("3", "混合标题", "混合内容", 456)
    println("MixedItem: $mixedItem")
    
    val actionItem = ActionItem("4", "点击操作", 789, "操作描述")
    println("ActionItem: $actionItem")
    
    val expandableItem = ExpandableItem("5", "可展开", listOf("细节1", "细节2"), false)
    println("ExpandableItem: $expandableItem")
    
    val cardItem = CardItem("6", "卡片标题", "卡片内容", "底部信息")
    println("CardItem: $cardItem")
    
    val groupItem = GroupItem("7", "分组", listOf(textItem, imageItem))
    println("GroupItem: $groupItem")
    
    // 测试数据列表
    val demoList = demoDataList
    println("\n=== Demo数据列表长度: ${demoList.size} ===")
    
    // 按类型统计
    val typeCount = mutableMapOf<String, Int>()
    demoList.forEach { item ->
        when (item) {
            is TextItem -> typeCount["TextItem"] = (typeCount["TextItem"] ?: 0) + 1
            is ImageItem -> typeCount["ImageItem"] = (typeCount["ImageItem"] ?: 0) + 1
            is MixedItem -> typeCount["MixedItem"] = (typeCount["MixedItem"] ?: 0) + 1
            is ActionItem -> typeCount["ActionItem"] = (typeCount["ActionItem"] ?: 0) + 1
            is ExpandableItem -> typeCount["ExpandableItem"] = (typeCount["ExpandableItem"] ?: 0) + 1
            is CardItem -> typeCount["CardItem"] = (typeCount["CardItem"] ?: 0) + 1
            is GroupItem -> typeCount["GroupItem"] = (typeCount["GroupItem"] ?: 0) + 1
        }
    }
    
    println("\n类型统计:")
    typeCount.forEach { (type, count) ->
        println("  $type: $count")
    }
    
    println("\n=== 测试完成 ===")
}

/**
 * 验证数据完整性
 */
fun validateDemoData(): Boolean {
    val data = demoDataList
    
    // 检查是否有空ID
    val hasEmptyId = data.any { it.id.isEmpty() }
    if (hasEmptyId) {
        println("错误: 存在空ID的数据项")
        return false
    }
    
    // 检查是否有重复ID
    val ids = data.map { it.id }
    if (ids.size != ids.toSet().size) {
        println("错误: 存在重复ID")
        return false
    }
    
    // 检查数据类型多样性
    val types = data.map { it::class.simpleName }.toSet()
    if (types.size < 5) {
        println("警告: 数据类型不够丰富")
    }
    
    println("数据验证通过: ${data.size} 项数据，${types.size} 种类型")
    return true
}