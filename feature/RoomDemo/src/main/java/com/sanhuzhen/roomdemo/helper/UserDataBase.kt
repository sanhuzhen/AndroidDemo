package com.sanhuzhen.feature.roomdemo.helper

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanhuzhen.feature.roomdemo.bean.UserEntity


/**
 * @description: 用来继承RoomDatabase()，实例化Dao，从而管理数据库
 * @author: sanhuzhen
 * @date: 2024/8/2 23:22
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
//@DataBase标识这是一个Database组件，entities表示DataBase拥有的实体，可以传入多个，version用于数据库版本更新，这里填1，当然@Database还可以设置其它的参数，这里就不详解了
@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    //定义获取上面定义的UserDao的抽象方法
    abstract fun userDao(): UserDao

    /**
     * 新增方法得到UserFlowDao的实例
     */
    abstract fun userFlowDao(): UserFlowDao
}