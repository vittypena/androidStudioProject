package es.ua.eps.hipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DificultadDialogFragment.DificultadDialogListener, SpinnerDialogFragment.SpinnerDialogListener,View.OnLongClickListener, View.OnClickListener {
    int[] imagenPersonaje ={R.mipmap.ic_menuaction_camuflada,R.mipmap.ic_menuaction_espaniola,R.mipmap.ic_menuaction_presumida,R.mipmap.ic_menuaction_nocturna};
    int[] imagenComprobar ={R.mipmap.ic_menuaction_cero,R.mipmap.ic_menuaction_uno,R.mipmap.ic_menuaction_dos,R.mipmap.ic_menuaction_tres,R.mipmap.ic_menuaction_cuatro,R.mipmap.ic_menuaction_cinco,R.mipmap.ic_menuaction_seis,R.mipmap.ic_menuaction_siete,R.mipmap.ic_menuaction_ocho};
    int numeroTotalBotones;
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Integer> hipoNumRandom = new ArrayList<>();
    int seleccion = 1;
    int contadorVictoria = 0;
    int cantidadHipotenochas = 0;
    int numColumnas = 0;
    int numFilas = 0;
    boolean finDelJuego = false;
    String dificultad ="";
    Toolbar myToolbar;
    TableLayout tableLayout;//Se instancia despues de instanciar la vista
    Menu menuPersonaje;//Para poder acceder el menu item desde otra parte

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //...........................MENU...........................//
        //Añadir la Toolbar en el main, previamente puesta en el xml y deshabilitar la barra nativa (elegir import android x)
        //Añadir icono con setOverflowIcon previamente introducido como image asset en legacy icons del mipmap
        //Añadir el menu.xml con sus items
        //Metodo onCreateOptionsMenu e incluir el inflater
        //Metodo onOptionsItemSelected para indicar acciones
            //Toolbar myToolbar; de ser necesario, si no esta al principio
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setOverflowIcon(getDrawable(R.mipmap.ic_menuactionhipo));
        myToolbar.setNavigationIcon(R.mipmap.ic_menuaction2);
        //...........................MENU...........................//

        //Crear al principio el layout dinamico
        tableLayout = findViewById(R.id.tableLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hipotenochas, menu);
        menuPersonaje = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){//El return true es necesario
            case R.id.menu_intrucciones:
                //Exhibir el fragmento instruccionesdialogfragment
                DialogFragment fragmentoDialogo = new IntruccionesDialogFragment();
                fragmentoDialogo.show(getSupportFragmentManager(),"intrucciones");
                return true;
            case R.id.menu_nuevo_juego:
                //principante -> 8x8 (10 hipo)
                //amateur -> 12x12 (30 hipo)
                //avanzado -> 16x16 (60 hipo)
                switch (dificultad){
                    case "Principiante":
                        numFilas=8;
                        numColumnas=8;
                        crearTableLayout(8,8,10);
                        finDelJuego=false;
                        contadorVictoria = 0;
                        menuPersonaje.getItem(2).setEnabled(false);
                        break;
                    case "Amateur":
                        numFilas=12;
                        numColumnas=12;
                        crearTableLayout(12,12,30);
                        finDelJuego=false;
                        contadorVictoria = 0;
                        menuPersonaje.getItem(2).setEnabled(false);
                        break;
                    case "Avanzado":
                        numFilas=16;
                        numColumnas=16;
                        crearTableLayout(16,16,60);
                        finDelJuego=false;
                        contadorVictoria = 0;
                        menuPersonaje.getItem(2).setEnabled(false);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Selecciona la dificultad", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.menu_configura_el_juego:
                DialogFragment fragmentoDialogoRadioButtons = new DificultadDialogFragment();
                fragmentoDialogoRadioButtons.show(getSupportFragmentManager(),"radioButtons");
                return true;
            case R.id.menu_personaje:
                DialogFragment fragmentoDialogoSpinner = new SpinnerDialogFragment();
                fragmentoDialogoSpinner.show(getSupportFragmentManager(),"spinner");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Interfaces
    public void onDialogPositiveClick(String dificultad){
        this.dificultad=dificultad;
    }

    @Override
    public void onDialogSpinner(int seleccion) {
        //Para insertar la imagen segun hemos creado un array con la ruta de las imagenes, asi que accedemos
        //a ese array con la posicion que hemos obtenido
        if(seleccion!=-1) {
            myToolbar.setOverflowIcon(getDrawable(imagenPersonaje[seleccion]));
            Toast.makeText(getApplicationContext(), "El item seleccionado es: " + seleccion + "", Toast.LENGTH_SHORT).show();
            this.seleccion=seleccion;
        }
    }

    //...........................TABLELAYOUTDINAMICO...........................//
        //Para crear un tablelayout dinamico debemos crear el tablelayout sin filas en en xml
        //Iniciar arriba el tablelayout con findbyid
        //Pasar al metodo crearTableLayout las filas y columnas
    public void crearTableLayout(int numeroFilas, int numeroColumnas, int cantidad){
            //int cantidad en el constructor solo es necesario para la hipotenocha
        numeroTotalBotones = numeroFilas*numeroColumnas;
        ids.clear();//Solo es necesario para la hipo
        hipoNumRandom.clear();//Solo es necesario para la hipo
        tableLayout.removeAllViews();
        for (int i = 0;i <numeroFilas;i++){//8 veces
            TableRow fila = new TableRow(getApplicationContext());//Instanciamos
            //Declaramos atributos de la fila
            TableLayout.LayoutParams lpFila = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);//Parametros del layout para el boton
            lpFila.weight=1;//Peso
            fila.setLayoutParams(lpFila);//Los añadimos a la fila
            //Agregamos elementos a la tabla
            for (int y = 0; y < numeroColumnas; y++){
                Button boton = new Button(getApplicationContext());//Instanciamos
                int id = View.generateViewId();//Guardamos el id generado para luego poder acceder a él mediante el array ids
                boton.setId(id);
                    //Este codigo es solo necesario para la hipo
                        ids.add(id);
                    //Fin de codigo innecesario para demas app
                boton.setOnLongClickListener(this);
                boton.setOnClickListener(this);
                TableRow.LayoutParams lpButton = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);//Parametros del layout para el boton
                lpButton.weight=1;
                boton.setLayoutParams(lpButton);//Añadimos los parametros al boton
                fila.addView(boton);//Añadimos el boton a la fila
            }
            //Agregamos la fila a el tablelayout
            tableLayout.addView(fila);
        }
        //Codigo innecesario , solo necesario para la hipo
        generarNumerosAleatorios(cantidad);
        cantidadHipotenochas = cantidad;
        //Fin codigo innecesario
    }
    //...........................TABLELAYOUTDINAMICO...........................//

    public void generarNumerosAleatorios(int cantidad){
        for (int i = 0; i < cantidad; i++){
            int numeroRandom = (int)(Math.random() * ((ids.get(ids.size()-1) - ids.get(0)) + 1)) + ids.get(0);//Generara aleatoriamente desde el primer numero de la lista hasta el último
            for (int y = 0; y < hipoNumRandom.size(); y++){
                while (hipoNumRandom.get(y)==numeroRandom){
                    numeroRandom = (int)(Math.random() * ((ids.get(ids.size()-1) - ids.get(0)) + 1)) + ids.get(0);
                }
            }
            hipoNumRandom.add(numeroRandom);
        }
    }

    //Para implementar onLongClick para todos, lo implementamos en la clase con View.OnLongClickListener e implementamos este metodo
    //Implementamos el listener en el propio boton, boton.setOnClickListener(this);
    @Override
    public boolean onLongClick(View v) {
        if(!finDelJuego) {
                int numeroBoton = v.getId();
                for (int i = 0; i < hipoNumRandom.size(); i++) {
                    if (numeroBoton == hipoNumRandom.get(i)) {
                        v.setBackground(getDrawable(R.mipmap.ic_menuaction_x));
                        contadorVictoria++;
                        finDelJuego = false;
                        v.setEnabled(false);
                        break;
                    }else if (numeroBoton != hipoNumRandom.get(i)){
                        finDelJuego = true;
                    }
                }
                if (finDelJuego){
                    Snackbar.make(v, "-------------------------------FIN DEL JUEGO-------------------------", Snackbar.LENGTH_SHORT).show();
                    mostrarTodos();
                    menuPersonaje.getItem(2).setEnabled(true);
                }
                if (contadorVictoria == cantidadHipotenochas) {
                    finDelJuego = true;
                    menuPersonaje.getItem(2).setEnabled(true);
                    Snackbar.make(v, "-------------------------------HAS GANADO-------------------------", Snackbar.LENGTH_LONG).show();
                }
        }else{
            Toast.makeText(getApplicationContext(), "Debes empezar una nueva partida, titann W", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //Para implementar OnClickListener para todos, lo implementamos en la clase con View.OnClickListener e implementamos este metodo
    //Implementamos el listener en el propio boton, boton.setOnClickListener(this);
    @Override
    public void onClick(View v) {
        if (!finDelJuego) {
            int numeroBoton = v.getId();
            for (int i = 0; i < hipoNumRandom.size(); i++) {
                if (numeroBoton == hipoNumRandom.get(i)) {
                    Button botonAñadir = findViewById(hipoNumRandom.get(i));
                    botonAñadir.setBackground(getDrawable(imagenPersonaje[seleccion]));
                    mostrarTodos();
                    finDelJuego = true;
                    menuPersonaje.getItem(2).setEnabled(true);
                    Snackbar.make(v, "-------------------------------FIN DEL JUEGO-------------------------", Snackbar.LENGTH_SHORT).show();
                    break;
                } else {
                    int numero = comprobarCelda(numeroBoton);
                    v.setBackground(getDrawable(imagenComprobar[numero]));
                    v.setEnabled(false);
                }
            }
        }else{
            Toast.makeText(getApplicationContext(), "Debes empezar una nueva partida, loser", Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarTodos(){
        for (int i = 0; i < hipoNumRandom.size(); i++){
            Button botonAñadir = findViewById(hipoNumRandom.get(i));
            botonAñadir.setBackground(getDrawable(imagenPersonaje[seleccion]));
        }
    }

    public int comprobarCelda(int idBoton){
        int numeroArrayFoto = 0;
        boolean comprobar = false;
            if (idBoton == ids.get(0))  {//PrimeraFilaIzq
                int[] listaNumeros = new int[] {idBoton+1,idBoton+numColumnas,idBoton+numColumnas+1};
                numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                comprobar = true;
            }
            if (idBoton == ids.get(numColumnas-1))  {//PrimeraFilaDer
                int[] listaNumeros = new int[] {idBoton-1,idBoton+numColumnas-1,idBoton+numColumnas};
                numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                comprobar = true;
            }
            if (idBoton == ids.get(ids.size()-numColumnas)) {//UltimaFilaIzq
                int[] listaNumeros = new int[] {idBoton-numColumnas,(idBoton-numColumnas)+1,idBoton+1};
                numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                comprobar = true;
            }
            if (idBoton == ids.get(ids.size()-1)) {//UltimaFilaDer
                int[] listaNumeros = new int[] {(idBoton-numColumnas)-1,idBoton-numColumnas,idBoton-1};
                numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                comprobar = true;
            }

            for (int i = 1; i <= (numColumnas-2);i++){//PrimeraFilaMedio
                if(idBoton == ids.get(i)){
                    int[] listaNumeros = new int[] {idBoton-1,idBoton+1,(idBoton+numColumnas)-1,idBoton+numColumnas,idBoton+numColumnas+1};
                    numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                    comprobar = true;
                }
            }

            for (int i = (ids.size()-numColumnas)+1; i <= (ids.size()-2);i++){//UltimaFilaMedio
                if (idBoton == ids.get(i)){
                    int[] listaNumeros = new int[] {(idBoton-numColumnas)-1,idBoton-numColumnas,(idBoton-numColumnas)+1,idBoton-1,idBoton+1};
                    numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                    comprobar = true;
                }
            }

            for (int i = 1; i<(numFilas-1);i++){//MedioIzq
                int num = numColumnas;
                num=i*num;
                if(idBoton == ids.get(num)){
                    int[] listaNumeros = new int[] {idBoton-numColumnas,(idBoton-numColumnas)+1,idBoton+1,idBoton+numColumnas,idBoton+numColumnas+1};
                    numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                    comprobar = true;
                }
            }
            for (int i = 2; i<numFilas;i++){//MedioDer
                int num = numColumnas;
                num=(num*i)-1;
                if(idBoton == ids.get(num)){
                    int[] listaNumeros = new int[] {(idBoton-numColumnas)-1,idBoton-numColumnas,idBoton-1,(idBoton+numColumnas)-1,idBoton+numColumnas};
                    numeroArrayFoto = comprobarAdyacentes(listaNumeros);
                    comprobar = true;
                }
            }

            if(!comprobar){
                int[] listaNumeros = new int[] {(idBoton-numColumnas)-1,idBoton-numColumnas,(idBoton-numColumnas)+1,idBoton-1,idBoton+1,(idBoton+numColumnas)-1,idBoton+numColumnas,idBoton+numColumnas+1};
                numeroArrayFoto = comprobarAdyacentes(listaNumeros);
            }
        return numeroArrayFoto;
    }

    public int comprobarAdyacentes(int[] listaNumeros){
        int numeroArrayFoto = 0;
        for (int i = 0; i < hipoNumRandom.size(); i++) {
            for (int y = 0; y < listaNumeros.length; y++) {
                if (listaNumeros[y] == hipoNumRandom.get(i)) {
                    numeroArrayFoto++;
                }
            }
        }
        return numeroArrayFoto;
    }
}