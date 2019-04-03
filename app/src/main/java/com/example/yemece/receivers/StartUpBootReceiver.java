package com.example.yemece.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.yemece.services.NotificationService;

import java.util.Calendar;

public class StartUpBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            // Cria um Calendar para as 0 horas do dia seguinte
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);

            //PendingIntent para lançar o serviço
            Intent serviceIntent = new Intent(context, NotificationService.class);
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, serviceIntent, 0);

            //Alarme que se repete todos os dias às 0 horas
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent);

        }
    }
}