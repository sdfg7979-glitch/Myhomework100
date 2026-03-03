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
    // 토스트와 진동을 동시에 실행하는 공통 함수
    private fun notifyError(message: String) {
        // 1. 토스트 메시지 출력
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        // 2. 진동 실행
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(1000)
        }
    }
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

        binding.btnReserve.setOnClickListener {

            val name = binding.editName.text.toString().trim()


            if (name.isBlank()) {
                Toast.makeText(
                    this@Test7Activity,
                    "이름을 입력하세요.",
                    Toast.LENGTH_SHORT
                ).show()

                binding.editName.requestFocus()
                return@setOnClickListener


        }
        binding.btnDatePiker.setOnClickListener {

            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    binding.tvDate.text =
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
        binding.btnReserve.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val date = binding.tvDate.text.toString().trim()
            val time = binding.tvTime.text.toString().trim()


            if (name.isBlank()) {
                notifyError("이름을 입력하세요.")
                binding.editName.requestFocus()
                return@setOnClickListener
            }


            if (date.isEmpty() || date.contains("date", ignoreCase = true) || date.contains("날짜")) {
                notifyError("날짜를 선택해 주세요.")
                return@setOnClickListener
            }


            if (time.isEmpty() || time.contains("time", ignoreCase = true) || time.contains("시간")) {
                notifyError("시간을 선택해 주세요.")
                return@setOnClickListener
            }


            Toast.makeText(this, "${name}님, ${date} ,${time}에 예약이 완료되었습니다!", Toast.LENGTH_SHORT).show()
        }
}  }}