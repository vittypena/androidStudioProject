package es.ua.eps.global;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Owner;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import es.ua.eps.global.modelo.Adaptador;
import es.ua.eps.global.modelo.data;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.OnFechaSeleccionada {
    TextView fecha;
    TextView nombre;
    EditText nombreEdit;
    String fechaString;
    ListView lista;
    Adaptador adaptador;
    ArrayList<data> arrayData;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayData = new ArrayList<>();
        Button botonAñadir = findViewById(R.id.button);
        Button botonDatePicker = findViewById(R.id.button2);
        nombre = findViewById(R.id.textView);
        fecha = findViewById(R.id.fechaNacimiento);
        nombreEdit = findViewById(R.id.edittext);
        lista = findViewById(R.id.listview);
        adaptador = new Adaptador(this, arrayData);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), arrayData.get(position).getNombre() + " " + getAge(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) ,calendar.get(Calendar.DAY_OF_MONTH)), Toast.LENGTH_SHORT).show();
                adaptador.notifyDataSetChanged();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayData.remove(arrayData.get(position));
                lista.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();
                return true;
            }
        });

        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayData.add(new data(nombreEdit.getText()+"",fechaString, calendar));
                lista.setAdapter(adaptador);
                fecha.setText(fechaString);
                nombre.setText(nombreEdit.getText());
            }
        });

        botonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        //android:onClick="onRadioButtonClicked" en el xml como atributo del radiobutton
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    Collections.sort(arrayData, new Comparator<data>() {
                        @Override
                        public int compare(data persona, data t1) {
                            return persona.getNombre().compareTo(t1.getNombre());
                        }
                    });
                    lista.setAdapter(adaptador);
                    break;
            case R.id.radioButton2:
                if (checked)
                    Collections.sort(arrayData, new Comparator<data>() {
                        @Override
                        public int compare(data persona, data t1) {
                            return persona.getCalendar().compareTo(t1.getCalendar());
                        }
                    });
                    lista.setAdapter(adaptador);
                    break;
        }
    }

    @Override
    public void onResultadoFecha(int anyo, int mes, int dia) {
        fechaString = dia+"/"+(mes+1)+"/"+anyo;//Para el string
        calendar = Calendar.getInstance();//Para ordenar por fecha
        calendar.set(Calendar.YEAR, anyo);
        calendar.set(Calendar.MONTH, mes+1);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
    }

    public static String getAge(int yearOfBirth, int monthOfBirth, int dayOfBirth) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            LocalDate birthdate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
            Period p = Period.between(birthdate, today);
            return p.getYears() + " años, ";
        }else{
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);

            Calendar c2 = new GregorianCalendar(yearOfBirth,monthOfBirth,dayOfBirth);
            Calendar c1 =  new GregorianCalendar(year, month, day);

            long end = c2.getTimeInMillis();
            long start = c1.getTimeInMillis();

            long milliseconds = TimeUnit.MILLISECONDS.toMillis(Math.abs(end - start));

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(milliseconds);
            int mYear = c.get(Calendar.YEAR)-1970;
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH)-1;

            return mYear + "Años";
        }
    }
}