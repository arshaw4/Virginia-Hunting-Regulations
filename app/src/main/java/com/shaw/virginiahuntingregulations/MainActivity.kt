package com.shaw.virginiahuntingregulations

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar


class MainActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener{
private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val datePickerButton: Button = findViewById(R.id.datePickerButton)
        datePickerButton.text =
            returnDateStr(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(
                Calendar.DAY_OF_MONTH))
        datePickerButton.setOnClickListener{
            DatePickerDialog(this,this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(
                Calendar.DAY_OF_MONTH)).show()
        }
        findViewById<Button>(R.id.submitDateButton).setOnClickListener{
            val intent = Intent(this, SpeciesInput::class.java)
            intent.putExtra("date", calendar)
            startActivity(intent)
        }
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.e("Calendar", "$year -- $month -- $dayOfMonth")
        findViewById<Button>(R.id.datePickerButton).text = returnDateStr(year,month,dayOfMonth)
        calendar.set(year, month, dayOfMonth)
    }
    private fun returnDateStr(year: Int, month: Int, dayOfMonth: Int): String{
        return "${month+1}/$dayOfMonth/$year"
    }
}