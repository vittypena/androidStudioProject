package es.ua.eps.jarretas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ListView ListViewContacto;
    List<Contacto> lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListViewContacto = findViewById(R.id.ListViewContacto);

        //Con este customadapter metemos usamos el getdata para meter los contactos en el listview
        CustomAdapter adapter = new CustomAdapter(this, GetData());
        ListViewContacto.setAdapter(adapter);

        //Listener
        ListViewContacto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contacto c = lst.get((int)adapter.getItemId(i));
                Toast.makeText(getBaseContext(), c.Nombre, Toast.LENGTH_SHORT).show();

                Intent i2 = new Intent(MainActivity.this, SecundaryActivity.class);
                i2.putExtra("nombre", c.Nombre);
                startActivity(i2);

            }
        });
    }

    private List<Contacto> GetData()
    {
        lst = new ArrayList<>();
        lst.add(new Contacto(1, R.drawable.contacto, "Juan", "478579348964785"));
        lst.add(new Contacto(2, R.drawable.contacto_rosa, "Maria", "478579348964785"));
        lst.add(new Contacto(3, R.drawable.contacto, "Manuel", "478579348964785"));
        lst.add(new Contacto(4, R.drawable.contacto_rosa, "Maria", "478579348964785"));
        lst.add(new Contacto(5, R.drawable.contacto, "David", "478579348964785"));
        lst.add(new Contacto(6, R.drawable.contacto_rosa, "Goizeder", "478579348964785"));
        lst.add(new Contacto(7, R.drawable.contacto, "Marcos", "478579348964785"));
        lst.add(new Contacto(8, R.drawable.contacto_rosa, "Alejandro", "478579348964785"));
        lst.add(new Contacto(9, R.drawable.contacto, "Ines", "478579348964785"));
        lst.add(new Contacto(10, R.drawable.contacto_rosa, "Irene", "478579348964785"));
        lst.add(new Contacto(11, R.drawable.contacto, "Adrian", "478579348964785"));
        lst.add(new Contacto(12, R.drawable.contacto, "Carlos", "478579348964785"));
        lst.add(new Contacto(13, R.drawable.contacto_rosa, "María Antonia", "478579348964785"));
        lst.add(new Contacto(14, R.drawable.contacto, "Juan Carlos", "478579348964785"));
        lst.add(new Contacto(15, R.drawable.contacto_rosa, "María Jose", "478579348964785"));

        return lst;
    }
}