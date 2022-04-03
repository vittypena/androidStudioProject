package com.example.dialogosclase;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    OnFechaSeleccionada f;
    Calendar calendario;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        f = (OnFechaSeleccionada) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendario = Calendar.getInstance();
        int anyo = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), this, anyo, mes, dia);

        return pickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        //f.onResultadoFecha(i,i1+1,i2);
        GregorianCalendar gc = new GregorianCalendar(i,i1,i2);
        f.onResultadoFecha(gc);
    }

    /*Con año mes dia
    public interface OnFechaSeleccionada{
        public void onResultadoFecha(int anyo, int mes, int dia);
    }*/

    public interface OnFechaSeleccionada{
        public void onResultadoFecha(GregorianCalendar calendario);
    }
}
