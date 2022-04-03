package es.ua.eps.globalexamenandroidev1.DialogFragmentTimePicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import es.ua.eps.globalexamenandroidev1.DialogFragmentDatePicker.DialogFragment_DatePicker;

public class DialogFragment_TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    //Interfaz
    DialogFragment_TimePicker.TimePickerDialogListener listener;
    public interface TimePickerDialogListener{
        public void onDialogTimePicker(int hour, int minute);
    }
    //Fin de interface

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DialogFragment_TimePicker.TimePickerDialogListener) context;
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
        listener.onDialogTimePicker(hourOfDay, minute);
    }
}
