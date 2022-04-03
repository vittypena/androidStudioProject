package com.example.listaalimentos;

public class Utilidades {

    public static final String NOMBRE_TABLA = "productos";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CANTIDAD = "cantidad";

    public static final String CREAR_TABLA_PRODUCTOS = "CREATE TABLE " + NOMBRE_TABLA +
        "(" + CAMPO_NOMBRE + " text not null, " + CAMPO_CANTIDAD + " integer);";
}
