package es.ua.eps.ejemplohilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ThreadEj : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvCrono = findViewById<TextView>(R.id.tvCrono)

        Thread{
            var t = 100
                do{
                    runOnUiThread {
                        tvCrono.text = "Contador: $t"
                    }
                    Thread.sleep(1000)
                    t--
                } while(t > 0)
                runOnUiThread {
                    tvCrono.text = "Contador terminado"
                }
        }.start()

    }
}
