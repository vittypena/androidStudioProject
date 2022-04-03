package es.ua.eps.retosmouredev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var semana0 = findViewById<TextView>(R.id.semana0)

        semana0.setOnClickListener {
            for (i in 1..100){
                if(i%3==0&&i%5==0) println("fizzbuzz")
                else if (i%3==0){
                    println("fizz")
                }
                else if(i%5==0){
                    println("buzz")
                }
                else{
                    println(i)
                }
            }
        }
    }
}