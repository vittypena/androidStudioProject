package es.ua.eps.filmoteca

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
//TODO En activity_about hay un comentario para investigar acerca del constraintLayout
class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        button.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indiedevday.es/"))
            startActivity(browser)
            //Toast.makeText(this,"Funcionalidad sin implementar",Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:victorpenawow@gmail.com"))
            startActivity(email)
            //Toast.makeText(this,"Funcionalidad sin implementar 2",Toast.LENGTH_LONG).show()
        }

        button3.setOnClickListener {
            finish()
            //Toast.makeText(this,"Funcionalidad sin implementar 3",Toast.LENGTH_LONG).show()
        }
    }
}