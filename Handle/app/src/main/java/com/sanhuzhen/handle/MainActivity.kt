package com.sanhuzhen.handle

import android.content.Intent
import android.os.Bundle
import com.sanhuzhen.handle.base.BaseJavaActivity
import com.sanhuzhen.handle.databinding.ActivityMainBinding
import com.sanhuzhen.handle.java.JavaDemoActivity
import com.sanhuzhen.handle.kotlin.KotlinDemoActivity

class MainActivity : BaseJavaActivity<ActivityMainBinding>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView();
    }

    private fun initView() {
        binding.btn.setOnClickListener {
            startActivity(Intent(this, JavaDemoActivity::class.java))
        }
        binding.btn2.setOnClickListener {
            startActivity(Intent(this, KotlinDemoActivity::class.java))
        }
    }
}