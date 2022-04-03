package es.ua.eps.filmoteca

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FilmListActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var adapter: RecyclerView.Adapter<*>? = null
    var layoutManagerP: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list_recyclerview)


        recyclerView = findViewById(R.id.lista)

        //ADAPTADOR RECYCLERVIEW
        //Creamos el adaptador y se lo asignamos al RecyclerView
        layoutManagerP = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManagerP
        recyclerView?.itemAnimator = DefaultItemAnimator()
        val adapter = RecyclerViewAdapter(FilmDataSource.films)
        recyclerView?.adapter = adapter
        this.adapter = adapter

        adapter.setOnItemClickListener { film, position ->

            val goActivityFilmData = Intent(this@FilmListActivity, FilmDataActivity::class.java)
            goActivityFilmData.putExtra("posicion", position)
            startActivity(goActivityFilmData)

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_caca, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId){
            R.id.miNuevaPelicula -> {
                nuevaPelicula()
                return true
            }
            R.id.miAcercaDe -> {
                acercaDe()
                return true
            }
        }
        return false
    }

    private fun acercaDe() {
        val intent = Intent(FilmListActivity@this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun nuevaPelicula() {
        val f = Film()//Predifinida
        f.title = "<New film>"
        f.imageResId = R.mipmap.ic_launcher
        FilmDataSource.films.add(f)
        adapter?.notifyItemInserted(FilmDataSource.films.size - 1)//La inserta en el adaptador
    }
}