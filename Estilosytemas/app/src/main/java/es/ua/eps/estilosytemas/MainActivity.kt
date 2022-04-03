package es.ua.eps.estilosytemas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var boton = findViewById<Button>(R.id.button)
        var goActivitySecundary = Intent(this, ActividadSecundaria::class.java)

        boton.setOnClickListener { startActivity(goActivitySecundary) }
    }
}