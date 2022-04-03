package es.ua.eps.globalexamenandroidev1.DialogFragmentTexto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

import es.ua.eps.globalexamenandroidev1.R;

public class DialogFragment_Texto extends DialogFragment {//Tiene que extender de la clase DialogFragment
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Dialogo Opciones .set")
                .setIcon(R.mipmap.ic_dialog_fragment_texto)
                .setMessage(R.string.dialogFragment_texto)//Le metemos el texto que ha de estar en strings.xml
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"He clicado sí", Snackbar.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"He clicado no", Snackbar.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }
}
