package es.ua.eps.componentesviewpersonalizados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var botonGrafica = findViewById<Button>(R.id.button2)
        var goActivityGrafica = Intent(this, GraficaActivity::class.java)

        botonGrafica.setOnClickListener { startActivity(goActivityGrafica) }

    }
}