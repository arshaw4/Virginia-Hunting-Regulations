package com.shaw.virginiahuntingregulations

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class ResultScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val calendar = intent.getStringExtra("date")
        val tealValue = intent.getIntExtra("tealValue", -1)
        val mergansersValue = intent.getIntExtra("mergansersValue",-1)


        val huntZoneSwitchValue = intent.getBooleanExtra("huntZoneSwitchValue", false)
        val i95ZoneSwitchValue = intent.getBooleanExtra("i95ZoneSwitchValue", false)
        //tealResult.text = tealResult(calendar,findViewById<EditText>(R.id.tealInputValue).text)
    }
    private fun dateInRange(startRange: Calendar, endRange: Calendar, checkDate: Calendar): Boolean{
        return checkDate.after(startRange) && checkDate.before(endRange)
    }
    private fun tealResults(calendar: Calendar, tealInput: String, i95SwitchResult: Boolean){

    }
}