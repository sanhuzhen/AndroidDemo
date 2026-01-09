package com.sanhuzhen.sideslip

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.sideslip.helper.ItemTouchHelperCallback


class MainActivity : AppCompatActivity() {

    private val DataList = mutableListOf<String>()
    private val rv by lazy {
        findViewById<RecyclerView>(R.id.rv_main)
    }

    private val rvAdapter by lazy { RvAdapter() }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        rv.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        rvAdapter.submitList(DataList)
        val itemTouchHelperCallback = ItemTouchHelperCallback(rvAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rv)
        findViewById<Button>(R.id.btn_slip).setOnClickListener {
            startActivity(Intent(this,Main2Activity::class.java))
        }

    }
    private fun initList() {
        repeat(20){
            DataList.add("这是第${it}条数据")
        }
    }
}