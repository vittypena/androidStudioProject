package com.example.eventoonclic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JavaMainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);

        Button btn1 = findViewById(R.id.button);
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

        btn1.setOnClickListener(view -> {
            Toast.makeText(this, "Se ha javeado el boton 1", Toast.LENGTH_SHORT).show();

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            if(!email.isEmpty()&&!password.isEmpty()){
                Intent intentLogin = new Intent(this,HomeActivity.class );
                //Primer parametro activity actual, segundo parametro clase a la que queremos navegar
                intentLogin.putExtra("valorEmail", email);
                startActivity(intentLogin);
                //Aun no se pasa ningun parametro
            }

        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Se ha javeado el textView 1", Toast.LENGTH_SHORT).show();
    }
}