package com.example.adapterdelegatesdemo.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapterdelegatesdemo.databinding.ActivityDemoBinding
import com.example.adapterdelegatesdemo.demo.adapter.DemoAdapter
import com.example.adapterdelegatesdemo.demo.data.*

/**
 * AdapterDelegate Demo Activity
 * 展示第三方库AdapterDelegates4的各种使用场景
 */
class DemoActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDemoBinding
    private lateinit var adapter: DemoAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        loadData()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "AdapterDelegate Demo"
    }
    
    private fun setupRecyclerView() {
        adapter = DemoAdapter()

        // 设置ActionItem的点击和长按事件
        adapter.setActionClickHandlers(
            click = { item ->
                onActionItemClick(item)
            },
            longClick = { item ->
                onActionItemLongClick(item)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DemoActivity)
            adapter = this@DemoActivity.adapter
        }
    }
    
    private fun loadData() {
        // 显示加载状态
        binding.progressBar.visibility = android.view.View.GONE
        binding.emptyView.visibility = android.view.View.GONE
        
        // 设置数据
        adapter.items = demoDataList
        adapter.notifyDataSetChanged()
        
        // 如果没有数据，显示空状态
        if (demoDataList.isEmpty()) {
            binding.recyclerView.visibility = android.view.View.GONE
            binding.emptyView.visibility = android.view.View.VISIBLE
        } else {
            binding.recyclerView.visibility = android.view.View.VISIBLE
            binding.emptyView.visibility = android.view.View.GONE
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    
    // 演示不同类型的点击事件处理
    private fun onActionItemClick(item: ActionItem) {
        Toast.makeText(this, "点击: ${item.actionName}", Toast.LENGTH_SHORT).show()
    }
    
    private fun onActionItemLongClick(item: ActionItem): Boolean {
        Toast.makeText(this, "长按: ${item.actionName}", Toast.LENGTH_SHORT).show()
        return true
    }
}