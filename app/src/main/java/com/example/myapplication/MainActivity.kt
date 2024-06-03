package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var touchInfo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        touchInfo = findViewById(R.id.touch_info)
        val touchView = findViewById<View>(R.id.touch_view)
        touchView.setOnTouchListener { _, event ->
            handleTouchEvent(event)
            true
        }

    }

    private fun handleTouchEvent(event: MotionEvent) {
        val pointerCount = event.pointerCount
        val stringBuilder = StringBuilder()
        stringBuilder.append("Pointer count: $pointerCount\n")
        for (i in 0 until pointerCount) {
            val pointerId = event.getPointerId(i)
            val x = event.getX(i)
            val y = event.getY(i)
            stringBuilder.append("Pointer ID: $pointerId, X: $x, Y: $y\n")
        }
        touchInfo.text = stringBuilder.toString()

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                // First touch
                stringBuilder.append("Action: DOWN\n")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                // Additional touch
                stringBuilder.append("Action: POINTER DOWN\n")
            }
            MotionEvent.ACTION_MOVE -> {
                // Touch move
                stringBuilder.append("Action: MOVE\n")
            }
            MotionEvent.ACTION_UP -> {
                // Last touch released
                stringBuilder.append("Action: UP\n")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                // One of the additional touches released
                stringBuilder.append("Action: POINTER UP\n")
            }
            MotionEvent.ACTION_CANCEL -> {
                // Touch event cancelled
                stringBuilder.append("Action: CANCEL\n")
            }
        }
        touchInfo.text = stringBuilder.toString()
    }
}