package es.ua.eps.filmoteca

import es.ua.eps.myapplication.R
import java.util.*

/**
 * Created by malozano on 11/5/16.
 */
object FilmDataSource {
    val films: MutableList<Film> = mutableListOf<Film>()

    init {
        var f = Film()
        f.title = "Regreso al futuro"
        f.director = "Robert Zemeckis"
        f.imageResId = R.mipmap.ic_launcher
        f.comments = ""
        f.format = Film.Companion.FORMAT_DIGITAL
        f.genre = Film.Companion.GENRE_SCIFI
        f.imdbUrl = "http://www.imdb.com/title/tt0088763"
        f.year = 1985
        films.add(f)

        f = Film()
        f.title = "Regreso al futuro II"
        f.director = "Robert Zemeckis"
        f.imageResId = R.mipmap.ic_launcher
        f.comments = ""
        f.format = Film.Companion.FORMAT_DIGITAL
        f.genre = Film.Companion.GENRE_SCIFI
        f.imdbUrl = "http://www.imdb.com/title/tt0096874"
        f.year = 1989
        films.add(f)

        f = Film()
        f.title = "Regreso al futuro III"
        f.director = "Robert Zemeckis"
        f.imageResId = R.mipmap.ic_launcher
        f.comments = ""
        f.format = Film.Companion.FORMAT_DIGITAL
        f.genre = Film.Companion.GENRE_SCIFI
        f.imdbUrl = "http://www.imdb.com/title/tt0099088"
        f.year = 1990
        films.add(f)

        f = Film()
        f.title = "Los cazafantasmas"
        f.director = "Ivan Reitman"
        f.imageResId = R.mipmap.ic_launcher
        f.comments = ""
        f.format = Film.Companion.FORMAT_DIGITAL
        f.genre = Film.Companion.GENRE_COMEDY
        f.imdbUrl = "http://www.imdb.com/title/tt0087332"
        f.year = 1984
        films.add(f)

        // Añade tantas peliculas como quieras
    }
}