package com.sanhuzhen.handle.kotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ProgressBar
import com.sanhuzhen.handle.base.BaseJavaActivity
import com.sanhuzhen.handle.databinding.ActivityKotlindemoBinding
import java.lang.ref.WeakReference

class KotlinDemoActivity : BaseJavaActivity<ActivityKotlindemoBinding>() {

    private lateinit var handlerAddThreadProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handlerAddThreadProgressBar = binding.pbKotlin
        binding.btKotlin.setOnClickListener {

            // 启动工作线程
            val workThread = WorkThread(this)
            workThread.start()
        }
    }

    override fun getViewBinding() = ActivityKotlindemoBinding.inflate(layoutInflater)

    private class WorkThread(activity: KotlinDemoActivity) : Thread() {
        private val handler = MyHandler(activity)

        override fun run() {
            for (i in 0..6) {
                sleep(1000) // 模拟下载任务
                val message = handler.obtainMessage(1, i)
                handler.sendMessage(message)
            }
        }
    }

    /**
     * 静态内部类，避免内存泄漏
     */
    private class MyHandler(activity: KotlinDemoActivity) : Handler(Looper.getMainLooper()) {
        private val weakReference = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                val activity = weakReference.get()
                activity?.let {
                    val progressValue = msg.obj as Int
                    it.handlerAddThreadProgressBar.progress = progressValue
                }
            }
        }
    }
}