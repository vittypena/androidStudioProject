package es.wow.vp.ejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import es.wow.vp.ejemplo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layout = layoutInflater.inflate(R.layout.toast_layout, null)
        layout!!.findViewById<TextView>(R.id.txtMensaje).text = "Toast Personalizado ^^"

        binding.button.setOnClickListener {
            val toast = Toast(this)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layout//Deprecated
            toast.setGravity(Gravity.CENTER or Gravity.LEFT, 0, 0)//Con or se combina center y izquierda
            toast.show()
            binding.textView.setText(R.string.boton_pulsado)
        }
    }
}