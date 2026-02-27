package com.example.ch7

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch7.databinding.ActivityTest7Binding
import java.util.Calendar
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.content.Context
import android.os.VibratorManager
class Test7Activity : AppCompatActivity() {

    lateinit var binding: ActivityTest7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnTimePicker.setOnClickListener {

            val name = binding.editName.text.toString().trim()


            if (name.isBlank()) {
                Toast.makeText(
                    this@Test7Activity,
                    "이름을 입력하세요.",
                    Toast.LENGTH_SHORT
                ).show()

                binding.editName.requestFocus()
                return@setOnClickListener
                Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
                    val birator=getSystemService(Context.VIBRATOR_SERVICE)as Vibrator
                    VIBRATOR_SERVICE?.vibrate(
                    VibrationEffect.createOneShot(
                        1000,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )

            }

        }
            binding.tvDatePiker.setOnClickListener {

                val calendar = Calendar.getInstance()

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePicker = DatePickerDialog(
                    this,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        binding.tvDatePiker.text =
                            "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    },
                    year,
                    month,
                    day
                )
                binding.tvDate.text = datePicker.toString()
                datePicker.show()

            }


            binding.btnTimePicker.setOnClickListener {

                val calendar = Calendar.getInstance()

                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, selectedHour, selectedMinute ->

                        val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                        binding.btnTimePicker.text = time
                        binding.tvTime.text = time
                    },
                    hour,
                    minute,
                    true
                )

                timePickerDialog.show()
            }
        }
    }