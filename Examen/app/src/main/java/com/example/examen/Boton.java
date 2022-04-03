package com.example.examen;

import android.content.Context;

public class Boton extends androidx.appcompat.widget.AppCompatButton{
    boolean hayColor;
    boolean activado;
    //int numAdyacentes;

    public Boton(Context context) {
        super(context);
        this.hayColor = false;
        this.activado = true;
        //this.numAdyacentes = 0;
    }
}
