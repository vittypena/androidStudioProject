package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int anchoPantalla, altoPantalla;
    int anchoBoton, altoBoton;
    int numeroFilas = 5;
    int numColores = 6;

    int aciertos = 0;
    int victorias = 0;
    int derrotas = 0;
    int numPartidas = 0;

    boolean fallo = false;
    boolean seAcabo = false;

    public static int MILISEGUNDOS_ESPERA = 5000;

    GridLayout tableroJuego;

    Boton[][] boton;

    Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableroJuego = findViewById(R.id.gridLayout);
        validar = findViewById(R.id.boton_validar);
        dimensionPantalla();

        anchoBoton = anchoPantalla / numeroFilas;
        altoBoton = altoPantalla / (numeroFilas + 1) ;

        iniciarJuego();
        //crearBotones();
        //asignarColor();

    }

    public void crearBotones(){
        tableroJuego.removeAllViews();
        tableroJuego.setColumnCount(numeroFilas);
        tableroJuego.setRowCount(numeroFilas);

        boton = new Boton[numeroFilas][numeroFilas];

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroFilas; j++) {
                boton[i][j] = new Boton(this);
                boton[i][j].setId(View.generateViewId());
                boton[i][j].setOnClickListener(this);
                boton[i][j].setLayoutParams(new ViewGroup.LayoutParams(anchoBoton,altoBoton));
                tableroJuego.addView(boton[i][j]);
            }
        }
    }

    @Override
    public void onClick(View view) {
        Boton btn = (Boton) view;
        Indice indice = buscarIndice(btn.getId());

        if(btn.hayColor) {
            //Toast.makeText(MainActivity.this, "hay color", Toast.LENGTH_SHORT).show();
            btn.setEnabled(false);
            aciertos++;
        }
        else {
            //Toast.makeText(MainActivity.this, "no hay color", Toast.LENGTH_SHORT).show();
            btn.setEnabled(false);
            fallo = true;
        }

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seAcabo) {
                    if (aciertos == 6 && !fallo) {
                        Toast.makeText(MainActivity.this, "Has ganado", Toast.LENGTH_SHORT).show();
                        victorias++;
                    } else if (fallo) {
                        Toast.makeText(MainActivity.this, "Has perdido", Toast.LENGTH_SHORT).show();
                        derrotas++;
                        fallo = true;
                    }

                    if (victorias < 3 && derrotas < 3) {
                        Toast.makeText(MainActivity.this, "Móvil " + derrotas + " - Ismael " + victorias, Toast.LENGTH_SHORT).show();
                        iniciarJuego();
                    } else {
                        Toast.makeText(MainActivity.this, "Móvil " + derrotas + " - Ismael " + victorias, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Se han acabado las partidas", Toast.LENGTH_SHORT).show();
                        seAcabo = true;
                    }
                    aciertos = 0;
                    fallo = false;
                }
                else {
                    Toast.makeText(MainActivity.this, "Se han acabado las partidas", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //Toast.makeText(this, indice.i + " " + indice.j, Toast.LENGTH_SHORT).show();

    }

    public Indice buscarIndice(int id) {
        for(int i = 0; i < numeroFilas; i++) {
            for(int j = 0; j < numeroFilas; j++) {
                if(id == boton[i][j].getId()) {
                    return new Indice(i,j);
                }
            }
        }
        return null;
    }

    public void asignarColor() {
        ArrayList<Indice> listaNumeros = new ArrayList<Indice>();
        for(int i = 0; i < numeroFilas; i++) {
            for(int j = 0; j < numeroFilas; j++) {
                Indice indice = new Indice(i,j);
                listaNumeros.add(indice);
            }
        }

        int limite = numeroFilas * numeroFilas;

        for (int i = 1; i <= numColores; i++) {
            int numAleatorio = (int) (Math.random() * limite);
            limite--;
            Indice indice = listaNumeros.remove(numAleatorio);
            boton[indice.i][indice.j].setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            boton[indice.i][indice.j].hayColor = true;
            //boton[indice.i][indice.j].setOnClickListener(this);

        }
        esperarYCerrar(MILISEGUNDOS_ESPERA);
    }

    public void iniciarJuego() {
        crearBotones();
        asignarColor();
    }

    void dimensionPantalla(){
        Display mdisp = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        mdisp.getSize(p);
        anchoPantalla = p.x;
        altoPantalla = p.y;
    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                //Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < numeroFilas; i++) {
                    for (int j = 0; j < numeroFilas; j++) {
                        if(i % 2 == 0 && j % 2 == 0) {
                            boton[i][j].setBackgroundColor(Color.GRAY);
                        }
                        else if(i % 2 == 0 && j % 2 != 0) {
                            boton[i][j].setBackgroundColor(Color.BLUE);
                        }
                        else if(i % 2 != 0 && j % 2 == 0) {
                            boton[i][j].setBackgroundColor(Color.RED);
                        }
                        else {
                            boton[i][j].setBackgroundColor(Color.GREEN);
                        }
                    }
                }
            }
        }, milisegundos);
    }



    class Indice {
        int i, j;
        Indice(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}