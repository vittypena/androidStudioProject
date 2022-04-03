package es.ua.eps.ejemplohilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CorrutinasEj : AppCompatActivity() {
    private lateinit var tvCrono: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        tvCrono = findViewById<TextView>(R.id.tvCrono)

        GlobalScope.launch(Dispatchers.IO){
            var t = 100
            do{
                launch(Dispatchers.Main){
                    tvCrono.text = "Contador: $t"
                }
                Thread.sleep(1000)
                t--
            } while(t > 0)
                launch(Dispatchers.Main) {
                    tvCrono.text = "Contador terminado"
                }
        }
    }
}