package es.ua.eps.globalexamenandroidev1.DialogFragmentCheckBox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import es.ua.eps.globalexamenandroidev1.DialogFragmentRadioButton.DialogFragment_RadioButton;
import es.ua.eps.globalexamenandroidev1.R;

public class DialogFragment_CheckBox extends DialogFragment {
    //Variables Globales
    ArrayList<Integer> seleccion = new ArrayList<>();
    String[] valoresNumeros = new String[]{"1","2","3","4"};//Debe de ser de tipo string y luego hacer un parseInt

    //Interface que retornara el array seleccionado con las selecciones
    CheckBoxDialogListener listener;
    public interface CheckBoxDialogListener {
        public void onDialogCheckBox(ArrayList<Integer> seleccion);
    }
    //Fin interface


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DialogFragment_CheckBox.CheckBoxDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elige los operadores para sumar:")
                .setMultiChoiceItems(valoresNumeros, null, new DialogInterface.OnMultiChoiceClickListener() {//Debe de ser un array de tipo String y el dato un String
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {//which es la posicion del array
                            if(isChecked){//Si esta chequeado lo añadimos a la lista
                                seleccion.add(Integer.parseInt(valoresNumeros[which]));
                            }else{//Si no esta chequeado lo quitamos de la lista
                                seleccion.remove(Integer.parseInt(valoresNumeros[which]));
                            }
                    }
                })
                .setPositiveButton("Sumar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogCheckBox(seleccion);//Pasamos el array
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Has cancelado la Operación del Checkbox", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}
