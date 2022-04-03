package es.ua.eps.examenfinal;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DialogFragment_TimePickerSalida extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    //Interfaz
    TimePickerDialogListener listener;
    public interface TimePickerDialogListener{
        public void onDialogTimePickerSalida(int hour, int minute);
    }
    //Fin de interface

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (TimePickerDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        listener.onDialogTimePickerSalida(hourOfDay, minute);
    }
}
