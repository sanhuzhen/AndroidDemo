package com.sanhuzhen.roomdemo.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @description: 定义数据库中的'user_entity'表和表中的数据结构
 * @author: sanhuzhen
 * @date: 2024/8/2 22:47
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */

@Entity(tableName = "user_entity")
data class UserEntity(
    //设置主键，帮助我们在插入相同id时给予冲突策略
    @PrimaryKey
    var id : Int,

    //设置列名
    @ColumnInfo(name = "user_name")
    var name : String,
    @ColumnInfo(name = "user_age")
    var age : Int,
    @ColumnInfo(name = "user_address")
    var address : String
)
