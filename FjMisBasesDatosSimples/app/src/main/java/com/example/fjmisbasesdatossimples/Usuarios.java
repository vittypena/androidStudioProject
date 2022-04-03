package com.example.fjmisbasesdatossimples;

public class Usuarios {
    int clave;
    int codigo;
    String nombre;

    public Usuarios() {
    }

    public Usuarios(int clave, int codigo, String nombre) {
        this.clave = clave;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
