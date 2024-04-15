package com.shaw.virginiahuntingregulations

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
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
        val month = intent.getIntExtra("month", -1)
        val day = intent.getIntExtra("day", -1)
        val year = intent.getIntExtra("year", -1)
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val tealValue = intent.getIntExtra("tealValue", -1)
        val mergansersValue = intent.getIntExtra("mergansersValue", -1)
        val mallardDrakeValue = intent.getIntExtra("mallardDrakeValue", -1)
        val mallardHenValue = intent.getIntExtra("mallardHenValue", -1)
        val woodDuckValue = intent.getIntExtra("woodDuckValue", -1)
        val blackDuckValue = intent.getIntExtra("blackDuckValue", -1)
        val scaupValue = intent.getIntExtra("scaupValue", -1)
        val redheadValue = intent.getIntExtra("redheadValue", -1)
        val canvasbackValue = intent.getIntExtra("canvasbackValue", -1)
        val pintailValue = intent.getIntExtra("pintailValue", -1)
        val mottledDuckValue = intent.getIntExtra("mottledDuckValue", -1)
        val fulvousWhistlingValue = intent.getIntExtra("fulvousWhistlingValue", -1)
        val cootValue = intent.getIntExtra("cootValue", -1)
        val geeseValue = intent.getIntExtra("geeseValue", -1)
        val scoterValue = intent.getIntExtra("scoterValue", -1)
        val eiderDrakeValue = intent.getIntExtra("eiderDrakeValue", -1)
        val eiderHenValue = intent.getIntExtra("eiderHenValue", -1)
        val longTailValue = intent.getIntExtra("longTailValue", -1)
        val huntZoneSwitchValue = intent.getBooleanExtra("huntZoneSwitchValue", false)
        val i95ZoneSwitchValue = intent.getBooleanExtra("i95ZoneSwitchValue", false)
        findViewById<TextView>(R.id.tealResults).text =
            tealResults(calendar, tealValue, i95ZoneSwitchValue)
        findViewById<TextView>(R.id.mergansersResults).text =
            mergansersResults(calendar, mergansersValue)
    }

    private fun dateInRange(
        startRange: Calendar,
        endRange: Calendar,
        checkDate: Calendar
    ): Boolean {
        return checkDate.after(startRange) && checkDate.before(endRange)
    }

    private fun tealResults(calendar: Calendar, tealInput: Int, i95SwitchResult: Boolean): String {
        val seasonStart = Calendar.getInstance()
        val seasonEnd = Calendar.getInstance()

        if (i95SwitchResult) {
            //if its true it should be west of i95
            //September 21th of current year
            seasonStart.set(calendar.get(Calendar.YEAR), 8, 21, 0, 0, 0)
            println(seasonStart.toString())
            //September 30th of current year
            seasonEnd.set(calendar.get(Calendar.YEAR), 8, 30, 23, 59, 59)
            return if (dateInRange(seasonStart, seasonEnd, calendar)) {
                if (tealInput < 6) {
                    "You can bag " + (6 - tealInput) + " more Teal today"
                } else if (tealInput == 6) {
                    "You have reached the daily Teal bag limit"
                } else {
                    "You have surpassed the daily Teal bag limit by " + (tealInput - 6)
                }
            } else {
                "Teal are not in season for your area"
            }
        } else {
            //if its false its east of i95
            //September 17th of current year
            seasonStart.set(calendar.get(Calendar.YEAR), 8, 17, 0, 0, 0)
            //September 30th of current year
            seasonEnd.set(calendar.get(Calendar.YEAR), 8, 30, 23, 59, 59)
            return if (dateInRange(seasonStart, seasonEnd, calendar)) {
                if (tealInput < 6) {
                    "You can bag " + (6 - tealInput) + " more Teal today"
                } else if (tealInput == 6) {
                    "You have reached the daily Teal bag limit"
                } else {
                    "You have surpassed the daily Teal bag limit by " + (tealInput - 6)
                }
            } else {
                "Teal are not in season for your area"
            }
        }
    }

    private fun mergansersResults(calendar: Calendar, mergansersInput: Int): String {
        var inOneOfRanges = false
        val seasonStart = Calendar.getInstance()
        val seasonEnd = Calendar.getInstance()
//        October 6-9
        seasonStart.set(calendar.get(Calendar.YEAR), 9, 6, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 9, 9, 23, 59, 59)
        inOneOfRanges = dateInRange(seasonStart, seasonEnd, calendar)
//        November 15-26
        seasonStart.set(calendar.get(Calendar.YEAR), 10, 15, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 10, 26, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
//        December 19-January 31 we have to break up into december 19-31 and jan 1-31
        seasonStart.set(calendar.get(Calendar.YEAR), 11, 19, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 11, 31, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        seasonStart.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 0, 31, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        return if (inOneOfRanges) {
            if (mergansersInput < 5) {
                "You can bag " + (5 - mergansersInput) + " more Mergansers today"
            } else if (mergansersInput == 5) {
                "You have reached the daily Mergansers bag limit"
            } else {
                "You have surpassed the daily Mergansers bag limit by " + (mergansersInput - 5)
            }
        } else {
            "Mergansers are not in season"
        }
    }

    private fun duckResults(
        calendar: Calendar,
        mallardDrakeInput: Int,
        mallardHenInput: Int,
        woodDuckInput: Int,
        blackDuckInput: Int,
        scaupInput: Int,
        redheadInput: Int,
        canvasbackInput: Int,
        pintailInput: Int,
        mottledDuckInput: Int,
        fulvousInput: Int,
        cootInput: Int,
        geeseInput: Int,
        scoterInput: Int,
        eiderDrakeInput: Int,
        eiderHenInput: Int,
        longTailInput: Int
    ): String {
        return " "
    }
}