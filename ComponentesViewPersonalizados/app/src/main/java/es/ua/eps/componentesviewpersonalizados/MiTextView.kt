package es.ua.eps.componentesviewpersonalizados

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import java.util.Random

class MiTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {
    private var nRandom = Random()
    //var num = nRandom.nextInt(50)+1 //De 1 a 49, si quitas el 1 del 0 al 49
    private var mListCitas : Array<String> = resources.getStringArray(R.array.citas)

    init {
        cambiarCita()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        cambiarCita()
        return super.onTouchEvent(event)
    }

    private fun cambiarCita() {
        val num = nRandom.nextInt(mListCitas.size)
        text = mListCitas[num]
    }

}