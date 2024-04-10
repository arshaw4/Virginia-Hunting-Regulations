package com.shaw.virginiahuntingregulations

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class SpeciesInput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_species_input)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val month = intent.getIntExtra("month", -1)
        val day = intent.getIntExtra("day", -1)
        val year = intent.getIntExtra("year", -1)
        findViewById<Button>(R.id.submitSpeciesCountButton).setOnClickListener{
            val intent = Intent(this, ResultScreen::class.java)
            intent.putExtra("month", month)
            intent.putExtra("day",day)
            intent.putExtra("year",year)
            intent.putExtra("tealValue", findViewById<EditText>(R.id.tealInputValue).text.toString().toInt())
            intent.putExtra("mergansersValue", findViewById<EditText>(R.id.mergansersInputValue).text.toString().toInt())
            intent.putExtra("mallardDrakeValue", findViewById<EditText>(R.id.mallardDrakeInputValue).text.toString().toInt())
            intent.putExtra("mallardHenValue", findViewById<EditText>(R.id.mallardHenInputValue).text.toString().toInt())
            intent.putExtra("woodDuckValue", findViewById<EditText>(R.id.woodDuckInputValue).text.toString().toInt())
            intent.putExtra("blackDuckValue", findViewById<EditText>(R.id.blackDuckInputValue).text.toString().toInt())
            intent.putExtra("scaupValue", findViewById<EditText>(R.id.scaupInputValue).text.toString().toInt())
            intent.putExtra("redheadValue", findViewById<EditText>(R.id.redheadInputValue).text.toString().toInt())
            intent.putExtra("canvasbackValue", findViewById<EditText>(R.id.canvasbackInputValue).text.toString().toInt())
            intent.putExtra("pintailValue", findViewById<EditText>(R.id.pintailInputValue).text.toString().toInt())
            intent.putExtra("mottledDuckValue", findViewById<EditText>(R.id.mottledDuckInputValue).text.toString().toInt())
            intent.putExtra("fulvousWhistlingValue", findViewById<EditText>(R.id.fulvousWhistlingInputValue).text.toString().toInt())
            intent.putExtra("cootValue", findViewById<EditText>(R.id.cootInputValue).text.toString().toInt())
            intent.putExtra("geeseValue", findViewById<EditText>(R.id.geeseInputValue).text.toString().toInt())
            intent.putExtra("scoterValue", findViewById<EditText>(R.id.scoterInputValue).text.toString().toInt())
            intent.putExtra("eiderDrakeValue", findViewById<EditText>(R.id.eiderDrakeInputValue).text.toString().toInt())
            intent.putExtra("eiderHenValue", findViewById<EditText>(R.id.eiderHenInputValue).text.toString().toInt())
            intent.putExtra("longTailValue", findViewById<EditText>(R.id.longTailInputValue).text.toString().toInt())
            intent.putExtra("huntZoneSwitchValue", findViewById<SwitchCompat>(R.id.huntZoneSwitch).isChecked)
            intent.putExtra("i95ZoneSwitchValue", findViewById<SwitchCompat>(R.id.i95ZoneSwitch).isChecked)
            startActivity(intent)
        }
    }
}