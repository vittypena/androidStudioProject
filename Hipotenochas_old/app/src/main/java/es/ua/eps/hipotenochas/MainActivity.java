package es.ua.eps.hipotenochas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements DificultadDialogFragment.DificultadDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //...........................MENU...........................//
        //Añadir la Toolbar en el main, previamente puesta en el xml y deshabilitar la barra nativa (elegir import android x)
        //Añadir icono con setOverflowIcon previamente introducido como image asset en legacy icons del mipmap
        //Añadir el menu.xml con sus items
        //Metodo onCreateOptionsMenu e incluir el inflater
        //Metodo onOptionsItemSelected para indicar acciones
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setOverflowIcon(getDrawable(R.mipmap.ic_menuactionhipo));
        myToolbar.setNavigationIcon(R.mipmap.ic_menuaction2);
        //...........................MENU...........................//

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hipotenochas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){//El return true es necesario
            case R.id.menu_intrucciones:
                //Exhibir el fragmento instruccionesdialogfragment
                DialogFragment fragmentoDialogo = new IntruccionesDialogFragment();
                fragmentoDialogo.show(getSupportFragmentManager(),"intrucciones");
                return true;
            case R.id.menu_nuevo_juego:
                //TODO acciones nuevo juego
                return true;
            case R.id.menu_configura_el_juego:
                DialogFragment fragmentoDialogoRadioButtons = new DificultadDialogFragment();
                fragmentoDialogoRadioButtons.show(getSupportFragmentManager(),"intrucciones");
                return true;
            case R.id.menu_personaje:
                //todo dialogo spinner
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onDialogPositiveClick(String seleccion){
        if(seleccion!=null)
        Toast.makeText(getApplicationContext(), seleccion, Toast.LENGTH_SHORT).show();
        //todo acciones con la dificultad
    }
}