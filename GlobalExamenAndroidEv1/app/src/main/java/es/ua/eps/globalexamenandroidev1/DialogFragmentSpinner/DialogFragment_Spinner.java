package es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import es.ua.eps.globalexamenandroidev1.R;

public class DialogFragment_Spinner extends DialogFragment {
/*
    IMPORTANTE -> ESTE PAQUETE CUENTA TB CON LOS LAYOUT: (dialog_fragment_spinner.xml, item_spinner_personalizado.xml).
 */
    //Interface para poder acceder al dato desde la invocación
    SpinnerDialogListener listener;
    public interface SpinnerDialogListener{
        public void onDialogSpinner(int seleccion);
    }
    //Fin interface

    @Override
    public void onAttach(@NonNull Context context) {//Para retornar el dato
        super.onAttach(context);
        listener = (DialogFragment_Spinner.SpinnerDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();//Creamos la vista enlazada al spinner
        View spinnerView = inflater.inflate(R.layout.dialog_fragment_spinner, null); //Aqui ponemos el xml del spinner
        builder.setMessage("Selecciona un personaje: ")
                .setView(spinnerView);//Enlazar la vista con el dialog

        //Spinner
        Spinner spinner = spinnerView.findViewById(R.id.spinner); //El id del spinner en spinnerView (la vista enlazada)
            //Declaramos el adaptador
        SpinnerAdapter adapter;
        adapter = new es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner.SpinnerAdapter(getActivity(), Data.getModeloList());
        //adapter = new SpinnerAdapter(getActivity(), Data.getHipotenochaList());
        spinner.setAdapter(adapter);
            //Establecemos que pasara al seleccionar el item del spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onDialogSpinner(position);//Retornamos la posicion que estamos clicando para luego acceder a ella desde el main
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Fin de spinner

        return builder.create();
    }


}


//...........................DialogFragmentSpiner...........................//
//Crear dialogo
//Crear spinner
//Crear vista spinner
//Crear spinner personalizado, para ello creamos el modelo con data y los datos; el item_xml y el adaprador personalizado en ese orden
//Enlazar vista con el dialogo
//Implementar interfaz para retornar datos
//Enlazar con el main
//...........................DialogFragmentSpiner...........................//