package es.ua.eps.globalexamenandroidev1.ActivityPasoParametrosRetornar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import es.ua.eps.globalexamenandroidev1.R;

public class Activity_RetornarParametros extends AppCompatActivity {
    //Variables Globales
    int suma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retornar_parametros);

        //Recibir parametros del anterior y hacer el String para el textView (No es necesario, es por darle sentido al ejercicio)
        int[] listaNumerosExtraIntent = getIntent().getIntArrayExtra("int[]PasoParametros");
        String cadena = "Los numeros son: ";
        suma = 0;
        for (int i = 0; i < listaNumerosExtraIntent.length; i++){
            cadena+= " " + listaNumerosExtraIntent[i];
            suma+= listaNumerosExtraIntent[i];
        }
        cadena += " y su suma total es: " + suma;
        TextView textViewSuma = findViewById(R.id.textViewMostrarIntent);
        textViewSuma.setText(cadena);

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
                data.putExtra("datoRetornado", suma + "");//Debe de ser una variable Global para poder acceder a ella, los "" es para pasar un String en este caso
                setResult(RESULT_OK, data);//Enviamos tb el intent con los datos retornados
                finish();
            }
        });
    }
}