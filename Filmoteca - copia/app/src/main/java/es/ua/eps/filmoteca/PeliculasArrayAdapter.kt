package es.ua.eps.filmoteca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

//Sobrescribimos el constructor, indicandole de que tipo de objeto va a ser, en este caso Film
class PeliculasArrayAdapter(context: Context?, resource: Int, objects: MutableList<Film>?) :
    ArrayAdapter<Film>(context!!, resource, objects!!) {

    //GetView se encarga de suministrar la vista para cada item, asi que lo sobreescribimos
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View = convertView?: LayoutInflater.from(this.context).inflate(R.layout.item_listview, parent, false)

        //creamos las variables que enlazan con el layout
        val tvTitulo = view.findViewById(R.id.titulo) as TextView
        val tvDirector = view.findViewById(R.id.director) as TextView
        val ivIcono = view.findViewById(R.id.icono) as ImageView

        getItem(position)?.let {//?. solo llama al metodo si el objeto no es null y let permmite hacer referencia al objeto devuelto mediante it
            //it de iterator, luego accedemos
            tvTitulo.text = it.title
            tvDirector.text = it.director
            ivIcono.setImageResource(it.imageResId)
        }
        return view
    }
}