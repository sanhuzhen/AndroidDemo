package com.sanhuzhen.sideslip

import android.os.Bundle
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


    }
    private fun initList() {
        repeat(20){
            DataList.add("这是第${it}条数据")
        }
    }
}