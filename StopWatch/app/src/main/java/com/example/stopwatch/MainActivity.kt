package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1

    lateinit var fab : FloatingActionButton
    lateinit var resetFab : FloatingActionButton
    lateinit var secTextView : TextView
    lateinit var milliTextView : TextView
    lateinit var lapLayout : LinearLayout
    lateinit var labButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        resetFab = findViewById(R.id.resetFab)
        secTextView = findViewById(R.id.secTextView)
        milliTextView = findViewById(R.id.milliTextView)
        lapLayout = findViewById(R.id.lapLayout)
        labButton = findViewById(R.id.labButton)


        fab.setOnClickListener{
            isRunning = !isRunning

            if (isRunning){
                start()
            }else{
                pause()
            }
        }

        labButton.setOnClickListener{
            recordLapTime()
        }

        resetFab.setOnClickListener{
            reset()
        }
    }

    private fun pause(){
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel()
    }

    private fun start(){
        fab.setImageResource(R.drawable.ic_baseline_pause_24)

        timerTask = timer(period=10){
            time++
            val sec = time/100
            val milli = time % 100
            runOnUiThread{
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    // 랩 타임 표시 메소드
    private fun recordLapTime(){
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime/100}.${lapTime%100}"

        lapLayout.addView(textView, 0)
        lap++
    }

    // 타이머 초기화 메소드
    private fun reset(){
        timerTask?.cancel()

        // 모든 변수 초기화
        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        secTextView.text = "0"
        milliTextView.text ="00"

        // 모든 랩타임 제거
        lapLayout.removeAllViews()
        lap = 1
    }

}