package es.ua.eps.recumarzo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MiAdaptador.OnDatosListener{

    ArrayList<Notas> listaProductos = new ArrayList<>();
    ConexionSQLiteHelper conn;
    RecyclerView.LayoutManager lm;
    RecyclerView rv;
    MiAdaptador adaptador;

    Menu menuItem;


    int pos = 0;
    ActivityResultLauncher<Intent> resultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Añadir toolbar
        Toolbar myToolbar;
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        conn = new ConexionSQLiteHelper(this, "bdAlimentos", null, 1);
        adaptador = new MiAdaptador(listaProductos, this, this);

        lm = new LinearLayoutManager(this);
        rv = findViewById(R.id.rvMain);
        rv.setLayoutManager(lm);
        rv.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_menu, menu);
        menuItem = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){//El return true es necesario para el menu
            case R.id.menuAlta:
                Intent activityAlta = new Intent(MainActivity.this, ActivityAlta.class);
                startActivity(activityAlta);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDatosBorrar(int posicion) {

    }
}