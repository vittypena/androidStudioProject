package es.ua.eps.globalexamenandroidev1.ActivityCambioIdioma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

import es.ua.eps.globalexamenandroidev1.R;

public class Activity_CambioIdioma extends AppCompatActivity{
    TextView tvSaludo;
    TextView tvDespedida;
    TextView tvIdioma;
    TextView tvFinal;
    String idioma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_idioma);

        //Variables iniciales
        tvSaludo = findViewById(R.id.textViewSaludo);
        tvDespedida = findViewById(R.id.textViewDespedida);
        tvIdioma = findViewById(R.id.textViewIdioma);
        tvFinal = findViewById(R.id.textViewFin);

    }

    public void onRadioButtonCambioIdioma(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButtonEspaniol:
                if (checked)
                    idioma = "SPANISH";
                    cambiarIdioma(idioma);
                    actualizarView();
                break;
            case R.id.radioButtonIngles:
                if (checked){
                    idioma = "ENGLISH";
                    cambiarIdioma(idioma);
                    actualizarView();
                }
                break;
            case R.id.radioButtonFrancesMal:
                if (checked){
                    idioma = "FRENCH";
                    cambiarIdioma(idioma);
                    actualizarView();
                }
                break;
        }
    }


    //Metodos para cambiar el idioma
    public void cambiarIdioma(String idioma) {
        String language = "";
        switch (idioma) {
            case "SPANISH":
                language = "es";
                break;
            case "ENGLISH":
                language = "en";
                break;
            case "FRENCH":
                language = "fr";
                break;
        }
        Locale localizacion = new Locale(language);

        Locale.setDefault(localizacion);
        Configuration config = new Configuration();
        config.locale = localizacion;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        actualizarView();
    }

    public void actualizarView() {
        tvSaludo.setText(R.string.hola);

        tvDespedida.setText(R.string.adios);

        tvIdioma.setText(R.string.idioma);

        tvFinal.setText(R.string.fin);
    }

}