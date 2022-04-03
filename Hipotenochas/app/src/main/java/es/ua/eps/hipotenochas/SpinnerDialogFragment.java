package es.ua.eps.hipotenochas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import es.ua.eps.hipotenochas.modelo.Data;

//...........................DialogFragmentSpiner...........................//
//Crear dialogo
//Crear spinner
//Crear vista spinner
//Crear spinner personalizado, para ello creamos el modelo con data y los datos; el item_xml y el adaprador personalizado en ese orden
//Enlazar vista con el dialogo
//Implementar interfaz para retornar datos
//Enlazar con el main
//...........................DialogFragmentSpiner...........................//

public class SpinnerDialogFragment extends DialogFragment {
    //Interfaz para retornar
    public interface SpinnerDialogListener{
        public void onDialogSpinner(int seleccion);
    }
    SpinnerDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (SpinnerDialogFragment.SpinnerDialogListener) context;
    }//FIN

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            //Crear vista enlazada al spinner
        LayoutInflater inflater = this.getLayoutInflater();
        View spinnerView = inflater.inflate(R.layout.dialog_fragment_spinner, null);
        builder.setMessage("Selecciona un personaje:");
        builder.setView(spinnerView);//Enlazarla al dialog
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {//No es necesario

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //Spinner
        Spinner spinner = spinnerView.findViewById(R.id.spinner);//Encuentra el spinner en la vista enlazada
            //Declaramos adaptador
        SpinnerAdapter adapter;
        adapter = new SpinnerAdapter(getActivity(), Data.getHipotenochaList());
        spinner.setAdapter(adapter);
            //Al seleccionar el item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//Al clicar en el spinner
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listener.onDialogSpinner(i);//Retornamos el dato de la posicion
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return builder.create();
    }
}
