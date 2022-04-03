package es.ua.eps.pruebasdebugger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "MiActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "indice=idx")
        Toast.makeText(this,"HOLAAA", Toast.LENGTH_LONG).show();
        Toast.makeText(this,"HOLAAA2", Toast.LENGTH_LONG).show();
        Toast.makeText(this,"HOLAAA3", Toast.LENGTH_LONG).show();
        Toast.makeText(this,"HOLAAA4", Toast.LENGTH_LONG).show();

    }
}