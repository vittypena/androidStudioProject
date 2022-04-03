package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.ua.eps.filmoteca.R


class FilmDataFragment : Fragment() {

    var filmIndex = -1

    companion object {
        const val EXTRA_FILM_INDEX = "EXTRA_FILM_INDEX"
        private const val REQUEST_CODE_EDIT = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_film_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getInt(EXTRA_FILM_INDEX, -1)?.let {
            filmIndex = it
        }

        val btnImdb = activity?.findViewById<Button>(R.id.button7)
        val btnEditar = activity?.findViewById<Button>(R.id.button8)
        val btnVolver = activity?.findViewById<Button>(R.id.button9)

        btnImdb?.setOnClickListener {
            if(filmIndex >= 0) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(FilmDataSource.films[filmIndex].imdbUrl)
                startActivity(intent)
            }
        }
        btnEditar?.setOnClickListener {
            val intent = Intent(activity, FilmEditActivity::class.java)
            intent.putExtra(FilmEditActivity.Companion.EXTRA_FILM_INDEX, filmIndex)
            startActivityForResult(intent, REQUEST_CODE_EDIT)
        }

        btnVolver?.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        updateFilmData()
    }

    private fun updateFilmData() {

        activity?.let {
            val txtTitulo = it.findViewById<TextView>(R.id.textView2)
            val txtAnyo = it.findViewById<TextView>(R.id.textView7)
            val txtDirector = it.findViewById<TextView>(R.id.textView5)
            val txtComentarios = it.findViewById<TextView>(R.id.textView10)
            val txtFormatoGenero = it.findViewById<TextView>(R.id.textView9)
            val imgPoster = it.findViewById<ImageView>(R.id.textView3)

            if (filmIndex >= 0) {
                val film = FilmDataSource.films[filmIndex]
                txtTitulo.text = film.title
                txtDirector.text = film.director
                txtAnyo.text = "${film.year}"
                txtComentarios.text = film.comments
                txtFormatoGenero.text = "${resources.getStringArray(R.array.formato)[film.format]}, ${resources.getStringArray(R.array.genero)[film.genre]}"
                imgPoster.setImageResource(film.imageResId)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK) {
            updateFilmData()
        }
    }

    fun setFilm(position: Int) {
        filmIndex = position
        updateFilmData()
    }
}