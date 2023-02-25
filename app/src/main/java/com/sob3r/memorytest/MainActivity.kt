package com.sob3r.memorytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sob3r.memorytest.utils.MemTest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scrWidth = resources.displayMetrics.widthPixels
        val scrHeight = resources.displayMetrics.heightPixels

        val btn1: Button = findViewById(R.id.randBtn1)
        val btn2: Button = findViewById(R.id.randBtn2)
        val btn3: Button = findViewById(R.id.randBtn3)
        val btn4: Button = findViewById(R.id.randBtn4)
        val btn5: Button = findViewById(R.id.randBtn5)
        val butts = listOf(btn1, btn2, btn3, btn4, btn5)

        val memFunc = MemTest(this, scrWidth, scrHeight, butts)

        val generateBtn: Button = findViewById(R.id.genRand)
        generateBtn.setOnClickListener {
            memFunc.mStartTime = 4000
            memFunc.iteration = 0
            memFunc.startGame()
        }

    }
}