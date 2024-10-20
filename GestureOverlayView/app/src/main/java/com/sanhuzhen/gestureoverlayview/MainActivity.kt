package com.sanhuzhen.gestureoverlayview

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), GestureOverlayView.OnGesturePerformedListener,
    GestureOverlayView.OnGesturingListener {
    private lateinit var gestureOverlayView: GestureOverlayView
    //词库
    private lateinit var gestureLibrary: GestureLibrary
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gestureOverlayView = findViewById(R.id.gestures1)
        textView = findViewById(R.id.textView1)
        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures)
        if (gestureLibrary.load()) {
            gestureOverlayView.addOnGesturePerformedListener(this)
        }
        //设置手势可以多笔绘画，默认只能一笔
        gestureOverlayView.gestureStrokeType = GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE
        //设置笔画的颜色
        gestureOverlayView.gestureColor = ContextCompat.getColor(this, R.color.black)
        //设置还没未能形成手势绘制是的颜色(红色)
        gestureOverlayView.uncertainGestureColor = ContextCompat.getColor(this, R.color.black)
        //设置手势粗细
        gestureOverlayView.gestureStrokeWidth = 10f
        /*手势绘制完成后淡出屏幕的时间间隔，即绘制完手指离开屏幕后相隔多长时间手势从屏幕上消失；
        * 可以理解为手势绘制完成手指离开屏幕后到调用onGesturePerformed的时间间隔
        * 默认值为420毫秒，这里设置为2秒
        */
        gestureOverlayView.fadeOffset = 100

        //绑定监听器
        gestureOverlayView.apply {
            addOnGesturePerformedListener(this@MainActivity)
            addOnGesturingListener(this@MainActivity)
        }
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val result = gestureLibrary.recognize(gesture)
        if (result.size > 0) {
            val str = result[0].name
            if (str == "c" || str == "r"){
                textView.text = "<"
            } else {
                textView.text = ">"
            }

        }
    }

    //手势开始
    override fun onGesturingStarted(overlay: GestureOverlayView?) {

    }

    //手势结束
    override fun onGesturingEnded(overlay: GestureOverlayView?) {

    }
}