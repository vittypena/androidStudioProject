package es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner;

import java.util.ArrayList;
import java.util.List;

import es.ua.eps.globalexamenandroidev1.R;

public class Data {
//TODO HACER UN MODELO MÁS ELABORADO
    public static List<Modelo> getModeloList(){
        List<Modelo> modeloList= new ArrayList<>();//Lista

            //Añadir objetos a la lista
        Modelo people1 = new Modelo();
        people1.setName("Yolo");
        people1.setImage(R.mipmap.ic_dialog_fragment_spinner_people1);
        modeloList.add(people1);

        Modelo people2 = new Modelo();
        people2.setName("TeamGeniosUnidos");
        people2.setImage(R.mipmap.ic_dialog_fragment_spinner_people2);
        modeloList.add(people2);

        Modelo people3 = new Modelo();
        people3.setName("Ninja");
        people3.setImage(R.mipmap.ic_dialog_fragment_spinner_people3);
        modeloList.add(people3);

        Modelo people4 = new Modelo();
        people4.setName("HeroWoman");
        people4.setImage(R.mipmap.ic_dialog_fragment_spinner_people4);
        modeloList.add(people4);

        return modeloList;
    }
}
//CUIDADO CON NO CAMBIAR EL obj1 (camuflada por ejemplo) en todas las lineas, set y add