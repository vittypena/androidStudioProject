package es.ua.eps.filmoteca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import es.ua.eps.myapplication.R

/**
 * Created by malozano on 25/12/16.
 */
class FilmAdapter(context: Context?, resource: Int,
                  objects: List<Film>?) : ArrayAdapter<Film>(context!!, resource, objects!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView?:LayoutInflater.from(this.context)
                .inflate(R.layout.item_film, parent, false)

        val txtNombre = view.findViewById<TextView>(R.id.txtFilmItemTitle)
        val txtDirector = view.findViewById<TextView>(R.id.txtFilmItemDirector)
        val imgPoster = view.findViewById<ImageView>(R.id.imgPoster)

        getItem(position)?.let {
            txtNombre.text = it.title
            txtDirector.text = it.director
            imgPoster.setImageResource(it.imageResId)
        }

        return view
    }
}