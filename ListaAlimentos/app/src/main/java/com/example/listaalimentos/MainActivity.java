package com.example.listaalimentos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MiAdaptador.OnDatosListener{

    TextView tvNumero;
    EditText etProducto;
    Button btnMenos, btnMas, btnAgnadir;

    ArrayList<Producto> listaProductos = new ArrayList<>();

    ConexionSQLiteHelper conn;

    RecyclerView.LayoutManager lm;
    RecyclerView rv;

    MiAdaptador adaptador;

    ActivityResultLauncher<Intent> resultLauncher;

    int contador = 0;

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumero = findViewById(R.id.tv_numero);
        etProducto = findViewById(R.id.et_producto);
        btnMenos = findViewById(R.id.btn_menos);
        btnMas = findViewById(R.id.btn_mas);
        btnAgnadir = findViewById(R.id.btn_agnadir);

        conn = new ConexionSQLiteHelper(this, "bdAlimentos", null, 1);

        consultarListaProductos();

        adaptador = new MiAdaptador(listaProductos, this, this);

        //crear objeto adaptador
        lm = new LinearLayoutManager(this);
        rv = findViewById(R.id.recyclerview);
        rv.setLayoutManager(lm);
        rv.setAdapter(adaptador);

        tvNumero.setText(contador + "");

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador > 0) {
                    contador--;
                    tvNumero.setText(contador + "");
                }
            }
        });

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
                tvNumero.setText(contador + "");
            }
        });

        btnAgnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Añadir().start();
            }
        });

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            if(data != null) {
                                listaProductos.get(pos).setNombre(data.getStringExtra("NOMBRE"));
                                listaProductos.get(pos).setCantidad(data.getIntExtra("CANTIDAD", -1));

                                consultarListaProductos();
                                adaptador.notifyDataSetChanged();
                            }
                        }
                    }
                });

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = rv.getChildAdapterPosition(view);
                Intent actividad = new Intent(MainActivity.this, Modificar.class);
                actividad.putExtra("NOMBRE", listaProductos.get(pos).getNombre());
                actividad.putExtra("CANTIDAD", listaProductos.get(pos).getCantidad());
                resultLauncher.launch(actividad);
            }
        });

        adaptador.notifyDataSetChanged();

    }
    private void consultarListaProductos() {
        listaProductos.clear();
        SQLiteDatabase db = conn.getReadableDatabase();
        Producto p = null;
        Cursor c = db.rawQuery("SELECT * FROM " + Utilidades.NOMBRE_TABLA, null);

        while(c.moveToNext()) {
            p = new Producto();
            p.setNombre(c.getString(0));
            p.setCantidad(c.getInt(1));
            listaProductos.add(p);
        }

        c.close();
    }

    private void altaProducto() {
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_NOMBRE, etProducto.getText().toString());
        values.put(Utilidades.CAMPO_CANTIDAD, contador);

        db.insert(Utilidades.NOMBRE_TABLA, null, values);
        db.close();

        consultarListaProductos();
    }

    private void borrarProducto(int posicion) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String nombre = listaProductos.get(posicion).getNombre();
        db.execSQL("DELETE FROM " + Utilidades.NOMBRE_TABLA +
                " WHERE " + Utilidades.CAMPO_NOMBRE + " = '" + nombre + "'");
        db.close();
    }

    class Añadir extends Thread {
        @Override
        public void run() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        altaProducto();
                        adaptador.notifyDataSetChanged();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Borrar extends Thread {
        @Override
        public void run() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        borrarProducto(pos);
                        listaProductos.remove(pos);
                        adaptador.notifyDataSetChanged();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDatosBorrar(int posicion) {
        pos = posicion;
        new Borrar().start();
    }
}