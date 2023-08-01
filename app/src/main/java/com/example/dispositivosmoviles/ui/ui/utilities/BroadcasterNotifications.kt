package com.example.dispositivosmoviles.ui.ui.utilities

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.dispositivosmoviles.R

class BroadcasterNotifications : BroadcastReceiver() {

    val CHANNEL: String = "Solicitudes"

    //parametros mandatorios
    //siempre debo tener un contexto
    override fun onReceive(context: Context, intent: Intent) {

//        val myIntent = Intent(context, CameraActivity::class.java)
//        val pendingIntent: PendingIntent = PendingIntent.getActivity(
//            context,
//            0,
//            myIntent,
//            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//        )
//        Intent(context,CameraActivity::class.java)

        val notify = NotificationCompat.Builder(context, CHANNEL)

        notify.setContentTitle("Broadcast Notification")
        notify.setContentText("Llego la hora del duelo xd")
        notify.setSmallIcon(R.drawable.baseline_favorite_24)
        notify.setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notify.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("ya vamonos :v")
        )

        //colocar el nuevo intent explicito para la notificacion.
//        notif.setContentIntent(pendingIntent)
//        notif.setAutoCancel(true)

        //crear un manager de tipo broadcast
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
            1,
            notify.build()
        )
    }
}