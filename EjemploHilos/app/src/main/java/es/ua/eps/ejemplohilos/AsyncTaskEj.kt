package es.ua.eps.ejemplohilos

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AsyncTaskEj : AppCompatActivity() {
    private lateinit var tvCrono: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCrono = findViewById<TextView>(R.id.tvCrono)

        TareaContador().execute()
    }

    inner class TareaContador : AsyncTask<Void, Int, Void>() {
        override fun onPreExecute() {
            tvCrono.text = "Comenzando la descarga..."
        }
        override fun doInBackground(vararg p0: Void?): Void? {
            var t = 10
            do {
                publishProgress(t)

                Thread.sleep(1000)
                t--
            } while (t > 0)
            return null
        }
        override fun onProgressUpdate(vararg values: Int?) {
            tvCrono.text = "Contador: ${values[0]}"
        }
        override fun onPostExecute(result: Void?) {
            tvCrono.text = "Contador terminado"
        }
        override fun onCancelled() {
            tvCrono.text = "Contador terminado"
        }
    }
}