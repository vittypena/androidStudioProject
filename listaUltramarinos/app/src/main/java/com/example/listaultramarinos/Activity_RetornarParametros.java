package com.example.listaultramarinos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_RetornarParametros extends AppCompatActivity {
    //Variables Globales
    Producto persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retornar_parametros);

        //Recibir parametros del anterior y hacer el String para el textView (No es necesario, es por darle sentido al ejercicio)
        String nombre = getIntent().getStringExtra("nombrePasoParametros");
        int edad = getIntent().getIntExtra("edadPasoParametros", -1);

        persona = new Producto(nombre, edad);

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etEdad = findViewById(R.id.etEdad);

        etNombre.setText(nombre);
        etEdad.setText(edad+"");

        //Retornar los datos al Activity llamante****************************************************
        Button botonAceptarRetorno = findViewById(R.id.buttonAceptar);
        Button botonCancelarRetorno = findViewById(R.id.buttonCancelar);

        botonCancelarRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);//Devuelve resultado negativo
                finish();//Cierra el activity llamado desde activityForResult
            }
        });

        botonAceptarRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("nombreRetornado", etNombre.getText().toString());
                data.putExtra("edadRetornado",etEdad.getText().toString());
                setResult(RESULT_OK, data);//Enviamos tb el intent con los datos retornados
                finish();
            }
        });


    }
}