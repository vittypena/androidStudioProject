package es.ua.eps.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragmentos)

        //Comprobamos si estamos usando el layout dinamico
        if(findViewById<View>(R.id.fragment_container) != null){
            //Si se esta restaurando no hace falta cargar el fragmento
            if(savedInstanceState != null) return

            //Creamos el fragmento
            val ppalFragment = PrincipalFragment()

            //Pasamos los extras del intent al fragmento
            ppalFragment.arguments = intent.extras

            //Añadimos el fragmento al contenedor
            //fragmentManager.beginTransaction().add(R.id.fragment_container, ppalFragment).commit()
            supportFragmentManager.findFragmentById(R.id.detalle_fragment)
        }else{
            //Estatico
        }
    }
}