package com.codeoxys.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<Button>(R.id.buttonDatePicker)

        btn.setOnClickListener(){view ->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker(view: View){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val day = myCalender.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"The year chosen is : $year month $month date $day",Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val tvseldate = findViewById<TextView>(R.id.tvSelectedDate)
                tvseldate.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time/6000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time/6000
                val diffInMinutes = currentDateInMinutes - selectedDateInMinutes

                val ageInMins = findViewById<TextView>(R.id.ageInMinutes)
                ageInMins.setText(diffInMinutes.toString())
            }
                    ,year
                    ,month
                    ,day)

            dpd.datePicker.setMaxDate(Date().time - 8600000)
            dpd.show()

        }
    }
}