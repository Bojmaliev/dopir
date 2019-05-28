package mk.trkalo.demoapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int uniqueId = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }
    public void calculateden(View view){
        Intent i = new Intent(MainActivity.this, SendNotif.class);
        i.putExtra("title", "Ova mi e naslov");
        i.putExtra("desc", "Ova e description");
        i.putExtra("id", 123);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),0,i,0);
        AlarmManager am = ((AlarmManager) getSystemService(ALARM_SERVICE));
        Calendar cc = Calendar.getInstance();
        am.set(AlarmManager.RTC_WAKEUP, cc.getTimeInMillis(), pi);


    }
}
