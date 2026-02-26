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
