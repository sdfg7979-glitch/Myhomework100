package com.example.ch11

import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.JavascriptInterface
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // layout 폴더 안에 있는 xml 의 파일이름을 따르게 되며, 현재 xml 의 이름은 activity_main.xml 이고
        // 이에 맞는 bind 되는 객체 이름은 ActivityMainBinding 입니다. 뒤에 무조건 Binding 이 붙는다고 보시면 됩니다.
        var binding = MainActivity.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.addJavascriptInterface(WebAppInterface(), "Android")
        binding.webView.loadUrl("https:///android_asset/test.html")

    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun calculateSum(number: Int) {
            var sum: Int = 0
        for(i in 1..number){
            sum += i
        }
        runOnUiThread {
            binding.WebView.evaluateJavascript("javascript:displayResult($sum)", null) }
        }
    }

    companion object

}
