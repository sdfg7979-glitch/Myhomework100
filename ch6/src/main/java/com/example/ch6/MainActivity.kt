package com.example.ch6

import android.os.Bundle
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // binding 변수의 타입이 MainActivity로 되어있습니다.
    // 이 변수는 레이아웃 파일의 뷰들을 담는 역할을 하므로,
    // ActivityMainBinding 타입으로 선언되어야 합니다.
    private lateinit var binding: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding 변수를 초기화하는 방식이 올바르지 않습니다.
        // 1. 위에서 lateinit으로 선언한 변수를 다시 var로 선언할 수 없습니다.
        // 2. MainActivity.inflate가 아니라, ActivityMainBinding.inflate(layoutInflater)를 사용해야 합니다.
        var binding = MainActivity.inflate(layoutInflater)
        setContentView(binding.root)

        // 웹뷰의 자바스크립트 설정을 활성화하고, 코틀린과 연결할 인터페이스를 추가하는 부분입니다.
        // 이 코드가 정상적으로 동작하려면, 위의 binding 변수가 올바르게 선언되고 초기화되어야 합니다.
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.addJavascriptInterface(WebAppInterface(), "Android")

        // 웹뷰에 로드할 HTML 파일의 경로가 잘못되었습니다.
        // 앱 내부에 저장된 로컬 파일을 불러오려면 "https://"가 아닌 "file:///android_asset/test.html"을 사용해야 합니다.
        // 그리고 test.html 파일은 main/assets 폴더 안에 위치해야 합니다.
        binding.webView.loadUrl("https:///android_asset/test.html")

    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun calculateSum(number: Int) {
            var sum: Int = 0
            for(i in 1..number){
                sum += i
            }
            // 계산된 결과를 다시 웹뷰의 자바스크립트 함수로 전달하는 부분입니다.
            // binding.WebView에서 'WebView'의 대소문자가 잘못 사용되었습니다.
            // 코틀린에서 변수 이름은 대소문자를 정확하게 구분해야 합니다. (xml에 정의된 id는 webView)
            runOnUiThread {
                binding.WebView.evaluateJavascript("javascript:displayResult($sum)", null) }
        }
    }

    // 현재 companion object 블록은 비어있고 아무 역할도 하지 않습니다.
    // 불필요한 코드는 삭제하여 코드를 간결하게 유지하는 것이 좋습니다.
    companion object

}
