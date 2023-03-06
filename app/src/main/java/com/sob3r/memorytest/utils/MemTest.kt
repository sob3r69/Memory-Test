package com.sob3r.memorytest.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sob3r.memorytest.R

class MemTest(
    private val activity: Activity,
) {
    private val settingsData = SettingsData()

    private var scrWidth: Int = 0
    private var scrHeight: Int = 0

    lateinit var mButts: List<Button>
    private var mBtnSize: Int = settingsData.button1xSize

    private var mDifficulty = settingsData.defDifficulty
    var mStartTime: Long = 4000
    var mIteration = 0

    private val mainScreen = this.activity
    private val currScore: TextView = mainScreen.findViewById(R.id.currVal)

    @SuppressLint("SetTextI18n")
    fun startGame(){
        currScore.text = "Current score: $mIteration"

        disableButts()
        revealButtsVal()
        setButtsPos()

        object: CountDownTimer(mStartTime, 100) {
            override fun onTick(millisUntilFinished: Long) { println("<<< current diff: $mDifficulty") }

            override fun onFinish() {
                hideButtsVal()
                enableButts()
            }
        }.start()
    }

    fun setScreenSize(width: Int, height: Int){
        scrWidth = width - mBtnSize
        scrHeight = height - mBtnSize
    }

    fun setButtons(butts: List<Button>){
        mButts = butts
    }

    fun setButtonSize(size: Int){
        mBtnSize = size
    }

    fun setDifficulty(difficulty: Int){
        mDifficulty = difficulty
    }

    private fun enableButts(){
        var x = 1
        for(btn in mButts) btn.setOnClickListener {

            println("<<< Button value: ${btn.text}, current value: $x")

            if (btn.text == x.toString()) {
                x += 1
                revealBtnVal(btn)
                btn.isClickable = false
                if(x == 6){
                    nextIteration()
                }
            } else{
                stopGame()
            }
        }
    }

    private fun nextIteration(){
        mIteration += 1
        mStartTime -= mStartTime/100 * mDifficulty
        println("<<< Iteration changed to $mIteration, start time = $mStartTime")
        startGame()
    }

    private fun stopGame(){
        disableButts()
        revealButtsVal()
        Toast.makeText(activity, "Fail", Toast.LENGTH_SHORT).show()
        println("<<< Failure")
    }

    private fun disableButts(){
        for (btn in mButts) {
            btn.layoutParams = ViewGroup.LayoutParams(mBtnSize, mBtnSize)
            btn.isClickable = false
        }
        println("<<< Butts disabled")
    }

    private fun hideButtsVal(){
        for(btn in mButts) btn.textSize = 0f
    }

    private fun revealButtsVal(){
        for(btn in mButts) {
            btn.textSize = 20f
            btn.visibility = View.VISIBLE
        }
    }

    private fun revealBtnVal(btn: Button){
        btn.textSize = 20f
    }

    private fun setRandPos(button: Button){
        val xCord = (0..scrWidth - 200).random()
        val yCord = (100..scrHeight - 200).random()

        button.animate().apply {
            duration = 0
            translationX(xCord.toFloat())
            translationY(yCord.toFloat())
        }
    }

    private fun setButtsPos(){
        for(btn in mButts) {
            setRandPos(btn)
        }
    }

}