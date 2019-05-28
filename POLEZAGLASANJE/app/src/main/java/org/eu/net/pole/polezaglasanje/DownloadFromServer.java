package org.eu.net.pole.polezaglasanje;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.util.Log;

        import com.google.gson.JsonObject;
        import com.google.gson.JsonParser;

        import java.util.Calendar;


public class DownloadFromServer extends BroadcastReceiver implements OnTaskCompleted {
    Context cc;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.cc = context;
        UrlMethod hello = new UrlMethod(DownloadFromServer.this);
        hello.execute(Language.getSyncUrl());

    }
    @Override
    public void onTaskCompleted(String response) {
        /*KRAJ*/
        /*JSON*/
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(response).getAsJsonObject();
        String celo = o.get("content").toString().replaceAll("\"","");
        celo = celo.replaceAll("\\\\r", "").replaceAll("\\\\n", "");
        //celo = celo.replaceAll("<hr id=\"system-readmore\" />", "");
        celo.replaceAll("<.*?>" , "");
        String redovi[] = celo.split("!#####!");

        /*GENERIRAJ NOTIFKACIJA*/
        /*Intent intent = new Intent(this.cc, SendNotif.class);
        intent.putExtra("title", "RABOTI BE");
        intent.putExtra("desc", "KAKO MOZE DA NE RABOTI");
        intent.putExtra("id", 123456);
        PendingIntent pi = PendingIntent.getBroadcast(this.cc,0,intent,0);
        AlarmManager am = ((AlarmManager) this.cc.getSystemService(Context.ALARM_SERVICE));
        Calendar cc = Calendar.getInstance();
        cc.set(2017,11,2,0,18,0);
        am.set(AlarmManager.RTC_WAKEUP, cc.getTimeInMillis(), pi);*/
        int min=8;
        for(int i=0; i<redovi.length; i++){
            String info[] = redovi[i].split("!@@@@!");
            Log.i("Info"+i, info[0]+" "+info[1]+" "+info[2]+" "+info[3]+" "+info[4]);

            if(info.length == 5){
                /*INFO ZA NOTIFIKACIJA*/
                int id = Integer.parseInt(info[0].replaceAll(" ", ""));
                Calendar data = calculateCalendar(info[1]);
                String naslov = info[2];
                String desc = info[3];
                String url = info[4];
                Log.i("Info"+i, data.getTime().toString());
                if(data.getTimeInMillis() < System.currentTimeMillis())continue;
                /*GENERIRAJ ALARM*/
                Intent intent1 = new Intent(this.cc, SendNotif.class);
                intent1.putExtra("title", naslov);
                intent1.putExtra("desc", desc);
                intent1.putExtra("id", id);
                intent1.putExtra("url", url);
                PendingIntent pi1 = PendingIntent.getBroadcast(this.cc,id,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager am1 = ((AlarmManager) this.cc.getSystemService(Context.ALARM_SERVICE));
                am1.set(AlarmManager.RTC_WAKEUP, data.getTimeInMillis(), pi1);
            }

        }

    }
    private Calendar calculateCalendar(String d){
        String [] dateTime = d.split(" ");
        Calendar toR = Calendar.getInstance();
        if(dateTime.length == 2){
            String[]date = dateTime[0].split("-");
            String[]time = dateTime[1].split(":");
            if(date.length == 3 && time.length == 2){
                toR.set(Integer.parseInt(date[2]), Integer.parseInt(date[1])-1, Integer.parseInt(date[0]),
                        Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
            }
        }
        return toR;
    }
}
