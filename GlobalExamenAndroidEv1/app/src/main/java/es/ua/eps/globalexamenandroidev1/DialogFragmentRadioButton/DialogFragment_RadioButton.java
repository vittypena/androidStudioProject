package es.ua.eps.globalexamenandroidev1.DialogFragmentRadioButton;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner.DialogFragment_Spinner;
import es.ua.eps.globalexamenandroidev1.R;

public class DialogFragment_RadioButton extends DialogFragment {//En este ejemplo hacemos uso de arrays.xml
    //Variables Globales
    String seleccion = "";

    //Interface que retornara el String seleccionado
    RadioButtonDialogListener listener;
    public interface RadioButtonDialogListener{
        public void onDialogRadioButtons(String seleccion);
    }
    //Fin interface


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (RadioButtonDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elige un String")
                .setSingleChoiceItems(R.array.arrayDialogFragmentRadioButton, -1, new DialogInterface.OnClickListener() {//Radiobuttons, el -1 es para que no haya uno por defecto checkeado
                    @Override//En SingleChoice debemos introducir un array de datos para que salgan las opciones
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Accedemos al array usando la posicion y lo guardamos en el dato que pasaremos con el listener
                        seleccion = getResources().getStringArray(R.array.arrayDialogFragmentRadioButton)[i];//Este array se encuentra en arrays.xml
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDialogRadioButtons(seleccion);
            }
        });
        return builder.create();
    }
}
