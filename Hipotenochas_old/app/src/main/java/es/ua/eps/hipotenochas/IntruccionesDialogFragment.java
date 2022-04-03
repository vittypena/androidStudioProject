package es.ua.eps.hipotenochas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//...........................DialogFragment...........................//
//Que extienda de DialogFragment
//Metodo onCreateDialog
//Exhibir en el main el dialogo (crear objeto DialogFragment y .show(getsupportFragmentManager, "tag");
//...........................DialogFragment...........................//

public class IntruccionesDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.instrucciones_juego)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}
