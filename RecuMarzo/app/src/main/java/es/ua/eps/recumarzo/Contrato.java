package es.ua.eps.recumarzo;

public class Contrato {

    public static final String NOMBRE_TABLA = "notas";
    public static final String CAMPO_NOMBRE = "titulo";
    public static final String CAMPO_CANTIDAD = "cantidad";

    public static final String CREAR_TABLA_PRODUCTOS = "CREATE TABLE " + NOMBRE_TABLA +
        "(" + CAMPO_NOMBRE + " text not null, " + CAMPO_CANTIDAD + " integer);";
}
