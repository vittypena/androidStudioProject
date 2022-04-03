package es.ua.eps.hipotenochas.modelo;

import java.util.ArrayList;
import java.util.List;

import es.ua.eps.hipotenochas.R;

//Declaramos
//Introducimos datos con el modelo en el data(que es el que contiene el array)
//Retornamos
public class Data {
    public static List<Hipotenocha> getHipotenochaList(){
        List<Hipotenocha> hipotenochaList= new ArrayList<>();
        //CUIDADO CON NO CAMBIAR EL obj1 (camuflada por ejemplo) en todas las lineas, set y add
        Hipotenocha camuflada = new Hipotenocha();
        camuflada.setName("Camuflada");
        camuflada.setImage(R.mipmap.ic_menuaction_camuflada);
        hipotenochaList.add(camuflada);

        Hipotenocha espaniola = new Hipotenocha();
        espaniola.setName("Española");
        espaniola.setImage(R.mipmap.ic_menuaction_espaniola);
        hipotenochaList.add(espaniola);

        Hipotenocha presumida = new Hipotenocha();
        presumida.setName("Presumida");
        presumida.setImage(R.mipmap.ic_menuaction_presumida);
        hipotenochaList.add(presumida);

        Hipotenocha nocturna = new Hipotenocha();
        nocturna.setName("Nocturna");
        nocturna.setImage(R.mipmap.ic_menuaction_nocturna);
        hipotenochaList.add(nocturna);

        return hipotenochaList;
    }
}
