package org.eu.dopis.dopir;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class firstFinish extends AppCompatActivity implements OnTaskCompleted {
    public static final String MY_PREFS_NAME = "appConfiguration";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_finish);

        Calendar calendar = Calendar.getInstance();

        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        setAlarm(calendar.getTimeInMillis()+1000);

    }
    public void setAlarm(long timeInMillis){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, DownloadFromServer.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_HALF_DAY, pendingIntent);

    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void saveConf(View view){

        EditText email = (EditText) findViewById(R.id.email);
        Spinner language = (Spinner) findViewById(R.id.spinnerLang);

        if(isEmailValid(email.getText().toString())){
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("email", email.getText().toString());
            editor.putString("lang", language.getSelectedItem().toString());
            editor.putBoolean("morningNotif", true);
            editor.putBoolean("nightNotif", true);
            editor.putBoolean("otherNotif", true);
            editor.putInt("progress", 0);
            editor.apply();
            Language.changeLang(language.getSelectedItem().toString());
            String e = email.getText().toString();
            String[] ime=e.split("@");
            UrlMethod hello = new UrlMethod(firstFinish.this);
            hello.execute("http://topinsurance365.com/user-endpoint.php?USER="+ime[0]+"&EMAIL="+e+"&LANG="+Language.getShort(language.getSelectedItem().toString())+"&APP=DOPIR");
            vratiSe();
        }else {
            Toast.makeText(this, "Please enter a valid email address...", Toast.LENGTH_SHORT).show();
        }
    }
    public void vratiSe(){
        Intent intent = new Intent(getApplicationContext(), secondStep.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed(){
        return;
    }

    @Override
    public void onTaskCompleted(String response) {
        //response nothing
    }
}
