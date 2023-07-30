package com.example.dispositivosmoviles.ui.ui.activities

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {
//            createNotificationChannel()
            sendNotification()
        }
    }

    //es un id
    val CHANNEL: String = "Solicitudes"

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Compras"
            val descriptionText = "Solicitudes de perritos"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNotification() {
        val intent = Intent(this, NotificacionDetalles::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        //3 opcion
        //this para esta actividad si es una funcion arbitraria
        val notif = NotificationCompat.Builder(this, CHANNEL)

        //crear la notificacion
        notif.setContentTitle("Primera notificacion mi llave :v")
        notif.setContentText("Abre tu wea :v")
        notif.setSmallIcon(R.drawable.baseline_favorite_24)
        notif.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        //colocar el nuevo intent explicito para la notificacion.
        notif.setContentIntent(pendingIntent)
        notif.setAutoCancel(true)

        notif.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Esta es una notificacion en android que no se te olvide xd")
        )

        //envia la notificacion
        with(NotificationManagerCompat.from(this)) {
            notify(1, notif.build())
        }
    }

}