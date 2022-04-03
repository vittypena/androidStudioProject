package es.ua.eps.drawables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.button);
        final Boolean[] comprobar = {false};
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!comprobar[0]){
                    boton.setBackground(getDrawable(R.drawable.boton_focused));
                    comprobar[0] =true;
                }else{
                    boton.setBackground(getDrawable(R.drawable.list_state_boton));
                    comprobar[0] = false;
                }
            }
        });


    }
}