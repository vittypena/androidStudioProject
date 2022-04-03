package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts

class FilmEditActivity : AppCompatActivity() {
    companion object{
         val EXTRA_FILM_INDEX = "EXTRA_FILM_INDEX"
        private const val TAG = "FilmEditActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit_linear)
        val button12 = findViewById<Button>(R.id.button12)
        val button13 = findViewById<Button>(R.id.button13)
        val button14 = findViewById<Button>(R.id.button14)
        val button15 = findViewById<Button>(R.id.button15)
        val titulo = findViewById<EditText>(R.id.editTextTextPersonName)
        val nombre = findViewById<EditText>(R.id.editTextTextPersonName3)
        val anyo = findViewById<EditText>(R.id.editTextTextPersonName4)
        val enlace = findViewById<EditText>(R.id.editTextTextPersonName2)
        val genero = findViewById<Spinner>(R.id.spinner)
        val formato = findViewById<Spinner>(R.id.spinner2)
        val comentarios = findViewById<EditText>(R.id.editTextTextMultiLine)
        val imagen = findViewById<ImageView>(R.id.imageView4)
        //val goActivityData = Intent(this@FilmEditActivity, FilmDataActivity::class.java)

        var img: Bitmap? = null //Para no tener que inizializarla
        var img2: Uri? = null
        var comprobarImagen = intent.getBooleanExtra("comprobar",false)
        val imagenExtra = intent.getStringExtra("imagenExtra")
        /*try {
            imagen.setImageResource(resources.getIdentifier(imagenExtra, "drawable", packageName))
        }catch (e : Exception){
            imagen.setImageBitmap(intent.extras?.get("imagenExtra") as Bitmap?)
        }*/

        if(!comprobarImagen && imagenExtra !=null){
            imagen.setImageResource(resources.getIdentifier(imagenExtra, "drawable", packageName))
        }else{
            imagen.setImageBitmap(intent.extras?.get("imagenBitmap") as Bitmap?)
        }

        val intentImagen = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                img = data?.extras?.get("data") as Bitmap?
                imagen.setImageBitmap(img)
                comprobarImagen = true
            }else{
                //Hacer lo que sea
            }
        }

        val intentGallery = Intent(Intent.ACTION_PICK)
        intentGallery.type = "image/*"
        var resultLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                img2 = data?.data
                imagen.setImageURI(data?.data)//Lo pilla como URI no como bitmap
                comprobarImagen = true
                //Lo de abajo no funciona porque la paso entera y para ello no deberia ser un bitmap, deberia de ser con la URI ya que ocupa demasiado
                //img2 = (imagen.drawable as BitmapDrawable).bitmap
            }else{
                //Hacer lo que sea
            }
        }

        button12.setOnClickListener {
            resultLauncher.launch(intentImagen)
        }

        button13.setOnClickListener {
            resultLauncher2.launch(intentGallery)
        }

        button14.setOnClickListener {
            setResult(RESULT_CANCELED)
            Log.d(TAG, "Edicion cancelada")
            finish()
        }

        button15.setOnClickListener {
            val data = Intent()
            data.putExtra("titulo", titulo.text.toString())
            data.putExtra("director", nombre.text.toString())
            data.putExtra("anyo", anyo.text.toString())
            data.putExtra("enlace", enlace.text.toString())
            data.putExtra("genero", genero.selectedItem.toString())
            data.putExtra("formato", formato.selectedItem.toString())
            data.putExtra("comentarios", comentarios.text.toString())
            data.putExtra("comprobar", comprobarImagen)
            data.putExtra("galeria", img2)
            data.putExtra("imagen", img)
            setResult(RESULT_OK, data)
            Log.d(TAG, "Edicion editada")
            finish()
        }

    }
}