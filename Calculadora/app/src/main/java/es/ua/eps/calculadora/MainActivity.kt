package es.ua.eps.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val etSumando1 = findViewById<EditText>(R.id.etSumando1)
        val etSumando2 = findViewById<EditText>(R.id.etSumando2)
        val btCalcular = findViewById<Button>(R.id.btCalcular)

        btCalcular.setOnClickListener {
            val sumando1 = etSumando1.text.toString().toFloat()
            val sumando2 = etSumando2.text.toString().toInt()
            val resultado = sumando1 + sumando2
            tvResult.text = "" + resultado
        }
    }
}