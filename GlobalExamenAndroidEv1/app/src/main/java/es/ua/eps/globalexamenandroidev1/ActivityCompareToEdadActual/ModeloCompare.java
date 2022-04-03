package es.ua.eps.globalexamenandroidev1.ActivityCompareToEdadActual;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class ModeloCompare implements Serializable {
    private String name;
    private GregorianCalendar gregorianCalendar;//Ruta de la imagen

    public ModeloCompare(String nombre, GregorianCalendar gc) {
        this.name = nombre;
        this.gregorianCalendar = gc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getGregorianCalendar() {
        return gregorianCalendar;
    }

    public void setGregorianCalendar(GregorianCalendar gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
    }
}
