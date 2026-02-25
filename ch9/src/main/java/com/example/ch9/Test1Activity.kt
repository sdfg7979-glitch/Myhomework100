package com.example.ch9


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch9.Sub1Activity
import com.example.ch9.databinding.ActivityTest1Binding



class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button1.setOnClickListener {
            val intent = Intent(this, Sub1Activity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent()
            intent.action = "ACTION_SUB2"
            //android.intent.category.DEFAULT 인 경우에는 주지 않아도 된다..
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            val intent = Intent()
            intent.action = "ACTION_SUB3"
            intent.addCategory("com.example.ch9.CATEGORY_SUB3")
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            val intent = Intent("ACTION_SUB4", Uri.parse("http://www.google.com"))
            startActivity(intent)
        }
    }
}