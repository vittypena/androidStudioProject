package es.ua.eps.snackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var boton = findViewById<Button>(R.id.button)
        var texto = findViewById<EditText>(R.id.editTextTextPersonName)
        var textoDestino = findViewById<TextView>(R.id.textView3)
        var textoPrevio = ""
        boton.setOnClickListener{
            if(texto.text.isEmpty()) {
                Snackbar.make(it, "Escribe un texto", Snackbar.LENGTH_INDEFINITE).show()
            }else{
                textoPrevio = textoDestino.text.toString()
                textoDestino.append("${texto.text}\n")
                texto.text.clear()
                Snackbar.make(it,"Tarea añadida", Snackbar.LENGTH_INDEFINITE).setAction("Deshacer"){textoDestino.text=textoPrevio}.show()
            }
        }
    }
}