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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    // 创建数据库,传入context,定义的UserDataBase以及数据库文件名
    private val userDataBase by lazy {
        Room.databaseBuilder(
            this,
            UserDataBase::class.java,
            "user_db"
        ).build()
    }

    private val UserData = mutableListOf<UserEntity>()

    private val et_id by lazy { findViewById<EditText>(R.id.et_id) }
    private val et_name by lazy { findViewById<EditText>(R.id.et_name) }
    private val et_age by lazy { findViewById<EditText>(R.id.et_age) }
    private val et_address by lazy { findViewById<EditText>(R.id.et_address) }
    private val bt_insert by lazy { findViewById<Button>(R.id.btn_insert) }
    private val bt_delete by lazy { findViewById<Button>(R.id.btn_delete) }
    private val bt_update by lazy { findViewById<Button>(R.id.btn_update) }
    private val bt_query by lazy { findViewById<Button>(R.id.btn_query) }
    private val rv_user by lazy { findViewById<RecyclerView>(R.id.recyclerView_user) }
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initClick()
    }

    private fun initClick() {
        bt_insert.setOnClickListener {
            lifecycleScope.launch{
                withContext(Dispatchers.IO){
                    val userEntity = createUser()
                    userDataBase.userDao().insert(userEntity)
                    UserData.add(userEntity)
                }
                userAdapter.submitList(UserData)
            }
        }
        bt_delete.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    val userEntity = createUser()
                    userDataBase.userDao().delete(userEntity)
                    if (UserData.contains(userEntity)) {
                        UserData.remove(userEntity)
                    }
                }
                userAdapter.submitList(UserData)
            }
        }
        bt_update.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    val userEntity = createUser()
                    userDataBase.userDao().update(userEntity)
                    if (UserData.contains(userEntity)) {
                        UserData.remove(userEntity)
                        UserData.add(userEntity)

                    }
                }
                userAdapter.submitList(UserData)
            }
        }
        bt_query.setOnClickListener {
            lifecycleScope.launch {
                val userEntity = userDataBase.userDao().queryById(1)
                userEntity?.let {
                    et_id.setText(it.id.toString())
                    et_name.setText(it.name)
                    et_age.setText(it.age.toString())
                    et_address.setText(it.address)
                }
            }
        }
    }


    // 创建UserEntity
    private fun createUser(): UserEntity {
        val id = et_id.text.toString().toInt()
        val name = et_name.text.toString()
        val age = et_age.text.toString().toInt()
        val address = et_address.text.toString()
        return UserEntity(id = id, name = name, age = age, address = address)
    }

    private fun initRecyclerView() {
        rv_user.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}