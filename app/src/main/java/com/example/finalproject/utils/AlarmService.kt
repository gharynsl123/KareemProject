package com.example.finalproject.utils

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.finalproject.R
import java.util.*

class AlarmService : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(EXTRA_MESSAGE)
        val type = intent?.getIntExtra(EXTRA_TYPE, 0)

        val title = when (type) {
            TYPE_PAGI -> "Dzikir Pagi"
            TYPE_PETANG -> "Dzikir Petang"
            else -> "Something Wrong Here."
        }

        if (context != null && message != null) {
            val requestCode = when (type) {
                TYPE_PAGI -> NOTIF_DZIKIR_PAGI
                TYPE_PETANG -> NOTIF_DZIKIR_PETANG
                else -> -1
            }
            showNotificationAlarm(
                context,
                title,
                message,
                requestCode
            )
        }
    }
    //set Dzikir Pagi Alarm
    fun setDzikirPagiAlarm(context: Context, type: Int, time: String, message: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmService::class.java)
        intent.putExtra("message", message)
        intent.putExtra("type", type)

        val timeArray = time.split(":").toTypedArray()

        val calendar = Calendar.getInstance()

        //Time
        calendar.set(Calendar.HOUR, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        //Pending Intent
        val pendingIntent = PendingIntent.getBroadcast(context, NOTIF_DZIKIR_PAGI, intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        Toast.makeText(context, "Success set RepeatingAlarm", Toast.LENGTH_SHORT).show()
    }

    //set Dzikir Petang Alarm
    fun setDzikirPetangAlarm(context: Context, type: Int, time: String, message: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmService::class.java)
        intent.putExtra("message", message)
        intent.putExtra("type", type)

        val timeArray = time.split(":").toTypedArray()

        val calendar = Calendar.getInstance()

        //Time
        calendar.set(Calendar.HOUR, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        //Pending Intent
        val pendingIntent = PendingIntent.getBroadcast(context, NOTIF_DZIKIR_PETANG, intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        Toast.makeText(context, "Success set RepeatingAlarm", Toast.LENGTH_SHORT).show()
    }


    private fun showNotificationAlarm(
        context: Context,
        title: String,
        message: String,
        notificationId: Int
    ) {
        val channelName = "DzikirPagiPetang"
        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        val channelId = "dzikir_pagi_petang"

        val notificationManage =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.icon_app)
            .setContentTitle(title)
            .setContentText(message)
            .setSound(ringtone)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(channelId)
            notificationManage.createNotificationChannel(channel)
        }
        val notif = builder.build()
        notificationManage.notify(notificationId, notif)
    }

    companion object {
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"

        const val NOTIF_DZIKIR_PAGI = 101
        const val NOTIF_DZIKIR_PETANG = 102

        const val TYPE_PAGI = 1
        const val TYPE_PETANG = 0
    }
}