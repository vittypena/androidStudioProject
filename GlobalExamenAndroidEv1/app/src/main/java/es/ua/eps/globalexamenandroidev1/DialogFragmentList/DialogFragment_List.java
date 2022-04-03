package es.ua.eps.globalexamenandroidev1.DialogFragmentList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import es.ua.eps.globalexamenandroidev1.DialogFragmentCheckBox.DialogFragment_CheckBox;

public class DialogFragment_List extends DialogFragment {
    //Variables Globales
    final String[] items = {"Vitty", "Rocio", "Manolo", "Sasuke","Naruto", "Milagro"};//Se rellena con un roll hacia abajo si alcanza el máximo

    //Interface
    DialogFragment_List.ListDialogListener listener;
    public interface ListDialogListener {
        public void onDialogList(String seleccion);
    }
    //Fin interface

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ListDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Selecciona un nombre")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogList(items[i]);
                    }
                });
        return ventana.create();
    }
}
