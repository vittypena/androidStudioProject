package es.ua.eps.dialogoseleccion

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var color : Int? = null
        var tamanio : Int? = null
        var buttonColor = findViewById<Button>(R.id.button)
        var buttonTamanio = findViewById<Button>(R.id.button2)
        var parrafo1 = findViewById<TextView>(R.id.textView)
        var parrafo2 = findViewById<TextView>(R.id.textView2)

        val colores = arrayOf("Blanco y Negro", "Negro y Blanco", "Negro y Verde")
        val tamanios = arrayOf("Pequeño", "Normal", "Grande")
//TODO poner un layout personalizado al AlertDialog
        buttonColor.setOnClickListener {
            AlertDialog.Builder(MainActivity@this!!).setTitle("Selecciona los colores")
                .setSingleChoiceItems(colores, -1){dialog, which ->
                    color = which
                }.setPositiveButton(R.string.aceptar) { dialog, id ->
                    if(color==0){
                        parrafo1.setBackgroundColor(Color.WHITE)
                        parrafo1.setTextColor(Color.BLACK)
                        parrafo2.setBackgroundColor(Color.WHITE)
                        parrafo2.setTextColor(Color.BLACK)
                    }else if (color == 1){
                        parrafo1.setBackgroundColor(Color.BLACK)
                        parrafo1.setTextColor(Color.WHITE)
                        parrafo2.setBackgroundColor(Color.BLACK)
                        parrafo2.setTextColor(Color.WHITE)
                    }else if(color==2){
                        parrafo1.setBackgroundColor(Color.BLACK)
                        parrafo1.setTextColor(Color.GREEN)
                        parrafo2.setBackgroundColor(Color.BLACK)
                        parrafo2.setTextColor(Color.GREEN)
                    }
                }.setNegativeButton(R.string.cancelar,null)
                .show()
        }

        buttonTamanio.setOnClickListener {
            AlertDialog.Builder(this!!).setTitle("Selecciona el tamaño")
                .setSingleChoiceItems(tamanios, -1) {dialog, which ->
                    tamanio = which
                }.setPositiveButton(R.string.aceptar) {dialog, id ->
                    if(tamanio==0){
                        parrafo1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8F)
                        parrafo2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8F)
                    }else if(tamanio==1){
                        parrafo1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                        parrafo2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                    }else if(tamanio==2){
                        parrafo1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                        parrafo2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                    }
                }.setNegativeButton(R.string.cancelar,null)
                .show()
        }

    }
}