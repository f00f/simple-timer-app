package de.f00f.simpletimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    private var txtMin: TextView? = null
    private var txtSec: TextView? = null
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMin = findViewById(R.id.txtMin)
        txtSec = findViewById(R.id.txtSec)
    }

    fun startTimer(view: View) {
        if (view !is AppCompatButton) {
            return
        }

        countDownTimer?.cancel()
        val tag: Any = view.tag
        val numSecs: Long = when (tag) {
            is Int -> tag.toLong()
            is Long -> tag
            is String -> tag.toLong()
            else -> return
        }
        val millisInFuture = numSecs * 1_000

        // todo: Switch layout to timer view
        countDownTimer = object: CountDownTimer(millisInFuture, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimer(millisUntilFinished)
            }
            override fun onFinish() {
                countDownTimer = null
                // Switch Layout back to buttons
            }
        }
        updateTimer(millisInFuture)
        countDownTimer?.start()
    }

    fun updateTimer(millisUntilFinished: Long) {
        var secsUntilFinished: Long = millisUntilFinished / 1_000
        val minutesUntilFinished: Long = secsUntilFinished / 60
        secsUntilFinished %= 60
        txtMin!!.text = minutesUntilFinished.toString().padStart(2, '0')
        txtSec!!.text = secsUntilFinished.toString().padStart(2, '0')
    }
}
