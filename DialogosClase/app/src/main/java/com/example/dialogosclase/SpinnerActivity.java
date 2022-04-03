package com.example.dialogosclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SpinnerActivity extends AppCompatActivity {
    Spinner sp;
    String[] nombreProvincia = {"Zaragoza","Huesca","Teruel"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spinner);
        sp = findViewById(R.id.spinner1);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,nombreProvincia);
        sp.setAdapter(adaptador);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*
                String text = sp.getSelectedItem().toString();
                Toast.makeText(SpinnerActivity.this, text, Toast.LENGTH_SHORT).show();
                 */
                Toast.makeText(getApplicationContext(), ""+i, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), sp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button volver =  findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.putExtra("PROVINCIA", sp.getSelectedItem().toString());
                setResult(RESULT_OK,in);
                finish();
            }
        });
    }


}
