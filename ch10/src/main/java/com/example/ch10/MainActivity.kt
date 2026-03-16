package com.example.ch10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
//쓰잘데기 없는 주석입니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fragment 실행
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, homeworkFragment())
            .commit()
    }
}