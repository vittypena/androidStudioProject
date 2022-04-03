package es.ua.eps.globalexamenandroidev1.DialogFragmentDatePicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

import es.ua.eps.globalexamenandroidev1.DialogFragmentPersonalizado.DialogFragment_Personalizado;

public class DialogFragment_DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    //Interfaz
    DialogFragment_DatePicker.DatePickerDialogListener listener;
    public interface DatePickerDialogListener{
        public void onDialogDatePicker(int anyo, int mes, int dia);
    }
    //Fin de interface

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DialogFragment_DatePicker.DatePickerDialogListener) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {//Lo muestra en el dialog
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {//Pone la Fecha para retornarla
        /*
        GregorianCalendar gc = new GregorianCalendar(year,month,dayOfMonth);
        listener.onDialogDatePicker(gc.get(GregorianCalendar.YEAR),gc.get(GregorianCalendar.MONTH),gc.get(GregorianCalendar.DAY_OF_MONTH));
         */
        listener.onDialogDatePicker(year,month+1,dayOfMonth);
    }


}
