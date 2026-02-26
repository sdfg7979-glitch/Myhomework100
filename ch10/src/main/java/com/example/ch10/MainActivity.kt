package com.example.ch10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch10.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mainDataList = mutableListOf<MainData>()

        for (i in 1..10) {
            mainDataList.add(MainData("홍길동$i", "안녕하세요 $i", "오후 1월 ${i}일"))
        }

        binding.main.layoutManager = LinearLayoutManager(this)
        binding.main.adapter = MainAdapter(mainDataList)
        binding.main.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }
}