package es.ua.eps.jarretas;

public class Contacto {

    public int id;
    public int imagen;
    public String Nombre;
    public String Des;

    public Contacto(int id, int imagen, String nombre, String des) {
        this.id = id;
        this.imagen = imagen;
        Nombre = nombre;
        Des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

}
