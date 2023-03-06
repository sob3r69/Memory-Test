package com.sob3r.memorytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.sob3r.memorytest.utils.MemTest
import com.sob3r.memorytest.utils.SettingsData

class MainActivity() : AppCompatActivity(), Settings.SettingsDialogListener {

    private val settingsData = SettingsData()

    private var btnSize: Int = settingsData.button1xSize
    private var difficulty: Int = settingsData.defDifficulty

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

        val memFunc = MemTest(this)
        val startBtn: Button = findViewById(R.id.startBtn)
        startBtn.setOnClickListener {
            memFunc.setScreenSize(scrWidth, scrHeight)
            memFunc.setButtons(butts)
            memFunc.setButtonSize(btnSize)
            memFunc.setDifficulty(difficulty)

            memFunc.mStartTime = 4000
            memFunc.mIteration = 0
            memFunc.startGame()
        }

        val settingsButton: Button = findViewById(R.id.btnSettings)
        settingsButton.setOnClickListener {
            val settingsWindow = Settings()
            settingsWindow.show(supportFragmentManager, "tag")
        }
    }

    override fun btnSize1x() {
        btnSize = settingsData.button1xSize
    }
    override fun btnSize15x() {
        btnSize = settingsData.button15xSize
        println("<<< Changed button size")
    }

    override fun defDifficulty() {
        difficulty = settingsData.defDifficulty
    }

    override fun medDifficulty() {
        difficulty = settingsData.medDifficulty
    }

    override fun hardDifficulty() {
        difficulty = settingsData.hardDifficulty
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {

    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("Not yet implemented")
    }
}