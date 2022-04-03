package es.ua.eps.pruebasdebugger

class Lenguaje
{
    var typo : String? = null
    var descripcion : String? = null

    constructor(typo: String?, descripcion: String?) {
        this.typo = typo
        this.descripcion = descripcion
    }


    override fun toString(): String {
        return "$typo"
    }

}