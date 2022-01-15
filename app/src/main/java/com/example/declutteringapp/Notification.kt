package com.example.declutteringapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.declutteringapp.view.MainActivity


private const val CHANNEL_ID = "challenges_channel"
    private const val NOTIFICATION_ID = 1

    class Notification(val context: Context) {
        fun createNotification(title: String, message: String){
            createNotificationChannel()
            val intent= Intent(context, MainActivity::class.java).apply {
                flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(context,0,intent,0)
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.btn_star)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)

        }

        private fun createNotificationChannel(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_ID, importance).apply {
                    description = "challenges Channel description"
                }
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

        }
    }

class WorkerNotification(private val context: Context, workParams: WorkerParameters): Worker(context, workParams)  {
    override fun doWork(): Result {
        Notification(context).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString())
        return Result.success()
    }

}
