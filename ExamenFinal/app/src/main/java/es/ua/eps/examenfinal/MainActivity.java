package es.ua.eps.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogFragment_TimePicker.TimePickerDialogListener, DialogFragment_TimePickerSalida.TimePickerDialogListener{
    TextView tvHoraEntrada, tvHoraSalida, tvConsultar;
    int intHoraEntrada, intHoraSalida, intMinutoEntrada, intMinutoSalida;
    ExamenDBHelper usuariosSQLitHelper;
    String nacionalidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btEntrada = findViewById(R.id.btEntrada);
        Button btSalida = findViewById(R.id.btSalida);
        Button btConsultar = findViewById(R.id.btConsultar);
        Button btAltas = findViewById(R.id.btAltas);
        Button btNacionalidad = findViewById(R.id.btNacionalidad);
        tvHoraEntrada = findViewById(R.id.tvHoraEntrada);
        tvHoraSalida = findViewById(R.id.tvHoraSalida);
        tvConsultar = findViewById(R.id.tvConsultar);
        EditText etNombre = findViewById(R.id.etNombre);


        usuariosSQLitHelper = new ExamenDBHelper(this,"Asistentes", null, 1);
        SQLiteDatabase db = usuariosSQLitHelper.getWritableDatabase();

        btEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoTimePicker = new DialogFragment_TimePicker();
                fragmentoTimePicker.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        btSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoTimePicker = new DialogFragment_TimePickerSalida();
                fragmentoTimePicker.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        btAltas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = usuariosSQLitHelper.getWritableDatabase();
                String nombre = etNombre.getText().toString();

                ContentValues cv = new ContentValues();
                cv.put("nombre", nombre);
                cv.put("nacionalidad", nacionalidad);
                cv.put("horaEntrada", intHoraEntrada);
                cv.put("minutosEntrada", intMinutoEntrada);
                cv.put("horaSalida", intHoraSalida);
                cv.put("minutosSalida", intMinutoSalida);
                db.insert("Asistentes", null, cv);

                db.close();
            }
        });

        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });

        btNacionalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    tvConsultar.setText("");
                    SQLiteDatabase dbr = usuariosSQLitHelper.getReadableDatabase();
                    Asistentes asistentes = null;
                    Cursor c = dbr.rawQuery("SELECT * FROM Asistentes", null);
                    while(c.moveToNext()) {
                        if (c.getString(1).equals(nacionalidad)) {
                            asistentes = new Asistentes();
                            asistentes.setNombre(c.getString(0));
                            asistentes.setNacionalidad(c.getString(1));
                            asistentes.setHoraEntrada(c.getInt(2));
                            asistentes.setMinutoEntrada(c.getInt(3));
                            asistentes.setHoraSalida(c.getInt(4));
                            asistentes.setMinutoSalida(c.getInt(5));
                            int minEntradao;
                            int minSalidao;
                            int tiempoEstancia;
                            if(asistentes.getHoraEntrada() > asistentes.getHoraSalida()){
                                tiempoEstancia = 0;
                                if(asistentes.getMinutoEntrada()>asistentes.getMinutoSalida()){
                                    tiempoEstancia = -1;
                                }
                                tiempoEstancia =asistentes.getHoraEntrada() + 24 +(asistentes.getHoraSalida()-asistentes.getHoraEntrada());
                            }else{
                                tiempoEstancia = 0;
                                if(asistentes.getMinutoEntrada()>asistentes.getMinutoSalida()){
                                    tiempoEstancia = -1;
                                }
                                tiempoEstancia += asistentes.getHoraSalida() - asistentes.getHoraEntrada();
                            }

                            tvConsultar.append(asistentes.getNombre() + " Nacionalidad: " + nacionalidad + " Tiempo estancia: " + tiempoEstancia  + "\n");
                        }
                    }
                    c.close();
                    dbr.close();

            }
        });
    }

    public void onRadioButtonGlobal(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rbEspaniola:
                if (checked)
                    nacionalidad = "Española";
                break;
            case R.id.rbFrancesa:
                if (checked)
                    nacionalidad = "Francesa";
                break;
            case R.id.rbItaliana:
                if (checked)
                    nacionalidad = "Italiana";
                break;
        }
    }

    @Override
    public void onDialogTimePicker(int hour, int minute) {
        intHoraEntrada = hour;
        intMinutoEntrada = minute;
        tvHoraEntrada.setText(hour + ":" + minute);
    }

    @Override
    public void onDialogTimePickerSalida(int hour, int minute) {
        intHoraSalida = hour;
        intMinutoSalida = minute;
        tvHoraSalida.setText(hour + ":" + minute);
    }

    public void consultar(){
        tvConsultar.setText("");
        SQLiteDatabase dbr = usuariosSQLitHelper.getReadableDatabase();
        Asistentes asistentes = null;
        Cursor c = dbr.rawQuery("SELECT * FROM Asistentes", null);
        while(c.moveToNext()) {
            asistentes = new Asistentes();
            asistentes.setNombre(c.getString(0));
            asistentes.setNacionalidad(c.getString(1));
            asistentes.setHoraEntrada(c.getInt(2));
            asistentes.setMinutoEntrada(c.getInt(3));
            asistentes.setHoraSalida(c.getInt(4));
            asistentes.setMinutoSalida(c.getInt(5));
            tvConsultar.append(asistentes.getNombre() + " Nacionalidad: " + nacionalidad + " Hora Entrada: " + asistentes.getHoraEntrada() + ":" + asistentes.getMinutoEntrada() + " Hora Salida:" + asistentes.getHoraSalida() + ":" + asistentes.getMinutoSalida() + "\n");
        }
        c.close();
        dbr.close();
    }
}