package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils

class FilmDataActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM_TITLE = "EXTRA_FILM_TITLE"
        const val EXTRA_URL_IMDB = "EXTRA_URL_IMDB"
        const val EXTRA_DIRECTOR = "EXTRA_DIRECTOR"
        const val EXTRA_ANIO_LANZAMIENTO = "EXTRA_ANIO_LANZAMIENTO"
        const val EXTRA_GENERO = "EXTRA_GENERO"
        const val EXTRA_FORMATO = "EXTRA_FORMATO"
        const val EXTRA_IMAGEN = "EXTRA_IMAGEN"
        const val EXTRA_FILM_INDEX = "EXTRA_FILM_INDEX"
    }

    private lateinit var textView5: TextView
    private lateinit var imageView3: ImageView
    private var comprobar: Boolean = false
    private var imgBitmap :Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val textView2 = findViewById<TextView>(R.id.textView2)
        textView5 = findViewById(R.id.textView5)
        val textView7 = findViewById<TextView>(R.id.textView7)
        val textView8 = findViewById<TextView>(R.id.textView8)
        val textView9 = findViewById<TextView>(R.id.textView9)
        val textView10 = findViewById<TextView>(R.id.textView10)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)


        imageView3 = findViewById(R.id.imageView3)
        comprobar = intent.getBooleanExtra("comprobar", false)
        var imgUri: Uri? = null

        val extraIntent_posicion = intent.getIntExtra("posicion", -1)//Long en listview
        val extraIntent_titulo = FilmDataSource.films[extraIntent_posicion].title
        var extraIntent_url = FilmDataSource.films[extraIntent_posicion].imdbUrl
        val extraIntent_director = FilmDataSource.films[extraIntent_posicion].director
        val extraIntent_anio = FilmDataSource.films[extraIntent_posicion].year
        val extraIntent_genero = resources.getStringArray(R.array.genero)[FilmDataSource.films[extraIntent_posicion].genre]
        val extraIntent_formato = resources.getStringArray(R.array.formato)[FilmDataSource.films[extraIntent_posicion].format]
        val extraIntent_imagen = FilmDataSource.films[extraIntent_posicion].imageResId
        val goActivityList = Intent(this@FilmDataActivity, FilmListActivity::class.java)
        val goActivityEdit = Intent( this@FilmDataActivity, FilmEditActivity::class.java)

        textView2.text = extraIntent_titulo
        textView5.text = extraIntent_director
        textView7.text = extraIntent_anio.toString()
        textView8.text = extraIntent_formato.toString()
        textView9.text = extraIntent_genero.toString()
        textView10.text = FilmDataSource.films[extraIntent_posicion].comments

        //Poner Imagen
        if(!comprobar){
            //imageView3.setImageResource(resources.getIdentifier(extraIntent_imagen, "drawable", packageName))
            imageView3.setImageResource(extraIntent_imagen)
        }else{
            if(intent.extras?.get("Bitmap") as Bitmap? != null){
            imgBitmap = intent.extras?.get("Bitmap") as Bitmap
            val resizedBitmap = Bitmap.createScaledBitmap(imgBitmap!!, 5000, 5000, false)//He probado 5000 por que se ve que si paso del tope del imageView da igual
            imageView3.setImageBitmap(resizedBitmap)
            goActivityEdit.putExtra("comprobar", comprobar)
            goActivityEdit.putExtra("imagenBitmap", imgBitmap)
            }
        }

        if(!comprobar) {
            goActivityEdit.putExtra("imagenExtra", extraIntent_imagen)
        }


        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data

                if (data != null && !data.getStringExtra("titulo").equals("")) {
                    FilmDataSource.films[extraIntent_posicion].title =  data.getStringExtra("titulo")
                    textView2.text = data.getStringExtra("titulo")
                    //textView2.text = data?.getStringExtra("titulo")
                }
                if (data != null && !data.getStringExtra("director").equals("")) {
                    textView5.text = data.getStringExtra("director")
                    FilmDataSource.films[extraIntent_posicion].director = data.getStringExtra("director")
                }

                if (data != null && !data.getStringExtra("anyo").equals("")) {
                    FilmDataSource.films[extraIntent_posicion].year = data.getStringExtra("anyo")!!.toInt()
                    textView7.text = data.getStringExtra("anyo")
                }

                if (data != null && !data.getStringExtra("formato").equals(""))
                    textView8.text = data.getStringExtra("formato")

                if (data != null && !data.getStringExtra("genero").equals(""))
                    textView9.text = data.getStringExtra("genero")

                if (data != null && !data.getStringExtra("comentarios").equals(""))
                    textView10.text = data.getStringExtra("comentarios")

                if (data?.getStringExtra("enlace") != null)
                    extraIntent_url = data.getStringExtra("enlace")

                //comprobar = data!!.getBooleanExtra("comprobar",false)
                if(data != null && (data.extras?.get("imagen") as Bitmap? != null)) {
                    imgBitmap = data.extras?.get("imagen") as Bitmap?
                    val resizedBitmap = Bitmap.createScaledBitmap(imgBitmap!!, 3000, 3000, false)
                    imageView3.setImageBitmap(resizedBitmap)
                    comprobar=true
                    goActivityEdit.putExtra("comprobar", comprobar)
                    goActivityEdit.putExtra("imagenBitmap", imgBitmap)
                    goActivityEdit.removeExtra("imagenUri")
                }
                if(data != null && (data.extras?.get("galeria") as Uri? != null)) {
                    imgUri = data.extras?.get("galeria") as Uri?
                    imageView3.setImageURI(data.extras?.get("galeria") as Uri?)
                    comprobar=true
                    goActivityEdit.putExtra("comprobar", comprobar)
                    goActivityEdit.putExtra("imagenUri", imgUri)
                    goActivityEdit.removeExtra("imagenBitmap")
                }
            }
        }

        button7.setOnClickListener {
            val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(extraIntent_url))
            startActivity(viewIntent)
        }

        button8.setOnClickListener {
            resultLauncher.launch(goActivityEdit)
        }

        button9.setOnClickListener {
            goActivityList.putExtra("comprobar",comprobar)
            goActivityList.putExtra("Bitmap", imgBitmap)
            goActivityList.putExtra("Uri", imgUri)
            goActivityList.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(goActivityList)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if(item.itemId == android.R.id.home){ //ID especial para boton home
            NavUtils.navigateUpTo(this, Intent(this, FilmListActivity::class.java))
            return true
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("boleano", comprobar)
        outState.putString("director", textView5.text.toString())
        //Datos grandes como bitmap hay que serializarlos
        outState.putParcelable("Bitmap", imgBitmap)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        textView5.text = savedInstanceState.getString("director")
        if (savedInstanceState.getBoolean("boleano")){
            imgBitmap = savedInstanceState.getParcelable("Bitmap")
            val resizedBitmap = Bitmap.createScaledBitmap(imgBitmap!!, 5000, 5000, false)//He probado 5000 por que se ve que si paso del tope del imageView da igual
            imageView3.setImageBitmap(resizedBitmap)
        }
    }

}