package com.example.listaultramarinos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    //Variables Globales
    Producto persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Recibir parametros del anterior y hacer el String para el textView (No es necesario, es por darle sentido al ejercicio)
        String nombre = getIntent().getStringExtra("nombrePasoParametros");
        int edad = getIntent().getIntExtra("edadPasoParametros", -1);

        persona = new Producto(nombre, edad);

        //Retornar los datos al Activity llamante****************************************************


    }
}