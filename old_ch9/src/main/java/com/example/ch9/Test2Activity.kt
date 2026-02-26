package com.example.ch9


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch9.databinding.ActivityTest2Binding

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //intent.. 되돌아 왔을때 사후처리..
        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){//callback.. 되돌아 왔을때 자동 실행..
            //결과 데이터 획득..
            val intent = it.data
            binding.dataView.text = intent?.getStringExtra("result")
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, SomeActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 100)
            launcher.launch(intent)
        }
    }
}