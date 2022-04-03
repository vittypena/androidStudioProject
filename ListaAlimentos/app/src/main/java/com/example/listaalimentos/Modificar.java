package com.example.listaalimentos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Modificar extends AppCompatActivity {

    EditText etNombre, etCantidad;
    Button btnCancelar, btnAceptar;

    String nombre;
    int cantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_modificar);

        etNombre = findViewById(R.id.et_AMproducto);
        etCantidad = findViewById(R.id.et_AMexistencias);
        btnCancelar = findViewById(R.id.btn_AMcancelar);
        btnAceptar = findViewById(R.id.btn_AMaceptar);

        Bundle extras = getIntent().getExtras();
        nombre = extras.getString("NOMBRE");
        cantidad = extras.getInt("CANTIDAD");

        etNombre.setText(nombre);
        etCantidad.setText(cantidad + "");

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ModificarProducto().start();

                Intent actividad = new Intent();
                setResult(RESULT_OK, actividad);
                finish();
            }
        });

    }

    private void modificarProducto() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bdAlimentos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Toast.makeText(getApplicationContext(), nombre + " " + cantidad, Toast.LENGTH_SHORT).show();
        db.execSQL("UPDATE " + Utilidades.NOMBRE_TABLA +
                " SET " + Utilidades.CAMPO_CANTIDAD + " = " + Integer.parseInt(etCantidad.getText().toString()) +
                " WHERE " + Utilidades.CAMPO_NOMBRE + " ='" + etNombre.getText().toString() + "'");

        db.close();
    }

    class ModificarProducto extends Thread {
        @Override
        public void run() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        modificarProducto();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
