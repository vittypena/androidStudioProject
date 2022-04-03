package es.ua.eps.recumarzo;

public class Notas {
    int id;
    String categoria;
    String titulo;
    String descipcion;

    public Notas(){

    }

    public Notas(int id, String categoria, String titulo, String descipcion) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descipcion = descipcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }
}
