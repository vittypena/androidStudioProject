package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import es.ua.eps.myapplication.R

class FilmEditActivity : AppCompatActivity() {

    var filmIndex = 0

    companion object {
        private const val TAG = "FilmEditActivity"
        const val EXTRA_FILM_INDEX = "EXTRA_FILM_INDEX"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)

        filmIndex = intent.getIntExtra(EXTRA_FILM_INDEX, -1)

        val btnGuardar = findViewById<Button>(R.id.btnEditSave)
        val btnCancelar = findViewById<Button>(R.id.btnEditCancel)

        btnGuardar.setOnClickListener {
            saveFilmData()
            setResult(Activity.RESULT_OK)
            Log.d(TAG, "Datos guardados")
            finish()
        }
        btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            Log.d(TAG, "Edición cancelada")
            finish()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadFilmData()
    }

    private fun loadFilmData() {
        val etTitulo = findViewById<View>(R.id.editTextTitle) as EditText
        val etDirector = findViewById<View>(R.id.editTextDirector) as TextView
        val etAnyo = findViewById<View>(R.id.editTextYear) as TextView
        val etComentarios = findViewById<View>(R.id.editTextComments) as TextView
        val etImdb = findViewById<View>(R.id.editTextURL) as TextView
        val spinnerFormato = findViewById<View>(R.id.spinnerFormat) as Spinner
        val spinnerGenero = findViewById<View>(R.id.spinnerGenre) as Spinner
        val imgPoster = findViewById<View>(R.id.imageViewCartel) as ImageView

        val film = FilmDataSource.films[filmIndex]
        etTitulo.setText(film.title)
        etDirector.text = film.director
        etAnyo.text = "${film.year}"
        etComentarios.text = film.comments
        etImdb.text = film.imdbUrl
        spinnerFormato.setSelection(film.format)
        spinnerGenero.setSelection(film.genre)
        imgPoster.setImageResource(film.imageResId)
    }

    private fun saveFilmData() {
        val etTitulo = findViewById<View>(R.id.editTextTitle) as EditText
        val etDirector = findViewById<View>(R.id.editTextDirector) as TextView
        val etAnyo = findViewById<View>(R.id.editTextYear) as TextView
        val etComentarios = findViewById<View>(R.id.editTextComments) as TextView
        val etImdb = findViewById<View>(R.id.editTextURL) as TextView
        val spinnerFormato = findViewById<View>(R.id.spinnerFormat) as Spinner
        val spinnerGenero = findViewById<View>(R.id.spinnerGenre) as Spinner
        val imgPoster = findViewById<View>(R.id.imageViewCartel) as ImageView

        val film = FilmDataSource.films[filmIndex]
        film.title = etTitulo.text.toString()
        film.director = etDirector.text.toString()
        film.year = etAnyo.text.toString().toInt()
        film.comments = etComentarios.text.toString()
        film.imdbUrl = etImdb.text.toString()
        film.genre = spinnerGenero.selectedItemPosition
        film.format = spinnerFormato.selectedItemPosition
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpTo(this, Intent(this, FilmListFragment::class.java))
                return true
            }
        }
        return false
    }
}