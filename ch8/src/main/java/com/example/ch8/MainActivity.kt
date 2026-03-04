package com.example.ch8

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8.PaymentActivity
import com.example.ch8.databinding.ActivityMainBinding
import android.Manifest



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 1. 전화 권한 런처
    private val callPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) makePhoneCall()
        else Toast.makeText(this, "전화 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
    }

    // 2. 결제 화면 결과 런처 (반드시 클릭 리스너 바깥에 독립적으로 선언)
    private val paymentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // 결제 성공 후 메인으로 돌아왔을 때 띄우는 메시지
            Toast.makeText(this, "결제하기 버튼을 눌렀습니다", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 지도보기 로직
        binding.btnMap.setOnClickListener {
            val address = binding.txtAddress.text.toString()
            val uri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps")
            }
            try {
                startActivity(mapIntent)
            } catch (e: Exception) {
                val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(address)}")
                startActivity(Intent(Intent.ACTION_VIEW, webUri))
            }
        }

        // 전화번호 클릭 로직
        binding.txtPhone.setOnClickListener {
            checkPermissionAndCall()
        }

        // 3. 예약하기 버튼 클릭 로직 (여기에 있어야 정상 작동합니다)
        binding.btnReserve.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            paymentLauncher.launch(intent)
        }
    }

    private fun checkPermissionAndCall() {
        val permission = Manifest.permission.CALL_PHONE
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            makePhoneCall()
        } else {
            callPermissionLauncher.launch(permission)
        }
    }

    private fun makePhoneCall() {
        val phoneNumber = binding.txtPhone.text.toString()
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }
}