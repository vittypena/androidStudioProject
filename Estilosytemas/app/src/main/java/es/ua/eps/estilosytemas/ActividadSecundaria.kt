package es.ua.eps.estilosytemas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.ContentInfoCompat

class ActividadSecundaria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_secundaria)

        var boton = findViewById<Button>(R.id.button)

        var goActivityMain = Intent(this, MainActivity::class.java)
        goActivityMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        boton.setOnClickListener { startActivity(goActivityMain) }
    }
}