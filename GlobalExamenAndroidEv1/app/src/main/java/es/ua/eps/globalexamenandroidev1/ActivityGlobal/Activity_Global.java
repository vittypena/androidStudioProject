package es.ua.eps.globalexamenandroidev1.ActivityGlobal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import es.ua.eps.globalexamenandroidev1.R;

public class Activity_Global extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);
        Log.i("LOGCICLO", "Entramos en método onCreate");
    }
    //Estados del activity
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LOGCICLO", "Entramos en método onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LOGCICLO", "Entramos en método onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LOGCICLO", "Entramos en método onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LOGCICLO", "Entramos en método onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LOGCICLO", "Entramos en método onDestroy");
    }

    //RadioButton Acciones (Poner el grupo de botones y en el xml el evento)
    public void onRadioButtonGlobal(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    Toast.makeText(getApplicationContext(), "Español", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radioButton2:
                if (checked)
                    Toast.makeText(getApplicationContext(), "Extranjero", Toast.LENGTH_SHORT).show();
                    break;
        }
    }

    //CheckBox Acciones (Poner en el xml el evento)
    public void onCheckboxGlobal(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked)
                    Toast.makeText(getApplicationContext(), "Hombre", Toast.LENGTH_SHORT).show();
            else
                // Remove the meat
                break;
            case R.id.checkBox2:
                if (checked)
                    Toast.makeText(getApplicationContext(), "Mujer", Toast.LENGTH_SHORT).show();
            else
                // I'm lactose intolerant
                break;
        }
    }
}
