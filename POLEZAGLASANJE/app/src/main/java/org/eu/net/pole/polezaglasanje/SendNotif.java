package org.eu.net.pole.polezaglasanje;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import java.util.Calendar;

/**
 * Created by ninja on 12/1/2017.
 */

public class SendNotif extends BroadcastReceiver {
    public static final String MY_PREFS_NAME = "appConfiguration";
    @Override
    public void onReceive(Context context, Intent intent){

        Integer timeNow = Calendar.HOUR_OF_DAY;
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);

        NotificationCompat.Builder notification;
        notification = new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.notiff);
        notification.setTicker("POLE ZA GLASANEJ");
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setSound(uri);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(intent.getStringExtra("title"));
        notification.setContentText(intent.getStringExtra("desc"));
        Intent novI = new Intent(context, MainActivity.class);
        novI.putExtra("urlToGo", intent.getStringExtra("url"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, novI, PendingIntent.FLAG_UPDATE_CURRENT);

        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(intent.getIntExtra("id", 0), notification.build());
    }

}

