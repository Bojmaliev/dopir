package org.eu.net.pole.polezaglasanje;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by ninja on 12/10/2017.
 */

public class BootReceiver  extends BroadcastReceiver {
    private Context cc;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BOOT COMPLETED", "SA SLUCI");
        this.cc = context;
        Calendar calendar = Calendar.getInstance();

        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        setAlarm(calendar.getTimeInMillis()+10000);

    }
    public void setAlarm(long timeInMillis){
        AlarmManager alarmManager = (AlarmManager) this.cc.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this.cc, DownloadFromServer.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.cc, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_HALF_DAY, pendingIntent);

    }
}
