package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope

class MainActivity : AppCompatActivity() {

    var firsttime:Long = 25* 60 * 1000
    var timeleft = firsttime
    var timer:CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener{
            startetimer()
        }
        reset.setOnClickListener{
            canceltime()
        }
    }


    private fun startetimer() {
        if (timeleft == firsttime || timeresult.text == "00:00") {
            timer = object : CountDownTimer(firsttime, 1000) {
                override fun onTick(p0: Long) {
                    timeleft = p0
                    updattime()
                    progressBar.progress =timeleft.toDouble().div(firsttime.toDouble()).times(100).toInt()

                }

                override fun onFinish() {

                }
            }.start()
        }
    }


    private fun updattime(){
        var timeynmin = timeleft.div(1000).div(60)
        var timeinsec = timeleft.div(1000) % 60
        var formattime = String.format("%02d:%02d", timeynmin , timeinsec)
        timeresult.setText(formattime)
    }

    private fun canceltime() {
         timer?.cancel()
         timeresult.text = "25:00"
         timeleft = firsttime
         progressBar.progress = 100
    }
}