package es.ua.eps.examenfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExamenDBHelper extends SQLiteOpenHelper {

    public ExamenDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATION = "CREATE TABLE " +
                ContratoExamen.Asistencia.NOMBRE_TABLA + " (" +
                ContratoExamen.Asistencia.COLUMNA_NOMBRE + " TEXT NOT NULL, " +
                ContratoExamen.Asistencia.COLUMNA_NACIONALIDAD + " TEXT NOT NULL, " +
                ContratoExamen.Asistencia.COLUMNA_HORAENTRADA + " INTEGER NOT NULL, " +
                ContratoExamen.Asistencia.COLUMNA_MINUTOSENTRADA + " INTEGER NOT NULL, " +
                ContratoExamen.Asistencia.COLUMNA_HORASALIDA + " INTEGER NOT NULL, " +
                ContratoExamen.Asistencia.COLUMNA_MINUTOSSALIDA + " INTEGER NOT NULL " +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Asistentes");
        onCreate(sqLiteDatabase);
    }
}
