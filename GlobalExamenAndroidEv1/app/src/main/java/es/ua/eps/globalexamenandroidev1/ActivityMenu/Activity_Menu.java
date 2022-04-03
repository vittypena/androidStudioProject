package es.ua.eps.globalexamenandroidev1.ActivityMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import es.ua.eps.globalexamenandroidev1.R;

public class Activity_Menu extends AppCompatActivity {
/*
    IMPORTANTE -> ESTE PAQUETE CUENTA TB CON LOS LAYOUT: (activity_menu.xml, menu_activity_menu.xml).
                  En el manifest hay que cambiar el stilo al activity en concreto o si no es solo a ese, directamente en style aplicamos un noaction bar a todos
                  android:theme="@style/Theme.GlobalExamenAndroidEv1.NoActionBar" --> Solo uno en el manifest
                  "Theme.NOMBREPROYECTO" parent="Theme.AppCompat.Light.NoActionBar" --> A todos en el values-themes
 */

    //VARIABLES GLOBALES
    Menu menuItem;//Para poder acceder el menu item desde otra parte
    String elegir = "Principiante";//Variable para probar el switch

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Añadir toolbar
            Toolbar myToolbar;
            myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            myToolbar.setOverflowIcon(getDrawable(R.mipmap.ic_menu_icon_toolbar));//Este aparece donde los tres puntitos, si comentamos esta linea tenemos los tres puntos
            myToolbar.setNavigationIcon(R.mipmap.ic_dialog_fragment_spinner_people1);//Este aparece a la izq como icono

        //Variables
        Switch switchButtonMenu = findViewById(R.id.switchButtonMenu);//Switch button para habilitar/deshabilitar item menu
        ToggleButton toggleButton = findViewById(R.id.toggleButtonMenu);//Toogle button para habilitar/deshabilitar item menu

        //Acciones con los botones para tener ejemplos de ToggleButton y Switch Button
        switchButtonMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){//Esta enabled
                    Toast.makeText(getApplicationContext(), "Menu DEShabilitado Switch", Toast.LENGTH_SHORT).show();
                    menuItem.getItem(0).setEnabled(false);//Deshabilitamos el menu en esta posicion
                    menuItem.getItem(1).setEnabled(false);
                    menuItem.getItem(2).setEnabled(false);
                }else{
                    Toast.makeText(getApplicationContext(), "Menu habilitado Switch", Toast.LENGTH_SHORT).show();
                    menuItem.getItem(0).setEnabled(true);
                    menuItem.getItem(1).setEnabled(true);
                    menuItem.getItem(2).setEnabled(true);
                }
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){//Esta enabled
                    Toast.makeText(getApplicationContext(), "Menu DEShabilitado ToggleButton", Toast.LENGTH_SHORT).show();
                    menuItem.getItem(0).setEnabled(false);//Deshabilitamos el menu en esta posicion
                    menuItem.getItem(1).setEnabled(false);
                    menuItem.getItem(2).setEnabled(false);
                }else{
                    Toast.makeText(getApplicationContext(), "Menu habilitado ToggleButton", Toast.LENGTH_SHORT).show();
                    menuItem.getItem(0).setEnabled(true);
                    menuItem.getItem(1).setEnabled(true);
                    menuItem.getItem(2).setEnabled(true);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_menu, menu);//Aqui ponemos el xml del menu
        menuItem = menu;//Gracias a esta variable podremos acceder a tocar el menu desde fuera
        //TODO revisar que mas cosas podemos hacer con menuItem y con el item de onOptionsItemSelected
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){//El return true es necesario para el menu
            case R.id.menuHola:
                Toast.makeText(getApplicationContext(), "Hola Menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuAdios:
                Toast.makeText(getApplicationContext(), "Adios Menu", Toast.LENGTH_SHORT).show();
                switch (elegir){//Switch para tener un switch en el menu de prueba
                    case "Principiante":
                        Toast.makeText(getApplicationContext(), "Variable String Principante", Toast.LENGTH_SHORT).show();
                        break;
                    case "Amateur":
                        Toast.makeText(getApplicationContext(), "Variable String Intermedio", Toast.LENGTH_SHORT).show();
                        break;
                    case "Avanzado":
                        Toast.makeText(getApplicationContext(), "Variable String Experto", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Variable String no coincide con ninguno", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.menuImagen:
                Toast.makeText(getApplicationContext(), "Imagen Menu", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}