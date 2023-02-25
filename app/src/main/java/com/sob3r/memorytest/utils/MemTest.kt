package com.sob3r.memorytest.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sob3r.memorytest.R

class MemTest(
    private val activity: Activity,
    private val width: Int,
    private val height: Int,
    private val butts: List<Button>
) {
    var mStartTime: Long = 4000
    var iteration = 0

    private val mainScreen = this.activity
    private val currScore: TextView = mainScreen.findViewById(R.id.currVal)

    @SuppressLint("SetTextI18n")
    fun startGame(){
        currScore.text = "Current score: $iteration"

        disableButts()
        revealButtsVal()
        setButtsPos()

        object: CountDownTimer(mStartTime, 100) {
            override fun onTick(millisUntilFinished: Long) {
                println(millisUntilFinished)
            }

            override fun onFinish() {
                hideButtsVal()
                enableButts()
            }
        }.start()
    }

    private fun enableButts(){
        var x = 1
        for(btn in butts) btn.setOnClickListener {

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
        iteration += 1
        mStartTime -= mStartTime/100 * 10
        println("<<< Iteration changed to $iteration, start time = $mStartTime")
        startGame()
    }

    private fun stopGame(){
        disableButts()
        revealButtsVal()
        Toast.makeText(activity, "Fail", Toast.LENGTH_SHORT).show()
        println("<<< Failure")
    }

    private fun disableButts(){
        for (btn in butts) btn.isClickable = false
        println("<<< Butts disabled")
    }

    private fun hideButtsVal(){
        for(btn in butts) btn.textSize = 0f
    }

    private fun revealButtsVal(){
        for(btn in butts) {
            btn.textSize = 20f
            btn.visibility = View.VISIBLE
        }
    }

    private fun revealBtnVal(btn: Button){
        btn.textSize = 20f
    }

    private fun setRandPos(button: Button){
        val xCord = (0..width - 200).random()
        val yCord = (100..height - 200).random()

        button.animate().apply {
            duration = 0
            translationX(xCord.toFloat())
            translationY(yCord.toFloat())
        }
    }

    private fun setButtsPos(){
        for(btn in butts) {
            setRandPos(btn)
        }
    }

}