package com.example.dialogosclase;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoraFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    OnHoraSeleccionada f;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        f = (OnHoraSeleccionada) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR);
        int minuto = calendario.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hora,minuto, true);
        return tpd;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        f.onResultadoFecha(i,i1);
    }

    public interface OnHoraSeleccionada{
        public void onResultadoFecha(int hora, int minuto);
    }
}