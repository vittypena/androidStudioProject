package com.example.ciclovidaactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LOGCICLO", "Entramos en método onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("LOGCICLO", "Entramos en método onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LOGCICLO", "Entramos en método onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LOGCICLO", "Entramos en método onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LOGCICLO", "Entramos en método onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LOGCICLO", "Entramos en método onDestroy")
    }

    fun clickOnHobby(view: android.view.View) {
        val checkBox: CheckBox = view as CheckBox
        val checked: Boolean = checkBox.isChecked

        when(view.id){
            R.id.checkBox -> {
                if(checked){
                    Toast.makeText(this,"Se ha marcado Deporte", Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(this,"Se ha desmarcado Deporte", Toast.LENGTH_LONG).show()
                }
            }
            R.id.checkBox2 -> {
                if(checked){
                    Toast.makeText(this,"Se ha marcado Videojuegos", Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(this,"Se ha desmarcado Videojuegos", Toast.LENGTH_LONG).show()
                }
            }
        }
        //View es porque el evento lo hemos creado desde el checkbox de el activity
        //When es el equivalente a switch
        //Toast es para mostrar por pantalla y el atributo LENGTH_LONG.show() es para que se muestre el maximo tiempo posible en pantalla
    }

    fun sexoSeleccionado(view: android.view.View) {
        val radioButtonSeleccionado = view as RadioButton
        //Cambiamos lo que esta seleccionado a tipo RadioButton para poder hacer lo siguiente
        when(view.id){
            R.id.radioButton -> {
                Toast.makeText(this, "Se ha seleccionado mujer", Toast.LENGTH_LONG).show()
            }
            R.id.radioButton2 -> {
                Toast.makeText(this, "Se ha seleccionado hombre", Toast.LENGTH_LONG).show()
            }
            R.id.radioButton3 -> {
                Toast.makeText(this, "Se ha seleccionado indefinido", Toast.LENGTH_LONG).show()
            }
        }
    }
}