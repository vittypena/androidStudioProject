package es.ua.eps.examenfinal;

import android.provider.BaseColumns;

public class ContratoExamen {

    public ContratoExamen() {
    }

    public static final class Asistencia implements BaseColumns{
        public static final String NOMBRE_TABLA = "Asistentes";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_NACIONALIDAD= "nacionalidad";
        public static final String COLUMNA_HORAENTRADA = "horaEntrada";
        public static final String COLUMNA_MINUTOSENTRADA = "minutosEntrada";
        public static final String COLUMNA_HORASALIDA= "horaSalida";
        public static final String COLUMNA_MINUTOSSALIDA = "minutosSalida";
    }


}
