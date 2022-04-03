package es.ua.eps.filmoteca

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import es.ua.eps.myapplication.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val btnSupport = findViewById<View>(R.id.btnSupport) as Button
        val btnWebsite = findViewById<View>(R.id.btnWebsite) as Button
        val btnBack = findViewById<View>(R.id.btnBack) as Button

        btnSupport.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:malozano@ua.es"))
            startActivity(intent)
        }
        btnWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://eps.ua.es/master-moviles"))
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpTo(this, Intent(this, FilmListFragment::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}