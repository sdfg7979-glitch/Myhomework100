package com.example.homework1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// 🛠️ 기계의 '메인 제어 장치(Main Controller)'입니다. 
// 모든 명령은 이 장치에서 시작됩니다.
class MainActivity : AppCompatActivity() {

    // 🛠️ 기계의 '시동 스위치(Power-on)'입니다. 
    // 전원이 들어오면 가장 먼저 실행되는 초기 설정 단계입니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 🛠️ '풀 스크린 모드(Edge-to-Edge)'를 활성화합니다. 
        // 계기판 화면을 베젤 끝까지 꽉 차게 확장하는 설정입니다.
        enableEdgeToEdge()
        
        // 🛠️ '대시보드 조립(Mounting Layout)'입니다.
        // 준비된 외부 패널(activity_main.xml)을 제어 장치에 연결합니다.
        setContentView(R.layout.activity_main)
        
        // 🛠️ '간격 조정 작업(Padding Adjustment)'입니다.
        // 상단 상태 표시줄이나 하단 버튼 같은 기계의 '외부 프레임'에 계기판 내용이 가려지지 않도록
        // 유격(Padding)을 자동으로 맞춰주는 미세 조정 과정입니다.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
