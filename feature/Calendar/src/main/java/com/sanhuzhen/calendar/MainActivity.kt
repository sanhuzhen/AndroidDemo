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
        val bottomSheetView = layoutInflater.inflate(R.layout.activity_dialog_additem, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.behavior.peekHeight = 1500
        findViewById<Button>(R.id.bt).setOnClickListener {
            bottomSheetDialog.show()
        }
    }
}