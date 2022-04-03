package es.ua.eps.barraestado

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private var contador = 0
    private val CHANNEL_ID = "channel_id"
    private val NOTIFI_ID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        var cadena = "Tareas iniciadas: $contador"
        var botonIniciar = findViewById<Button>(R.id.button)
        var botonDetener = findViewById<Button>(R.id.button2)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon((R.drawable.ic_stat_name))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setContentIntent(pendingIntent)
        val notificationManager = NotificationManagerCompat.from(this)

        botonIniciar.setOnClickListener {
            contador++
            builder.setContentTitle("Tareas")
                    .setContentText("Tareas iniciadas: $contador")

            notificationManager.notify(NOTIFI_ID, builder.build())
        }

        botonDetener.setOnClickListener {
            if(contador>0){
                contador--
                builder.setContentTitle("Tareas")
                    .setContentText("Tareas iniciadas: $contador")
                notificationManager.notify(NOTIFI_ID, builder.build())
            }else{
                notificationManager.cancel(NOTIFI_ID)
            }
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Creamos el canal
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            channel.description = getString(R.string.channel_description)

            //Registramos el canal
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}