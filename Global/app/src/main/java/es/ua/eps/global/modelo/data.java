package es.ua.eps.global.modelo;

import java.util.Calendar;
import java.util.Date;

public class data {
    private String nombre;
    private String fecha;
    private Calendar calendar;

    public data(String nombre, String fecha, Calendar calendar) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.calendar = calendar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public Calendar getCalendar() {
        return calendar;
    }
}
