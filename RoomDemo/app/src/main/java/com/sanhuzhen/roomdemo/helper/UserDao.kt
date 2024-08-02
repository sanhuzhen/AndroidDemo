package com.sanhuzhen.roomdemo.helper

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sanhuzhen.roomdemo.bean.UserEntity


/**
 * @description: 管理和操作数据库中的表，包括常见的增、删、改、查等操作
 * @author: sanhuzhen
 * @date: 2024/8/2 22:51
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
@Dao
interface UserDao {
    /**
     * 插入数据，当主键冲突时选择直接覆盖原数据
     *
     * 可选数据：
     * OnConflictStrategy.REPLACE 如果发生冲突，直接覆盖已有数据，将表中现存的数据替换成插入的这条；
     * OnConflictStrategy.IGNORE 如果发生冲突，直接忽略此次插入操作
     * OnConflictStrategy.NONE 这个是默认的策略，它和ABORT作用是一致的，都是终止此次插入操作，并且抛出SQLiteConstraintException
     * OnConflictStrategy.ROLLBACK 这个表示如果发生冲突，终止插入操作，并且将事务回滚到最初的状态，在最新版本已经被标记@Deprecated推荐使用ABORT
     * OnConflictStrategy.ABORT 和NONE作用一致，这里就不过多介绍
     * OnConflictStrategy.FAIL 这个表示如果发生冲突，终止插入操作，并且抛出SQLiteConstraintException异常，在最新版本也是被标记@Deprecated，也是推荐使用ABORT。
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    /**
     * 删除数据
     */
    @Delete
    fun delete(userEntity: UserEntity)

    /**
     * 删除所有用户的注解，使用Query注解可以执行自定义的SQL语句
     */
    @Query("DELETE FROM user_entity")
    fun deleteAll()

    /**
     * 更新数据
     */
    @Update
    fun update(userEntity: UserEntity)

    /**
     * 查询数据
     */
    @Query("select * from user_entity where id=:id")
    fun queryById(id : Int): UserEntity?

    @Query("select * from user_entity")
    fun queryAll(): List<UserEntity>
}