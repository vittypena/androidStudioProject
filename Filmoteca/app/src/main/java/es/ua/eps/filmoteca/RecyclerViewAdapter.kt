package es.ua.eps.filmoteca

import android.R.attr
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.R.attr.data




class RecyclerViewAdapter (val peliculas: List<Film>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder?>(){

    private var listener: (film: Film, pos: Int) -> Unit = { _ , _ ->}

    fun setOnItemClickListener(listener: (film: Film, pos: Int) -> Unit){
        this.listener = listener  //Guardamos una referencia al listener
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        var titulo: TextView
        var director: TextView
        var icono: ImageView

        fun bind(film: Film){
            titulo.text = film.title
            director.text = film.director
            icono.setImageResource(film.imageResId)
        }

        init{
            titulo = v.findViewById(R.id.titulo)
            director = v.findViewById(R.id.director)
            icono = v.findViewById(R.id.icono)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_listview, parent, false)
        val holder = ViewHolder(v)

        v.setOnClickListener {
            val position: Int = holder.bindingAdapterPosition
            peliculas[position]?.let { listener(it, position) }
        }
        return holder
    }


    override fun getItemCount(): Int {
        return peliculas.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peliculas[position])
    }
}