package es.ua.eps.recumarzo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActivityAlta extends AppCompatActivity {
    Spinner spinner;
    final String[] categoriaSeleccionada = new String[1];
    int seleccion;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        conn = new ConexionSQLiteHelper(this, "bdAlimentos", null, 1);

        Button btAceptar;
        btAceptar = findViewById(R.id.btAceptar);

        String[] categoria = new String[] {"Cita/Reunión", "Cosas Pendientes", "Varios"};
        ArrayAdapter<String> adaptadorCategorias = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categoria);
        spinner = findViewById(R.id.spinner);

        spinner.setAdapter(adaptadorCategorias);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoriaSeleccionada[0] = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcionAlta();
                
                Intent activityAlta = new Intent(ActivityAlta.this, MainActivity.class);
                startActivity(activityAlta);
            }

            private void funcionAlta() {
                SQLiteDatabase db = conn.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(Contrato.CAMPO_NOMBRE, etProducto.getText().toString());
                values.put(Contrato.CAMPO_CANTIDAD, contador);

                db.insert(Contrato.NOMBRE_TABLA, null, values);
                db.close();

                consultarListaProductos();
            }
        });
    }
}