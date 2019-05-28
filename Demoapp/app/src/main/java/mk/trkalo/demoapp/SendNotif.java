package mk.trkalo.demoapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by ninja on 12/1/2017.
 */

public class SendNotif extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        NotificationCompat.Builder notification;
        notification = new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.denar);
        notification.setTicker("CHSONGS");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(intent.getStringExtra("title"));
        notification.setContentText(intent.getStringExtra("desc"));
        Intent novI = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, novI, PendingIntent.FLAG_UPDATE_CURRENT);

        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(intent.getIntExtra("id", 0), notification.build());
    }

}

