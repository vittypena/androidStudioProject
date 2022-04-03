package com.example.dialogosclase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogoSeleccion.Idioma, Fecha.OnFechaSeleccionada, HoraFragment.OnHoraSeleccionada
{
    private static final String TAG = "MyActivity";
    TextView tv;

    @Override
    protected void onStart() {
        Log.i(TAG, "on start");
        //TODO hacer onstart
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "on resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "on pause");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "on stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "on destroy");
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "on create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonFragmento = findViewById(R.id.button3);
        tv = findViewById(R.id.textView);
        botonFragmento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Esto es una prueba", Snackbar.LENGTH_LONG)
                   //     .show();

                Fragmento2Botones f2b = new Fragmento2Botones();

                f2b.show(getSupportFragmentManager(), "Fragment Dialog");
            }
        });

        Button botonFragmentoSeleccion = findViewById(R.id.button4);

        botonFragmentoSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoSeleccion ds = new DialogoSeleccion();
                ds.show(getSupportFragmentManager(), "F2M");
            }
        });
        TextView texto = findViewById(R.id.textView);
        Button botonPersonalizado = findViewById(R.id.button6);
        botonPersonalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentoPersonalizado fp = new FragmentoPersonalizado(texto);
                fp.show(getSupportFragmentManager(),"Fragmento personalizado");
            }
        });

        Button buttonFecha = findViewById(R.id.button7);

        buttonFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fecha fragmentoFecha = new Fecha();
                fragmentoFecha.show(getSupportFragmentManager(), "Fragmento fecha");
            }
        });

        Button buttonHora = findViewById(R.id.button8);

        buttonHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoraFragment fragmentoHora = new HoraFragment();
                fragmentoHora.show(getSupportFragmentManager(), "Fragmento hora");
            }
        });

        Button buttonSpinner = findViewById(R.id.button5);

        buttonSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivityForResult(i,1234);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            tv.setText(data.getStringExtra("PROVINCIA"));
        }
    }

    @Override
    public void idiomaSeleccionado(String idioma) {
        tv.setText(idioma);
    }

    /*
    @Override
    public void onResultadoFecha(int anyo, int mes, int dia) {
        tv.setText(anyo + "/" + mes + "/" + dia);
    }*/

    @Override
    public void onResultadoFecha(GregorianCalendar calendario) {

        tv.setText(calendario.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(calendario.get(GregorianCalendar.MONTH)+1)+"/"+calendario.get(GregorianCalendar.YEAR));
    }

    @Override
    public void onResultadoFecha(int hora, int minuto) {
        if(minuto<10) {
            tv.setText(hora + ":" + "0" + minuto);
        }else{
            tv.setText(hora+":"+minuto);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_fecha:
                Fecha f = new Fecha();
                f.show(getSupportFragmentManager(), "fecha");
                break;
            case R.id.item_hora:
                HoraFragment h = new HoraFragment();
                h.show(getSupportFragmentManager(),"hora");
                break;
        }
        return true;
    }
}