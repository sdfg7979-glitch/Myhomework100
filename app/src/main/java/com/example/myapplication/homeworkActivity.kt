import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.stopwatch.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var time = 0  // 0.01초 단위
    private var isRunning = false

    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                time++
                val min = time / 6000
                val sec = (time % 6000) / 100
                val ms = time % 100

                binding.root.t =
                    String.format("%02d:%02d:%02d", min, sec, ms)

                handler.postDelayed(this, 10)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val onClickListener = binding.root.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                handler.post(runnable)
            }
        }


        binding.root.setOnClickListener {
            isRunning = false
        }

        binding.root.setOnClickListener {
            isRunning = false
            time = 0
            "00:00:00".also { binding.root.text = it }
        }
    }
}
