package es.ua.eps.globalexamenandroidev1.ActivityPasoParametrosRetornar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.ua.eps.globalexamenandroidev1.R;

public class Activity_PasoParametros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso_parametros);

        //INTENT MAIN ACTIVITY
            //Variables del intent extra desde MainActivity
        boolean comprobarExtraIntent = getIntent().getBooleanExtra("booleanPasoParametros", false);
        String textViewExtraIntent = getIntent().getStringExtra("TextViewPasoParametros");
        int imageViewExtraIntent = getIntent().getIntExtra("ImageVIewPasoParametros", -1);
        int[] listaNumerosExtraIntent = getIntent().getIntArrayExtra("int[]PasoParametros");

            //Probar las variables del intent desde Main activity
        if(comprobarExtraIntent){Toast.makeText(getApplicationContext(), "Verdadero", Toast.LENGTH_SHORT).show();}else{Toast.makeText(getApplicationContext(), "Falso", Toast.LENGTH_SHORT).show();}
        Toast.makeText(getApplicationContext(), textViewExtraIntent, Toast.LENGTH_SHORT).show();
        ImageView imagenPasoParametros = findViewById(R.id.imageViewParametros);
        imagenPasoParametros.setImageDrawable(getDrawable(imageViewExtraIntent));

        //RETORNAR DATOS ACTIVITY (Inicia el activity Activity_RetornarParametros y este devuelve los datos)*************************************************************
        Button botonRetornar = findViewById(R.id.buttonRetornarParametros);//Llama al activity que retornara
        TextView textViewRetornar = findViewById(R.id.textViewRetornar);//Para introducir los datos retornados

        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(//Necesitamos el result launcher para poder invocarlo retornando datos
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();//Para acceder a los datos devueltos
                        if(result.getResultCode() == Activity.RESULT_OK){//Si ha sido result_ok devolvemos los parametros
                            if (data != null && !data.getStringExtra("datoRetornado").equals("")) {//Si no es nulo hacemos las acciones que sean con los datos devueltos
                                textViewRetornar.setText(data.getStringExtra("datoRetornado"));
                            }
                        }
                    }
                }
        );

        botonRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity_Retornar = new Intent(Activity_PasoParametros.this, Activity_RetornarParametros.class);
                goToActivity_Retornar.putExtra("int[]PasoParametros", listaNumerosExtraIntent);
                resultLauncher.launch(goToActivity_Retornar);
            }
        });

    }
}