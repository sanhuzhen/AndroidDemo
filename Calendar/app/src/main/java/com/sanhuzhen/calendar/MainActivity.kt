package com.sanhuzhen.calendar

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sheetDialog = CalendarBottomSheetDialog(this)
        findViewById<Button>(R.id.bt).setOnClickListener {
            sheetDialog.show()
        }
    }
}