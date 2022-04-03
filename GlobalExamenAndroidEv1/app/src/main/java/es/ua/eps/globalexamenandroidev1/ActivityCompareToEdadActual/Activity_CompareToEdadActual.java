package es.ua.eps.globalexamenandroidev1.ActivityCompareToEdadActual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import es.ua.eps.globalexamenandroidev1.DialogFragmentDatePicker.DialogFragment_DatePicker;
import es.ua.eps.globalexamenandroidev1.DialogFragmentRadioButton.DialogFragment_RadioButton;
import es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner.Data;
import es.ua.eps.globalexamenandroidev1.R;

public class Activity_CompareToEdadActual extends AppCompatActivity implements DialogFragment_DatePicker.DatePickerDialogListener{
    int anio;
    int mes;
    int dia;
    Spinner spinnerCompare;
    SpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_to_edad_actual);

        //Variables
        EditText editTextNombre = findViewById(R.id.editTextTextPersonName);
        Button buttonEdadCompare = findViewById(R.id.buttonEdad);
        Button buttonAñadirLista = findViewById(R.id.buttonAñadirList);

        //SPINNERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
        spinnerCompare = findViewById(R.id.spinnerCompare);

        adapter = new SpinnerAdapterCompare(Activity_CompareToEdadActual.this, DataCompare.getListaModeloCompare());
        //adapter = new SpinnerAdapter(getActivity(), Data.getHipotenochaList());
        spinnerCompare.setAdapter(adapter);
        //Establecemos que pasara al seleccionar el item del spinner
        spinnerCompare.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int mesGregorian = 0;
                if(DataCompare.getListaModeloCompare().get(position).getGregorianCalendar().get(Calendar.MONTH) == 0){//ESTE IFF SI TRABAJAMOS CON DATOS DESDE CALENDAR HAY QUE TENER CUIDADO CON EL 12
                    mesGregorian = 12;
                }else{
                    mesGregorian = DataCompare.getListaModeloCompare().get(position).getGregorianCalendar().get(Calendar.MONTH);
                }
                String edad = getAge(DataCompare.getListaModeloCompare().get(position).getGregorianCalendar().get(Calendar.YEAR), mesGregorian,DataCompare.getListaModeloCompare().get(position).getGregorianCalendar().get(Calendar.DAY_OF_MONTH));
                if(Integer.parseInt(edad) >= 18){
                    Toast.makeText(Activity_CompareToEdadActual.this, "LOCO QUE ERES MAYOR DE EDAD YAAA", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Activity_CompareToEdadActual.this, "VENGA CHAVAL A TU PUTA CASA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//**********************************

        buttonEdadCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoDatePickerCompare= new DialogFragment_DatePicker();
                fragmentoDatePickerCompare.show(getSupportFragmentManager(),"DattePickerCompare");
            }
        });

        buttonAñadirLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
                gc.set(Calendar.YEAR, anio);
                gc.set(Calendar.MONTH, mes);
                gc.set(Calendar.DAY_OF_MONTH, dia);
                ModeloCompare obj = new ModeloCompare(String.valueOf(editTextNombre.getText()),gc);
                DataCompare.añadirModeloCompare(obj);
                spinnerCompare.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onDialogDatePicker(int anyo, int mes, int dia) {
        this.anio = anyo;
        this.mes = mes;
        this.dia = dia;
        Toast.makeText(getApplicationContext(), "La fecha elegida es: " + dia + "/" + mes + "/" + anyo, Toast.LENGTH_SHORT).show();
    }

    //Comparar con radioButton
    public void onRadioButtonComparar(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButtonCompararNombre:
                if (checked)
                    Collections.sort(DataCompare.getListaModeloCompare(), new Comparator<ModeloCompare>() {
                        @Override
                        public int compare(ModeloCompare persona, ModeloCompare t1) {
                            return persona.getName().compareTo(t1.getName());
                        }
                    });
                    spinnerCompare.setAdapter(adapter);
                    break;
            case R.id.radioButtonCompararEdad:
                if (checked){
                    Collections.sort(DataCompare.getListaModeloCompare(), new Comparator<ModeloCompare>() {
                        @Override
                        public int compare(ModeloCompare persona, ModeloCompare t1) {
                            return persona.getGregorianCalendar().compareTo(t1.getGregorianCalendar());
                        }
                    });
                    spinnerCompare.setAdapter(adapter);
                }
                break;
        }
    }

    //Metodo Edad Actual
    public static String getAge(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        /*
        LocalDate date2 = LocalDate.of(2018, 10, 30);
        LocalDate ahora = LocalDate.now();
         */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            LocalDate birthdate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
            Period p = Period.between(birthdate, today);
            return p.getYears()+"";
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

            return mYear+"";
        }
    }
}