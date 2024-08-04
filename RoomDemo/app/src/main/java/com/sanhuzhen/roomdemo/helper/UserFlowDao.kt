package com.sanhuzhen.roomdemo.helper

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sanhuzhen.roomdemo.bean.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */
/**
 * @description: 用法与UserDao相同，但我们使用Flow来获取数据
 * @author: sanhuzhen
 * @date: 2024/8/3 23:51
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
@Dao
interface UserFlowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user_entity")
    fun deleteAll()

    @Update
    fun update(userEntity: UserEntity)
    /**
     * 查询所有数据，并用Flow返回所有数据
     */
    @Query("select * from user_entity")
    fun queryAll(): Flow<List<UserEntity>>

    @Query("select * from user_entity where id=:id")
    fun queryById(id : Int): Flow<UserEntity?>

}