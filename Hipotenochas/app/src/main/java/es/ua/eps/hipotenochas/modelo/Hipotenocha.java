package es.ua.eps.hipotenochas.modelo;

import java.io.Serializable;
//Para el layout del adapter item
//Implementa serializable
//Se añade al gestor creandola
public class Hipotenocha implements Serializable {
    private String name;
    private int image;

    public Hipotenocha(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
