package com.example.eventoonclic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView textEmail;
    ListView lista;
    List<String> androidVersionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lista = findViewById(R.id.listView);
        androidVersionList = new ArrayList<>();
        androidVersionList.add("Pie");
        androidVersionList.add("Oreo");
        androidVersionList.add("Nougat");
        androidVersionList.add("Marshmallow");
        androidVersionList.add("Lollipop");
        androidVersionList.add("Kitkat");
        androidVersionList.add("...");
        ArrayAdapter adaptadorVersionesAndroid = new ArrayAdapter(this, android.R.layout.simple_list_item_1,androidVersionList);
        lista.setAdapter(adaptadorVersionesAndroid);

        Bundle extras =getIntent().getExtras();
        String emailUsuario = extras.getString("valorEmail");
                //Bundle contiene un conjunto en el que se almacenan los parametros extras que pasamos a traves de intent
        textEmail = findViewById(R.id.textView2);
        textEmail.setText(emailUsuario);
    }
}