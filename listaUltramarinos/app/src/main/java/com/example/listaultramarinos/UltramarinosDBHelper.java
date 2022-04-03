package com.example.listaultramarinos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UltramarinosDBHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BASEDATOS = "Ultramarinos.db";
    public static String SQL_CREATION_TABLA;
    public static final int VERSION_BASEDATOS = 1;

    String sqlCreate = "Create table Usuarios (clave INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, codigo INTEGER)";

    public UltramarinosDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
        SQL_CREATION_TABLA = "CREATE TABLE " +
                ContratoUltramarinos.EntradasUltramarinos.NOMBRE_TABLA + "(" +
                ContratoUltramarinos.EntradasUltramarinos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContratoUltramarinos.EntradasUltramarinos.COLUMNA_NOMBRE + " TEXT NOT NULL, " +
                ContratoUltramarinos.EntradasUltramarinos.COLUMNA_CANTIDAD + " INTEGER NOT NULL, " +
                ContratoUltramarinos.EntradasUltramarinos.COLUMNA_MARCA_TIEMPO + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATION_TABLA);
         */
        final String SQL_CREACION_TABLA2 = "CREATE TABLE " +
                ContratoUltramarinos.EntradasUltramarinos.NOMBRE_TABLA + " (" +
                ContratoUltramarinos.EntradasUltramarinos.COLUMNA_NOMBRE + " TEXT NOT NULL, " +
                ContratoUltramarinos.EntradasUltramarinos.COLUMNA_CANTIDAD + " INTEGER NOT NULL " +
                ");";

        sqLiteDatabase.execSQL(SQL_CREACION_TABLA2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ListaUltramarinos");
        onCreate(sqLiteDatabase);
    }
}
