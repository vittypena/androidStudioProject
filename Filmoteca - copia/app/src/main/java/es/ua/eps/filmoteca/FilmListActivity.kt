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


        //FIN DE ADAPTADOR RECYCLER VIEW


        //------------------------------------------------------------------------
        //val listView = findViewById<RecyclerView>(R.id.lista)

        /* ADAPTADOR SIMPLE LISTVIEW
        //Creamos el adaptador simple
                    //val valores = arrayOf("c", "java", "pascal","python", "perl", "php", "kotlin") //Array de tipo arrayList de valores que introducimos
            //El layout del adaptador se define tb, en vez de uno por defecto, le pasamos el id de el textview dnd ira la informacion y el array
        val adaptador = ArrayAdapter(this,R.layout.item_listview, R.id.titulo, FilmDataSource.films)//Tipo de adaptador, le pasamos el toString de la lista films de la clase filmdatasource
        listView.adapter = adaptador //Unimos el listView con el adaptador
        FIN DE ADAPTADOR SIMPLE LISTVIEW
         */

        /*
        //Adaptador personalizado LISTVIEW
        val adaptador = PeliculasArrayAdapter(this,R.layout.item_listview, FilmDataSource.films)//Usa la clase adaptador creada, que hereda de array adapter de forma personalizada, el layout personalizado y el array de films
        listView.adapter = adaptador

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val elemento = adaptador.getItemId(position)//Obtiene la posicion en el array
            val goActivityFilmData = Intent(this@FilmListActivity, FilmDataActivity::class.java)
            goActivityFilmData.putExtra("posicion", elemento)
            startActivity(goActivityFilmData)
         //FIN DE ADAPTADOR PERSONALIZADO LISTVIEW
        }
        */

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //Con los botones anteriores
        /*
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val goActivityFilmData = Intent(this@FilmListActivity, FilmDataActivity::class.java)
        val goActivityAbout = Intent(this@FilmListActivity, AboutActivity::class.java)
        //var comprobar = intent.getBooleanExtra("comprobar", false)

        button4.setOnClickListener {
            //Envia los datos a FilmDataActivity
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, "Regreso al futuro")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_URL_IMDB, "https://www.imdb.com/title/tt0088763/?ref_=nv_sr_srsg_0")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_DIRECTOR,"Robert Zemeckis")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_ANIO_LANZAMIENTO,"1985")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_GENERO,"Sci-Fi")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_FORMATO,"DVD")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_IMAGEN, "regresofuturo")

                if(intent.extras?.get("Bitmap") as Bitmap? != null)
                    goActivityFilmData.putExtra("Bitmap", intent.extras?.get("Bitmap") as Bitmap?)
                if(intent.extras?.get("Uri") as Uri? != null)
                    goActivityFilmData.putExtra("Uri", intent.extras?.get("Uri") as Uri?)
                goActivityFilmData.putExtra("comprobar", comprobar)

            startActivity(goActivityFilmData)
        }

        button5.setOnClickListener {
            //Envia los datos a FilmDataActivity
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, "Your Name")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_URL_IMDB,"https://www.imdb.com/title/tt5311514/?ref_=nv_sr_srsg_3")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_DIRECTOR,"Makoto Sinkai")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_ANIO_LANZAMIENTO,"2016")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_GENERO,"Drama")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_FORMATO,"Bluray")
            goActivityFilmData.putExtra(FilmDataActivity.EXTRA_IMAGEN, "yourname")

            //Recibe los datos si han sido cambiados
                if(intent.extras?.get("Bitmap") as Bitmap? != null)
                goActivityFilmData.putExtra("Bitmap", intent.extras?.get("Bitmap") as Bitmap?)
                if(intent.extras?.get("Uri") as Uri? != null)
                goActivityFilmData.putExtra("Uri", intent.extras?.get("Uri") as Uri?)
                goActivityFilmData.putExtra("comprobar", comprobar)

            startActivity(goActivityFilmData)
        }
        */

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

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