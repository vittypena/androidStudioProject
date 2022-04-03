package com.example.fjmisbasesdatossimples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etNombre, etCodigo;
    TextView tvConsulta;
    Button btInsertar, btActualizar, btEliminar, btConsultar;
    UsuariosSQLiteHelper usuariosSQLitHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = findViewById(R.id.etNombre);
        etCodigo = findViewById(R.id.etCodigo);
        tvConsulta = findViewById(R.id.tvConsulta);
        btInsertar = findViewById(R.id.button_insertar);
        btActualizar = findViewById(R.id.button_actualizar);
        btEliminar = findViewById(R.id.button_eliminar);
        btConsultar = findViewById(R.id.button_consultar);

        usuariosSQLitHelper = new UsuariosSQLiteHelper(this,"dbUsuarios", null, 6);//Aqui se actualiza la bd si añadimos una version superior
        SQLiteDatabase db = usuariosSQLitHelper.getWritableDatabase();
        //Esto lo comento ya que si no siempre ira metiendo los iniciales
        /*
        for(int i = 1; i<=5; i++){
            int codigo = i;
            String nombre = "Usuario " + i;

            //Insertamos los datos en la tabla Usuarios
            db.execSQL("Insert into Usuarios(codigo, nombre) VALUES(" + codigo + ", '"  + nombre + "')"); // Las comillas simples son porque nombre es un string y asi las lee, ya que los strings van con comillas simples
        }
         */

        db.close();

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = usuariosSQLitHelper.getWritableDatabase();
                String nombre = etNombre.getText().toString();
                int codigo = Integer.parseInt(etCodigo.getText().toString());

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("Insert into Usuarios(codigo, nombre) VALUES(" + codigo + " , '" +nombre +"')");

                //Otra forma mas actual
                /*
                ContentValues cv = new ContentValues();
                cv.put("codigo", codigo);
                cv.put("nombre", nombre);
                db.insert("usuarios", null, cv);
                 */

                db.close();
                consultar();
            }
        });

        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = usuariosSQLitHelper.getWritableDatabase();
                String nombre = etNombre.getText().toString();
                int codigo = Integer.parseInt(etCodigo.getText().toString());
                //Metodo sql
                db.execSQL("UPDATE Usuarios SET nombre = '" + nombre + "' WHERE codigo = " + codigo);

                //Metodo actual
                /*
                ContentValues cv = new ContentValues();
                cv.put("nombre", nombre);
                db.update("usuarios", cv, "codigo = " + codigo, null);
                 */

                db.close();
                consultar();
            }
        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = usuariosSQLitHelper.getWritableDatabase();
                String codigo = etCodigo.getText().toString();

                //Metodo SQL
                /*
                db.execSQL("DELETE FROM Usuarios WHERE codigo = " + codigo);
                 */
                //Metodo actual
                db.delete("Usuarios","codigo = " + codigo, null);

                db.close();
                consultar();
            }
        });

        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    consultar();
            }
        });

    }

    public void consultar(){
        tvConsulta.setText("");
        SQLiteDatabase dbr = usuariosSQLitHelper.getReadableDatabase();
        Usuarios u = null;
        Cursor c = dbr.rawQuery("SELECT * FROM Usuarios", null);
        while(c.moveToNext()) {
            u = new Usuarios();
            u.setClave(c.getInt(0));
            u.setCodigo(c.getInt(1));
            u.setNombre(c.getString(2));
            tvConsulta.append(u.getClave() + " codigo " + u.getCodigo() + "   " + u.getNombre() + "\n"); //Le añade los datos al textview
        }
        c.close();
        dbr.close();
    }
}
