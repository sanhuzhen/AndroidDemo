package com.sanhuzhen.roomdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.sanhuzhen.roomdemo.bean.UserEntity
import com.sanhuzhen.roomdemo.helper.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
 * @description: 使用Flow来获取数据，从而达到不需手动更新UI的效果
 * @author: sanhuzhen
 * @date: 2024/8/4 0:00
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
class MainFlowActivity :AppCompatActivity() {

    private val userDataBase by lazy {
        Room.databaseBuilder(this, UserDataBase::class.java, "user_db").build()
    }

    private val et_id by lazy { findViewById<EditText>(R.id.et_id) }
    private val et_name by lazy { findViewById<EditText>(R.id.et_name) }
    private val et_age by lazy { findViewById<EditText>(R.id.et_age) }
    private val et_address by lazy { findViewById<EditText>(R.id.et_address) }
    private val bt_insert by lazy { findViewById<Button>(R.id.btn_insert) }
    private val bt_delete by lazy { findViewById<Button>(R.id.btn_delete) }
    private val bt_update by lazy { findViewById<Button>(R.id.btn_update) }
    private val bt_flow by lazy { findViewById<Button>(R.id.btn_flow) }
    private val rv_user by lazy { findViewById<RecyclerView>(R.id.recyclerView_user) }
    private val userAdapter by lazy { UserAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainflow)
        initRecyclerView()
        initClick()
        updateUI()
    }

    private fun initClick() {
        bt_insert.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    userDataBase.userFlowDao().insert(createUser())
                }
            }
        }

        bt_delete.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    userDataBase.userFlowDao().delete(createUser())
                }
            }
        }

        bt_update.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    userDataBase.userFlowDao().update(createUser())
                }
            }
        }
    }

    /**
     * 创建UserEntity实例
     */
    private fun createUser(): UserEntity {
        return UserEntity(
            id = et_id.text.toString().toInt(),
            name = et_name.text.toString(),
            age = et_age.text.toString().toInt(),
            address = et_address.text.toString()
        )
    }
    private fun initRecyclerView() {
        rv_user.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainFlowActivity)
        }
    }

    private fun updateUI() {
        /**
         * 使用Flow来获取数据，从而实现自动更新UI
         */
        userDataBase.userFlowDao().queryAll().onEach {
            userAdapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}