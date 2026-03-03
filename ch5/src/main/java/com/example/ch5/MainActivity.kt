package com.example.ch5

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. binding을 클래스 전체에서 사용할 수 있도록 늦은 초기화 선언
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // R.id.main 대신 binding.root를 사용하는 것이 안전합니다.
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.webView.settings.javaScriptEnabled = true
        // 2. WebAppInterface 클래스를 인스턴스화해서 전달
        binding.webView.addJavascriptInterface(WebAppInterface(), "Android")
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("file:///android_asset/test.html")
    }

    // 3. 내부 클래스(inner class) 정의 수정
    inner class WebAppInterface {
        @JavascriptInterface
        fun calculateSum(number: Int) {
            var sum: Int = 0
            for (i in 1..number) {
                sum += i
            }

            // UI 작업(evaluateJavascript)은 반드시 메인 스레드에서 실행
            runOnUiThread {
                binding.webView.evaluateJavascript("javascript:displayResult($sum)", null)
            }
        }
    }
}

