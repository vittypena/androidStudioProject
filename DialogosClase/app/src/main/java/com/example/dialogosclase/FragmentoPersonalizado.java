package com.example.dialogosclase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class FragmentoPersonalizado extends DialogFragment {

    private TextView cambiarTexto;

    public FragmentoPersonalizado(TextView cambiarTexto) {
        this.cambiarTexto = cambiarTexto;
    }

    public void onAttach(@NonNull Context context) {
        //cambiarTexto = (FragmentoPersonalizado.texto1)getActivity();
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());

        LayoutInflater inflador = getActivity().getLayoutInflater();
        View vistaPersonaizada = inflador.inflate(R.layout.fragmento_personalizado, null);

        TextView texto1 = vistaPersonaizada.findViewById(R.id.editTextTextPersonName);
        TextView texto2 = vistaPersonaizada.findViewById(R.id.editTextTextPersonName3);
        ventana.setTitle("Datos personalizados")
                .setView(vistaPersonaizada)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cambiarTexto.setText(texto1.getText()+" "+texto2.getText());
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(),"No cambies nada", Toast.LENGTH_LONG).show();
                    }
                });

        return ventana.create();
    }
}
