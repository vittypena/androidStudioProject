package es.ua.eps.globalexamenandroidev1.ActivityTableLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import es.ua.eps.globalexamenandroidev1.R;

public class Activity_TableLayout extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener{
    //VARIABLES GLOBALES
    TableLayout tableLayout;
    ArrayList<Integer> ids = new ArrayList<>();//Para acceder a los id del table layout
    int numFilas = 0;
    int numColumnas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        //Variables
        tableLayout = findViewById(R.id.tableLayout);
        Button botonLimpiarCeldas = findViewById(R.id.buttonLimpiarCeldas);

        //OnClickBotones
        botonLimpiarCeldas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < ids.size(); i++){
                    Button botonesTodos = findViewById(ids.get(i));
                    botonesTodos.setBackgroundResource(android.R.drawable.btn_default);//Color del boton por defecto
                }
            }
        });

        //Crear el tableLayout
        numFilas = 18;
        numColumnas = 18;
        crearTableLayout(numFilas,numColumnas);
    }

    public void crearTableLayout(int numeroFilas, int numeroColumnas){
        ids.clear();//Para limpiar los ids generados automaticamente cada vez.
        tableLayout.removeAllViews();
        for (int i = 0;i <numeroFilas;i++){
            TableRow fila = new TableRow(getApplicationContext());//Instanciamos
            //Declaramos atributos de la fila
            TableLayout.LayoutParams lpFila = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);//Parametros del layout para la fila
            lpFila.weight=1;//Peso
            fila.setLayoutParams(lpFila);//Los añadimos a la fila
            //Agregamos elementos a la tabla
            for (int y = 0; y < numeroColumnas; y++){//Aqui se puede crear cualquier tipo de view dentro
                Button boton = new Button(getApplicationContext());//Instanciamos
                int id = View.generateViewId();//Guardamos el id generado para luego poder acceder a él mediante el array ids
                boton.setId(id);
                //Este codigo es solo necesario para la hipo
                ids.add(id);
                //Fin de codigo innecesario para demas app
                boton.setOnLongClickListener(this);//Añadirle el evento del activity OnLongClick
                boton.setOnClickListener(this);//Añadirle el evento del activity OnClick
                TableRow.LayoutParams lpButton = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);//Parametros del layout para el boton
                lpButton.weight=1;
                boton.setLayoutParams(lpButton);//Añadimos los parametros al boton
                fila.addView(boton);//Añadimos el boton a la fila
            }
            //Agregamos la fila a el tablelayout
            tableLayout.addView(fila);
        }
    }



    //Eventos OnClik para toda la VIEW, (debemos implementarlos en el view que queramos, programaticamente c directamente en el xml).
    @Override
    public void onClick(View v) {//
        int idCelda = v.getId();
        int[] listaIdsAdyacentes = comprobarCelda(idCelda);//Devuelve los ids adyacentes
        for (int i = 0; i < listaIdsAdyacentes.length; i++) {//Variante que pinta las adyacentes de rojo
            Button botonAdyacente = findViewById(listaIdsAdyacentes[i]);
            botonAdyacente.setBackground(getDrawable(R.color.yellow));
        }
    }

    @Override
    public boolean onLongClick(View v) {//Debe retornar true
        v.setBackground(getDrawable(R.color.red));
        //v.setEnabled(false); Si quisiera deshabilitarlo
        return true;
    }

    //COMPROBAR CELDA Y ADYACENTES
    public int[] comprobarCelda(int idCelda){
        int[] listaIdsAdyacentes = null;
        boolean comprobar = false;
        if (idCelda == ids.get(0))  {//PrimeraFilaIzq
            listaIdsAdyacentes = new int[] {idCelda+1,idCelda+numColumnas,idCelda+numColumnas+1};
            comprobar = true;
        }
        if (idCelda == ids.get(numColumnas-1))  {//PrimeraFilaDer
            listaIdsAdyacentes = new int[] {idCelda-1,idCelda+numColumnas-1,idCelda+numColumnas};
            comprobar = true;
        }
        if (idCelda == ids.get(ids.size()-numColumnas)) {//UltimaFilaIzq
            listaIdsAdyacentes = new int[] {idCelda-numColumnas,(idCelda-numColumnas)+1,idCelda+1};
            comprobar = true;
        }
        if (idCelda == ids.get(ids.size()-1)) {//UltimaFilaDer
            listaIdsAdyacentes = new int[] {(idCelda-numColumnas)-1,idCelda-numColumnas,idCelda-1};
            comprobar = true;
        }
        for (int i = 1; i <= (numColumnas-2);i++){//PrimeraFilaMedio
            if(idCelda == ids.get(i)){
                listaIdsAdyacentes = new int[] {idCelda-1,idCelda+1,(idCelda+numColumnas)-1,idCelda+numColumnas,idCelda+numColumnas+1};
                comprobar = true;
            }
        }
        for (int i = (ids.size()-numColumnas)+1; i <= (ids.size()-2);i++){//UltimaFilaMedio
            if (idCelda == ids.get(i)){
                listaIdsAdyacentes = new int[] {(idCelda-numColumnas)-1,idCelda-numColumnas,(idCelda-numColumnas)+1,idCelda-1,idCelda+1};
                comprobar = true;
            }
        }
        for (int i = 1; i<(numFilas-1);i++){//MedioIzq
            int num = numColumnas;
            num=i*num;
            if(idCelda == ids.get(num)){
                listaIdsAdyacentes = new int[] {idCelda-numColumnas,(idCelda-numColumnas)+1,idCelda+1,idCelda+numColumnas,idCelda+numColumnas+1};
                comprobar = true;
            }
        }
        for (int i = 2; i<numFilas;i++){//MedioDer
            int num = numColumnas;
            num=(num*i)-1;
            if(idCelda == ids.get(num)){
                listaIdsAdyacentes = new int[] {(idCelda-numColumnas)-1,idCelda-numColumnas,idCelda-1,(idCelda+numColumnas)-1,idCelda+numColumnas};
                comprobar = true;
            }
        }
        if(!comprobar){
            listaIdsAdyacentes = new int[] {(idCelda-numColumnas)-1,idCelda-numColumnas,(idCelda-numColumnas)+1,idCelda-1,idCelda+1,(idCelda+numColumnas)-1,idCelda+numColumnas,idCelda+numColumnas+1};
        }
        return listaIdsAdyacentes;
    }
}