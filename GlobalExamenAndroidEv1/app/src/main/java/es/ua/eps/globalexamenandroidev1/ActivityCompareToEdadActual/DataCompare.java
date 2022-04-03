package es.ua.eps.globalexamenandroidev1.ActivityCompareToEdadActual;

import java.util.ArrayList;
import java.util.List;

public class DataCompare {
    private static List<ModeloCompare> listaModeloCompare = new ArrayList<>();

    public static void añadirModeloCompare(ModeloCompare objeto){
        listaModeloCompare.add(objeto);
    }

    public static List<ModeloCompare> getListaModeloCompare() {
        return listaModeloCompare;
    }
}
