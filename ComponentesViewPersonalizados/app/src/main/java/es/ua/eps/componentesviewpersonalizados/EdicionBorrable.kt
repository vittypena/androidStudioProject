package es.ua.eps.componentesviewpersonalizados

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class EdicionBorrable @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    var editText : EditText? = null
    var button : Button? = null

    init {
        inicializar()
    }

    private fun inicializar() {
        // Creamos la interfaz a partir del layout
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        li.inflate(R.layout.edicion_borrable, this, true)

        // Obtenemos las referencias a las vistas hijas
        // y asignamos el comportamiento
        editText = findViewById(R.id.editText) as EditText
        button = findViewById(R.id.button) as Button

        button!!.setOnClickListener { editText!!.text.clear() }
    }
}