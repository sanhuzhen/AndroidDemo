package com.example.adapterdelegatesdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegatesdemo.adapter.MainRvDelegateAdapter
import com.example.adapterdelegatesdemo.data.list1
import com.example.adapterdelegatesdemo.demo.DemoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var originalRv: RecyclerView
    private lateinit var btnOriginal: Button
    private lateinit var btnDemo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        // 原始RecyclerView（隐藏状态，点击按钮后显示）
        originalRv = findViewById(R.id.recycler_view)
        originalRv.apply {
            adapter = MainRvDelegateAdapter(list1)
            layoutManager = LinearLayoutManager(this@MainActivity)
            visibility = android.view.View.GONE
        }

        // 按钮
        btnOriginal = findViewById(R.id.btn_original)
        btnDemo = findViewById(R.id.btn_demo)
    }

    private fun setupClickListeners() {
        // 原始实现按钮 - 切换显示/隐藏原始RecyclerView
        btnOriginal.setOnClickListener {
            if (originalRv.visibility == android.view.View.GONE) {
                originalRv.visibility = android.view.View.VISIBLE
                btnOriginal.text = "隐藏原始实现"
            } else {
                originalRv.visibility = android.view.View.GONE
                btnOriginal.text = "查看原始实现"
            }
        }

        // Demo按钮 - 启动新的Demo Activity
        btnDemo.setOnClickListener {
            val intent = Intent(this, DemoActivity::class.java)
            startActivity(intent)
        }
    }
}