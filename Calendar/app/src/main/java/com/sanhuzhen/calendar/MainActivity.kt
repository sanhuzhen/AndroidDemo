package com.sanhuzhen.calendar

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.calendar_bottom_sheet, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        findViewById<Button>(R.id.bt).setOnClickListener {
            bottomSheetDialog.show()
        }
    }
}