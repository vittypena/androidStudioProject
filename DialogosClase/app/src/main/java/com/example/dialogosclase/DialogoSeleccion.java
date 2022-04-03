package com.example.dialogosclase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoSeleccion extends DialogFragment {
    Idioma idioma;
    @Override
    public void onAttach(@NonNull Context context) {
        idioma = (Idioma)getActivity();
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final String[] items = {"Español", "Frances", "Ingles"};
        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Seleccion")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        idioma.idiomaSeleccionado(items[i]);
                        //Snackbar.make(getActivity().findViewById(android.R.id.content),"Idioma: " + items[i], Snackbar.LENGTH_LONG).show();
                    }
                });
        return ventana.create();
    }

    public interface Idioma{
        public void idiomaSeleccionado(String idioma);
    }
}
