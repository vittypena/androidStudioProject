package es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner;

import java.io.Serializable;

public class Modelo implements Serializable {
    private String name;
    private int image;//Ruta de la imagen

    public Modelo() {}

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
