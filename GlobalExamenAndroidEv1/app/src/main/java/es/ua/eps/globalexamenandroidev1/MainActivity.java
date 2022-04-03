package es.ua.eps.globalexamenandroidev1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import es.ua.eps.globalexamenandroidev1.ActivityCambioIdioma.Activity_CambioIdioma;
import es.ua.eps.globalexamenandroidev1.ActivityCompareToEdadActual.Activity_CompareToEdadActual;
import es.ua.eps.globalexamenandroidev1.ActivityGlobal.Activity_Global;
import es.ua.eps.globalexamenandroidev1.ActivityMenu.Activity_Menu;
import es.ua.eps.globalexamenandroidev1.ActivityPasoParametrosRetornar.Activity_PasoParametros;
import es.ua.eps.globalexamenandroidev1.ActivityTableLayout.Activity_TableLayout;
import es.ua.eps.globalexamenandroidev1.DialogFragmentCheckBox.DialogFragment_CheckBox;
import es.ua.eps.globalexamenandroidev1.DialogFragmentDatePicker.DialogFragment_DatePicker;
import es.ua.eps.globalexamenandroidev1.DialogFragmentList.DialogFragment_List;
import es.ua.eps.globalexamenandroidev1.DialogFragmentPersonalizado.DialogFragment_Personalizado;
import es.ua.eps.globalexamenandroidev1.DialogFragmentRadioButton.DialogFragment_RadioButton;
import es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner.DialogFragment_Spinner;
import es.ua.eps.globalexamenandroidev1.DialogFragmentTexto.DialogFragment_Texto;
import es.ua.eps.globalexamenandroidev1.DialogFragmentTimePicker.DialogFragment_TimePicker;

