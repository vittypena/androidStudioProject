package es.ua.eps.jarretas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SecundaryActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        tv = findViewById(R.id.tv1);

        Intent i = this.getIntent();

        String dato = i.getStringExtra("nombre");
        tv.setText("Hola " + dato);

    }



}
