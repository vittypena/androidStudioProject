package com.example.listaultramarinos;

import android.provider.BaseColumns;

public class ContratoUltramarinos {

    public ContratoUltramarinos() {
    }

    public static final class EntradasUltramarinos implements BaseColumns{
        public static final String NOMBRE_TABLA = "ListaUltramarinos";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_CANTIDAD = "cantidad";
        public static final String COLUMNA_MARCA_TIEMPO = "marca-tiempo";
    }


}
