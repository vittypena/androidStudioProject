package es.ua.eps.globalexamenandroidev1.DialogFragmentPersonalizado;

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

import java.util.ArrayList;

import es.ua.eps.globalexamenandroidev1.DialogFragmentCheckBox.DialogFragment_CheckBox;
import es.ua.eps.globalexamenandroidev1.R;

public class DialogFragment_Personalizado extends DialogFragment {
    /*
    IMPORTANTE -> ESTE PAQUETE CUENTA TB CON LOS LAYOUT: (dialog_fragment_personalizado.xml).
    */

    //Variables Globales
    String datos;
    //Interface que retornara los datos
    DialogFragment_Personalizado.PersonalizadoDialogListener listener;
    public interface PersonalizadoDialogListener {
        public void onDialogPersonalizado(String datos);
    }
    //Fin interface


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DialogFragment_Personalizado.PersonalizadoDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Poner la vista en el DialogFragment
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View personalizada_view = inflater.inflate(R.layout.dialog_fragment_personalizado, null);//Inflamos la view personalizada
        //Inicializar elementos de la view personalizada despues de obtener la view
        TextView textviewNombre = personalizada_view.findViewById(R.id.editTextNombre);
        TextView textviewEdad = personalizada_view.findViewById(R.id.editTextEdad);

        builder.setView(personalizada_view)
                .setTitle("View Personalizada")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datos = textviewNombre.getText()+ " " + textviewEdad.getText();
                        listener.onDialogPersonalizado(datos);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "View personalizada Cancelada", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
