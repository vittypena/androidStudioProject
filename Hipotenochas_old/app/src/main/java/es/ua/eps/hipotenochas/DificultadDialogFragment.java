package es.ua.eps.hipotenochas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
//...........................DialogFragmentRadioButtons...........................//
//Que extienda de DialogFragment
//Metodo onCreateDialog
//Exhibir en el main el dialogo (crear objeto DialogFragment y .show(getsupportFragmentManager, "tag");
//Implementar array en values
//Implementar interfaz en el componente padre y en este para poder retornar datos
//Implementar onAttach
//Introducir datos en el listener
//Devolver datos con el listener
//...........................DialogFragmentRadioButtons...........................//

public class DificultadDialogFragment extends DialogFragment {
    String seleccion;
    //Interfaz-------
    public interface DificultadDialogListener{
        public void onDialogPositiveClick(String seleccion);
    }
    DificultadDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DificultadDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elige una dificultad")
                .setSingleChoiceItems(R.array.dificultad, -1, new DialogInterface.OnClickListener() {//Radiobuttons, el -1 es para que no haya uno por defecto checkeado
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Accedemos al array usando la posicion y lo guardamos en el dato que pasaremos con el listener
                            seleccion = getResources().getStringArray(R.array.dificultad)[i];
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    listener.onDialogPositiveClick(seleccion);
            }
        });
        return builder.create();
    }
}


