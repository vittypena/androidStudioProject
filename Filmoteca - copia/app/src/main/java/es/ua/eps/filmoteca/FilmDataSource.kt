package es.ua.eps.filmoteca

object FilmDataSource
{
    //Colección genérica ordenada de elementos que admite la adición y eliminación de elementos.
    val films : MutableList<Film> = mutableListOf<Film>()

    init {
        //Podria tener un constructor tb y meter los parametros dentro

        //Regreso al futuro
        var a = Film()
        a.title = "Regreso al futuro"
        a.director = "Robert Zemeckis"
        a.imageResId = R.drawable.regresofuturo
        a.comments = "Muy antigua"
        a.format = Film.Companion.FORMAT_DIGITAL
        a.genre = Film.Companion.GENRE_SCIFI
        a.imdbUrl = "https://www.imdb.com/title/tt0088763/?ref_=nv_sr_srsg_0"
        a.year = 1985
        films.add(a)

        //Your name
        var b = Film()
        b.title = "Your Name"
        b.director = "Makoto Sinkai"
        b.imageResId = R.drawable.yourname
        b.comments = "Bonita y cautivadora"
        b.format = Film.Companion.FORMAT_BLURAY
        b.genre = Film.Companion.GENRE_DRAMA
        b.imdbUrl = "https://www.imdb.com/title/tt5311514/?ref_=nv_sr_srsg_3"
        b.year = 2016
        films.add(b)

        //Harry potter
        var c = Film()
        c.title = "Harry Potter y la piedra filosofal"
        c.director = "Chris Columbus"
        c.imageResId = R.drawable.harrypotter
        c.comments = "OjeteMora!!"
        c.format = Film.Companion.FORMAT_DIGITAL
        c.genre = Film.Companion.GENRE_ACTION
        c.imdbUrl = "https://www.imdb.com/title/tt0241527/?ref_=fn_al_tt_1"
        c.year = 2001
        films.add(c)

        //El hobbit
        var d = Film()
        d.title = "El hobbit: Un viaje inesperado"
        d.director = "Peter Jackson"
        d.imageResId = R.drawable.elhobbit
        d.comments = "El drogas"
        d.format = Film.Companion.FORMAT_DVD
        d.genre = Film.Companion.GENRE_COMEDY
        d.imdbUrl = "https://www.imdb.com/title/tt0903624/?ref_=fn_al_tt_3"
        d.year = 2012
        films.add(d)

        //Dune
        var e = Film()
        e.title = "Dune"
        e.director = "Denis Villeneuve"
        e.imageResId = R.drawable.dune
        e.comments = "Misteriosa"
        e.format = Film.Companion.FORMAT_BLURAY
        e.genre = Film.Companion.GENRE_HORROR
        e.imdbUrl = "https://www.imdb.com/title/tt1160419/?ref_=fn_al_tt_1"
        e.year = 2021
        films.add(e)

        //Free Guy
        var g = Film()
        g.title = "Free Guy"
        g.director = "Shawn Levy"
        g.imageResId = R.drawable.freeguy
        g.comments = "De videojuegos"
        g.format = Film.Companion.FORMAT_BLURAY
        g.genre = Film.Companion.GENRE_COMEDY
        g.imdbUrl = "https://www.imdb.com/title/tt6264654/?ref_=hm_fanfav_tt_i_2_pd_fp1"
        g.year = 2021
        films.add(g)

        //Misa de medianoche
        var h = Film()
        h.title = "Misa de medianoche"
        h.director = "Mike Flanagan"
        h.imageResId = R.drawable.misanoche
        h.comments = "Estoy cagao"
        h.format = Film.Companion.FORMAT_DVD
        h.genre = Film.Companion.GENRE_HORROR
        h.imdbUrl = "https://www.imdb.com/title/tt10574558/?ref_=adv_li_tt"
        h.year = 2021
        films.add(h)

        //The sandman
        var j = Film()
        j.title = "The Sandman"
        j.director = "Neil Gaiman"
        j.imageResId = R.drawable.sandman
        j.comments = "Que tio ma raro"
        j.format = Film.Companion.FORMAT_DIGITAL
        j.genre = Film.Companion.GENRE_HORROR
        j.imdbUrl = "https://www.imdb.com/title/tt1751634/?ref_=adv_li_i"
        j.year = 2018
        films.add(j)

        //Squid Game
        var k = Film()
        k.title = "Squid Game"
        k.director = "Stephen Fu"
        k.imageResId = R.drawable.squidgame
        k.comments = "El puto viejo"
        k.format = Film.Companion.FORMAT_BLURAY
        k.genre = Film.Companion.GENRE_DRAMA
        k.imdbUrl = "https://www.imdb.com/title/tt10919420/?ref_=hm_fanfav_tt_t_1_pd_fp1"
        k.year = 2021
        films.add(k)

        //The voyeurs
        var l = Film()
        l.title = "The Voyeurs"
        l.director = "Michael Mohan"
        l.imageResId = R.drawable.thevoyeurs
        l.comments = "La rubia tetona poderosa"
        l.format = Film.Companion.FORMAT_BLURAY
        l.genre = Film.Companion.GENRE_ACTION
        l.imdbUrl = "https://www.imdb.com/title/tt11235772/?ref_=hm_stp_pvs_piv_tt_t_2"
        l.year = 2021
        films.add(l)

        //El lobo de Wall Street
        var z = Film()
        z.title = "El lobo de Wall Street"
        z.director = "Michael Mohan"
        z.imageResId = R.drawable.lobowall
        z.comments = "El leon"
        z.format = Film.Companion.FORMAT_DVD
        z.genre = Film.Companion.GENRE_DRAMA
        z.imdbUrl = "https://www.imdb.com/title/tt0993846/?ref_=hm_stp_pvs_piv_tt_t_6"
        z.year = 2013
        films.add(z)

        //Hannah Montana: La película
        var x = Film()
        x.title = "Hannah MontaDa: La película"
        x.director = "Peter Chelsom"
        x.imageResId = R.drawable.hannah
        x.comments = "La crush de mi perrah"
        x.format = Film.Companion.FORMAT_DVD
        x.genre = Film.Companion.GENRE_COMEDY
        x.imdbUrl = "https://www.imdb.com/title/tt1114677/?ref_=nm_knf_t2"
        x.year = 2013
        films.add(x)

        //Venom
        var q = Film()
        q.title = "Venom"
        q.director = "Ruben Fleischer"
        q.imageResId = R.drawable.venom
        q.comments = "El luffy oscuro"
        q.format = Film.Companion.FORMAT_DIGITAL
        q.genre = Film.Companion.GENRE_ACTION
        q.imdbUrl = "https://www.imdb.com/title/tt1270797/?ref_=hm_stp_pvs_piv_tt_t_1"
        q.year = 2018
        films.add(q)
    }
}