public class MainActivity extends AppCompatActivity implements DialogFragment_Spinner.SpinnerDialogListener, DialogFragment_RadioButton.RadioButtonDialogListener, DialogFragment_CheckBox.CheckBoxDialogListener, DialogFragment_List.ListDialogListener, DialogFragment_Personalizado.PersonalizadoDialogListener, DialogFragment_DatePicker.DatePickerDialogListener,DialogFragment_TimePicker.TimePickerDialogListener {
    //Variables Globales
        //Variables de arrays
        Calendar calendar;//Para mostrar los resultados del DatePicker con un ejemplo
        int[] intImagenResultadoSpinner ={R.mipmap.ic_dialog_fragment_spinner_people1,R.mipmap.ic_dialog_fragment_spinner_people2,R.mipmap.ic_dialog_fragment_spinner_people3,R.mipmap.ic_dialog_fragment_spinner_people4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declarar Variables
            //Variables botones
        Button botonDialogFragSpinner = findViewById(R.id.DialogFragmentSpinner);//Llama al spinner personalizado que retorna la posicion seleccionada, asi que implementamos interfaz en este activity para hacer algo con ella
        Button botonDialogFragTexto = findViewById(R.id.DialogFragmentTexto);//Llama al dialogo con el texto
        Button botonDialogRaddioButton = findViewById(R.id.DialogFragmentRadio);//Llama al dialogo con el radiobutton y retorna el String del array que hemos introducido
        Button botonDialogCheckBox = findViewById(R.id.DialogFragmentCheckBox);//Llama al dialogo con el checkBox y retorna un array de numeros seleccionados y en el main en su interfaz hacemos la suma
        Button botonDialogList = findViewById(R.id.DialogFragmentList);//Llama al dialogo con la lista de Strings y lo retorna.
        Button botonDialogPersonalizado = findViewById(R.id.DialogFragmentPersonalizado);//Llama al dialogo con views personalizados y retorna sus valores
        Button botonDialogDatePicker = findViewById(R.id.DialogFragmentDatePicker);//Llama al dialogo con un date picker y regresa la fecha seleccionada
        Button botonDialogTimePicker = findViewById(R.id.DialogFragmentTimePicker);//Llama al dialogo con un time picker y regresa la hora seleccionada
        Button botonActivityMenu = findViewById(R.id.ActivityMenu);//Llama a un activity que contiene un menu con distintas opciones, el icono del menu cambiado, opcion con imagen en el menu,con un toolbar personalizado y con togglebutton y switch
        Button botonActivityTableLayout = findViewById(R.id.ActivityTableLayout);//Llama a un activity que contiene un tableLayout que pinta las casillas adyacentes
        Button botonActivityPasoParametros = findViewById(R.id.ActivityParametros);//Llama a un activity pasandole parametros y este activity invoca otro activity que le retorna parametros
        Button botonActivityGlobal = findViewById(R.id.ActivityGlobal);//Llama a un activity con checkbox, radiobutton, edittext con input y los estados de un activity
        Button botonActivityCambioIdioma = findViewById(R.id.ActivityCambioIdioma);//Contiene textView que cambiaran segun el idioma y con un ratioButton permite cambiar el idioma
        Button botonActivityCompareTo = findViewById(R.id.ActivityCompareTo);//Llama a un activity que contiene lo de la edad actual, el compare to y un modelo en el que se crean datos dinamicamente en un spinner



        botonDialogFragTexto.setOnClickListener(new View.OnClickListener() {//Fragmento con un texto
            @Override
            public void onClick(View v) {//Manera de llamar al fragmento
                DialogFragment fragmentoDialogo = new DialogFragment_Texto();
                fragmentoDialogo.show(getSupportFragmentManager(),"Intrucciones");
            }
        });

        botonDialogFragSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoSpinner = new DialogFragment_Spinner();
                fragmentoSpinner.show(getSupportFragmentManager(),"Spinner");


            }
        });

        botonDialogRaddioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoRadioButton = new DialogFragment_RadioButton();
                fragmentoRadioButton.show(getSupportFragmentManager(),"RaddioButton");
            }
        });

        botonDialogCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoCheckBox = new DialogFragment_CheckBox();
                fragmentoCheckBox.show(getSupportFragmentManager(),"CheckBox");
            }
        });

        botonDialogList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoList = new DialogFragment_List();
                fragmentoList.show(getSupportFragmentManager(),"List");
            }
        });

        botonDialogPersonalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoPersonalizado = new DialogFragment_Personalizado();
                fragmentoPersonalizado.show(getSupportFragmentManager(),"Personalizado");
            }
        });

        botonDialogDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoDatePicker = new DialogFragment_DatePicker();
                fragmentoDatePicker.show(getSupportFragmentManager(),"DatePicker");
            }
        });

        botonDialogTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragmentoTimePicker = new DialogFragment_TimePicker();
                fragmentoTimePicker.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        botonActivityMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity_Menu = new Intent(MainActivity.this, Activity_Menu.class);
                startActivity(goToActivity_Menu);
            }
        });

        botonActivityTableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity_TableLayout = new Intent(MainActivity.this, Activity_TableLayout.class);
                startActivity(goToActivity_TableLayout);
            }
        });

        botonActivityPasoParametros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textViewPasoParametros = "Del Activity Anterior";
                int imageViewPasoParametros = R.mipmap.ic_paso_parametros;
                int[] listaNumerosPasoParametros = {1,2,3,4,5};
                boolean comprobar = false;

                Intent goToActivity_PasoParametros = new Intent(MainActivity.this, Activity_PasoParametros.class);
                goToActivity_PasoParametros.putExtra("TextViewPasoParametros",textViewPasoParametros);
                goToActivity_PasoParametros.putExtra("ImageVIewPasoParametros",imageViewPasoParametros);
                goToActivity_PasoParametros.putExtra("int[]PasoParametros",listaNumerosPasoParametros);
                goToActivity_PasoParametros.putExtra("booleanPasoParametros", comprobar);

                startActivity(goToActivity_PasoParametros);
            }
        });

        botonActivityGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity_Global = new Intent(MainActivity.this, Activity_Global.class);
                startActivity(goToActivity_Global);
            }
        });

        botonActivityCambioIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity_CambioIdioma = new Intent(MainActivity.this, Activity_CambioIdioma.class);
                startActivity(goToActivity_CambioIdioma);
            }
        });

        botonActivityCompareTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity_CompareTo = new Intent(MainActivity.this, Activity_CompareToEdadActual.class);
                startActivity(goToActivity_CompareTo);
            }
        });
    }
    //***********************************INTERFACES PARA RETORNAR DATOS DE FRAGMENTOS*******************************************

        //Interfaz del Dialogo Spinner
        @Override
        public void onDialogSpinner(int seleccion) {//Retorna la posicion para seleccionar la imagen en un array
            //TODO elegir uno por defecto anulado
            if(seleccion!=-1) {
                Toast.makeText(getApplicationContext(), "El item seleccionado es: " + seleccion + "", Toast.LENGTH_SHORT).show();
                ImageView imagenResultadoSpinner = findViewById(R.id.imageViewResultadoSpinner);
                imagenResultadoSpinner.setImageResource(intImagenResultadoSpinner[seleccion]);
                //Sino probar con .setImageResource(getDrawable(array[seleccion]))
            }
        }

        //Interfaz del Dialogo RadioButtons
        @Override
        public void onDialogRadioButtons(String seleccion) {
            if(!seleccion.equals("")) {
                Toast.makeText(getApplicationContext(), "Has elegido el String: " + seleccion, Toast.LENGTH_SHORT).show();
            }
        }

        //Interfaz del Dialogo CheckBox
        @Override
        public void onDialogCheckBox(ArrayList<Integer> seleccion) {
            int sumaTotal = 0;
            if (seleccion!=null){
                for (int  i = 0; i<seleccion.size();i++){
                    sumaTotal+=seleccion.get(i);
                }
                Toast.makeText(getApplicationContext(), "La suma de los numeros seleccionados es de: " + sumaTotal, Toast.LENGTH_SHORT).show();
            }
        }

        //Interfaz del Dialogo List
        @Override
        public void onDialogList(String seleccion) {
            Toast.makeText(getApplicationContext(), "Has selecionado " + seleccion, Toast.LENGTH_SHORT).show();
        }

        //Interfaz del Dialogo Personalizado
        @Override
        public void onDialogPersonalizado(String datos) {
            Toast.makeText(getApplicationContext(), "Tu nombre completo es: " + datos, Toast.LENGTH_SHORT).show();
        }

        //Interfaz del Dialogo DatePicker
        @Override
        public void onDialogDatePicker(int anyo, int mes, int dia) {//TODO obtener fecha y hora de hoy
            Toast.makeText(getApplicationContext(), "La fecha elegida es: " + dia + "/" + mes + "/" + anyo, Toast.LENGTH_SHORT).show();
            //El ejemplo del calendar para pasarselo a un modelo por ejemplo y asi ordenar por fecha
            calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, anyo);
            calendar.set(Calendar.MONTH, mes+1);
            calendar.set(Calendar.DAY_OF_MONTH, dia);
        }
        //TODO poner el ejericio del examen y hipotenochas y hacer los ejercicios sueltos
        //Interfaz del Dialogo TimePicker
        @Override
        public void onDialogTimePicker(int hour, int minute) {//TODO Revisar gregorian Calendar
            Toast.makeText(getApplicationContext(), "La hora elegida es: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
            /*
                if(minuto<10) {
                tv.setText(hora + ":" + "0" + minuto);
                }else{
                    tv.setText(hora+":"+minuto);
                }
             */
        }
}