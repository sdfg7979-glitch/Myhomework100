package com.example.ch8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 결제하기 버튼 클릭 시
        binding.btnConfirm.setOnClickListener {
            setResult(RESULT_OK) // 메인 화면으로 성공 신호 보냄
            finish() // 현재 화면 닫기
        }

        // 취소하기 버튼 클릭 시
        binding.btnCancel.setOnClickListener {
            finish() // 그냥 닫기
        }
    }}