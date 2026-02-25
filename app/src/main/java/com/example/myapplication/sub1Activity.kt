package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8.databinding.ActivitySub1Binding

private val databinding: Any

class Sub1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivitySub1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //테마 설정을 통해 ActionBar 가 나오지 않게 하고..
        //개발자가 직접 준비하는 Toolbar 로 ActionBar 를 대체하겠다..
        //ActionBar 내용이 개발자 뷰인 Toolbar 에 적용되어야 한다.. 어느 뷰인지는 알려줘야 한다..
        setSupportActionBar(binding.toolbar)

        //up 버튼 출력..
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
}