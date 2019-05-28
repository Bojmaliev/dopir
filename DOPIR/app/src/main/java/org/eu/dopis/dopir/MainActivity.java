package org.eu.dopis.dopir;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends AppCompatActivity {

    private int kliknatoBack=0;
    public static final String MY_PREFS_NAME = "appConfiguration";
    WebView view;
    SwipeRefreshLayout mySwipeRefreshLayout;
    public boolean timerEnabled = false;
    public TextView counterText;
    final MediaPlayer mp = new MediaPlayer();
    final  MediaPlayer cekori = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*firsttime*/
        checkFirstTime();
        setDefaults();
        resetCounting();
        /*webview*/
        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);
        mySwipeRefreshLayout.setRefreshing(true);
        view=(WebView) this.findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setDisplayZoomControls(false);
        view.setWebViewClient(new WebViewClient());


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        view.reload();
                    }
                }
        );
        view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                URI urlto = null;
                try {
                    urlto = new URI(view.getUrl());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                if( progress > 30){
                    if(urlto != null){
                        if(timerEnabled && urlto.getHost().equals("topinsurance365.com")){
                            resetCounting();
                            resetToto("normal");
                        }else{
                            poraka("hide");
                        }
                    }
                }
                if (progress == 100) {
                    if(!timerEnabled && urlto != null) {
                        mySwipeRefreshLayout.setRefreshing(false);
                        if (!urlto.getHost().equals("topinsurance365.com")) {

                            if(canCount()){startCounting();totoSeta();}
                        }else{
                            if(canCount()){poraka(0, "show",0);}
                        }
                    }
                }
            }
        });
        view.loadUrl(Language.getUrl());
        resetToto("normal");
    }

    public boolean canCount(){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return (prefs.getLong("lastTimePoeni",0)+(3*60*60*1000) < System.currentTimeMillis());
    }
    @Override
    public void onResume(){
        super.onResume();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            view.loadUrl(b.getString("urlToGo"));
        }
        if(Language.changedLang){
            view.loadUrl(Language.getUrl()); Language.changedLang = false;
        }


    }

    public void startCounting(){

        timerEnabled = true;
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted() && timerEnabled) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                counterText = (TextView) findViewById(R.id.counterView);
                                Integer smeniInt = Integer.parseInt(counterText.getText().toString());

                                Log.i("INFO", String.valueOf(smeniInt));
                                smeniInt--;
                                counterText.setText(String.valueOf(smeniInt));
                                if(smeniInt == 0){
                                    resetCounting();
                                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                                    //TOAST
                                    Toast thankYou = Toast.makeText(MainActivity.this, Language.getString("thankYouHumanity"), Toast.LENGTH_LONG);
                                    View view11 = thankYou.getView();
                                    //ImageView image = new ImageView(MainActivity.this);
                                    //image.setImageResource(R.drawable.toastbg);
                                    TextView text = (TextView) view11.findViewById(android.R.id.message);
                                    text.setTextColor(Color.WHITE);
                                    //thankYou.setView(image);
                                    thankYou.show();
                                    //END TOAST
                                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                        int kolku = prefs.getInt("progress", 0) + 5;
                                        if(kolku >= 100){
                                            int srce = prefs.getInt("srca", 0);
                                            if(srce < 6){
                                                kolku=0;
                                                srce++;
                                                editor.putInt("srca", srce);
                                                setDefaults();
                                            }else{
                                                kolku=100;
                                            }
                                            playSound("srceDavanje.mp3");
                                        }else{
                                            playSound("iLoveYou.mp3");

                                        }

                                        editor.putInt("progress", kolku);
                                        editor.putLong("lastTimePoeni", System.currentTimeMillis());
                                        //setBar
                                        ProgressBar PROG = (ProgressBar) findViewById(R.id.progressBar);
                                        PROG.setProgress(kolku);
                                        //setBarFinish
                                        editor.apply();
                                        showImageGif();


                                }else if(smeniInt == 53){
                                    poraka(1, "show", 4000);
                                }else if(smeniInt == 44){
                                    poraka(2, "show", 4000);
                                }else if(smeniInt == 34){
                                    poraka(3, "show", 4000);
                                }else if(smeniInt == 25){
                                    poraka(4, "show", 4000);
                                }else if(smeniInt == 16){
                                    poraka(5, "show", 4000);
                                }else if(smeniInt == 1){
                                    poraka(6, "show", 4000);
                                    resetToto("sleep");
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }
    public void resetToto(String what){
        playRepeatingSound("stop");
        //WebView toto = (WebView) findViewById(R.id.TotoWebView);
        GifImageView toto = (GifImageView) findViewById(R.id.TotoWebView);
        //RelativeLayout totoSeta = (RelativeLayout) findViewById(R.id.Toto);
        toto.clearAnimation();
        ViewGroup.LayoutParams params = toto.getLayoutParams();

        if(canCount() && what.equals("normal")){
            //toto moze da sa dvize
            //toto.loadUrl("file:///android_asset/totoready.gif");
            toto.setImageResource(R.drawable.totoready);
            //params.width=116;
            //params.height=120;
        }else{
            toto.setImageResource(R.drawable.totospie);
            //toto.loadUrl("file:///android_asset/totospie.gif");
            //params.width=218;
            //params.height=120;
        }
        toto.setLayoutParams(params);
        //toto.getSettings().setLoadWithOverviewMode(true);
        //toto.getSettings().setUseWideViewPort(true);

    }
    public Point goleminaEkran(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public void poraka(String sto){
        GifImageView poraka = (GifImageView) findViewById(R.id.porakaNadToto);
        RelativeLayout lay = (RelativeLayout) findViewById(R.id.porakaLayout);
        if(sto.equals("show")){
            poraka.setVisibility(View.VISIBLE);
            lay.setVisibility(View.VISIBLE);
        }else {
            poraka.setVisibility(View.GONE);
            lay.setVisibility(View.GONE);
        }
    }
    public void poraka(Integer id, String sto, Integer mili){
        final GifImageView poraka = (GifImageView) findViewById(R.id.porakaNadToto);
        final RelativeLayout lay = (RelativeLayout) findViewById(R.id.porakaLayout);
        Point p = goleminaEkran();
        int widthToto = (int)(p.x*0.48);
        if(sto.equals("show")){
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(widthToto, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if(id == 0){
                poraka.setImageResource(R.drawable.porakastart);
                lp.setMargins((p.x-widthToto)/2, 0, 0,0);
                playSound("notif0.mp3");
            }else if(id == 1){
                poraka.setImageResource(R.drawable.porakavtora);
                lp.setMargins(((p.x/2)-widthToto)/2, 0, 0,0);
                playSound("audiovtoro.mp3");
            }else if(id == 2){
                poraka.setImageResource(R.drawable.porakatreta);
                lp.setMargins(((p.x/2)-widthToto)/2, 0, 0,0);
                playSound("audiotreto.mp3");
            }else if(id == 3){
                poraka.setImageResource(R.drawable.porakacetvrta);
                lp.setMargins((p.x-widthToto)/2, 0, 0,0);
                playSound("audiocetvrto.mp3");
            }else if(id == 4){
                poraka.setImageResource(R.drawable.porakapet);
                lp.setMargins(((p.x/2)-widthToto)/2 +p.x/2, 0, 0,0);
                playSound("audiopet.mp3");
            }else if(id == 5){
                poraka.setImageResource(R.drawable.porakasest);
                lp.setMargins(((p.x/2)-widthToto)/2 +p.x/2, 0, 0,0);
                playSound("audiosest.mp3");
            }else if(id == 6){
                poraka.setImageResource(R.drawable.porakasedma);
                lp.setMargins((p.x-widthToto)/2, 0, 0,0);
                playSound("audiosedmo.mp3");
            }
            poraka.setLayoutParams(lp);
            poraka.setVisibility(View.VISIBLE);
            lay.setVisibility(View.VISIBLE);

        if(mili != 0) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    poraka.setVisibility(View.GONE);
                    lay.setVisibility(View.GONE);
                }
            }, mili);
        }
        }else{
            poraka.setVisibility(View.GONE);
            lay.setVisibility(View.GONE);
        }

    }


    public void totoSeta(){
        playRepeatingSound("play");
        //WebView toto = (WebView) findViewById(R.id.TotoWebView);
        GifImageView toto = (GifImageView) findViewById(R.id.TotoWebView);
        //ViewGroup.LayoutParams params = toto.getLayoutParams();
        //params.width = 112;
        //params.height = 120;
        //toto.setLayoutParams(params);
        //toto.getLayoutParams().width=70;
        //toto.loadUrl("file:///android_asset/totowalking.gif");
        //toto.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        toto.setImageResource(R.drawable.totowalking);
        Point size = goleminaEkran();
        final float toLeft = ((size.x/(float)2) - (toto.getWidth()/(float)3.3))*-1;
        TranslateAnimation animation = new TranslateAnimation(0.0f, toLeft, 0.0f, 0.0f);
        animation.setDuration(14000); // animation duration
        animation.setRepeatCount(1); // animation repeat count
        animation.setRepeatMode(2); // repeat animation (left to right, right to left)

        animation.setFillAfter(true);
        toto.startAnimation(animation);//your_view for mine is imageView
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!timerEnabled)return;
                //WebView toto = (WebView) findViewById(R.id.TotoWebView);
                GifImageView toto = (GifImageView) findViewById(R.id.TotoWebView);
                animation = new TranslateAnimation(0.0f, toLeft*(-1), 0.0f, 0.0f);
                animation.setDuration(14000);
                animation.setRepeatCount(1);
                animation.setRepeatMode(2);
                animation.setFillAfter(true);
                toto.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void playSound(String fileToPlay){
        //POCETOK PLAY SOUDN
        if(mp.isPlaying()) mp.stop();
        try {
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd(fileToPlay);
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // KRAJ PLAY SOUDN
    }
    public void playSoundStop(){
        if(mp.isPlaying()) mp.stop();
    }
    public void playRepeatingSound(String cmd){
        if(cmd.equals("play")) {
            if (cekori.isPlaying()) cekori.stop();
            try {
                cekori.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("cekoriRepeating.mp3");
                cekori.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                cekori.prepare();
                cekori.start();
                cekori.setLooping(true);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            cekori.stop();
        }

    }
    public void showImageGif(){
        //new random
        /*Random rand = new Random();
        int index = rand.nextInt(7)+1;
        int drawableId=getResources().getIdentifier("gif_"+String.valueOf(index), "drawable", getPackageName());*/
        final GifImageView slika = (GifImageView) findViewById(R.id.mestoZaGif);
        slika.setImageResource(R.drawable.animationdone);
        //slika.loadUrl("file:///android_asset/animationdone.gif");
        //slika.getSettings().setLoadWithOverviewMode(true);
        //slika.getSettings().setUseWideViewPort(true);
        slika.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                slika.setVisibility(View.GONE);
            }
        }, 5000);


    }
    public void resetCounting(){
        TextView counter = (TextView) findViewById(R.id.counterView);
        timerEnabled=false;
        counter.setText("60");

    }
    public void checkFirstTime(){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        //editor.remove("email");
        //editor.remove("lang");
        //editor.remove("lastTimePoeni");
        editor.apply();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String lanGG = prefs.getString("lang", null);
        if(email == null || lanGG == null) {
            Intent intent = new Intent(getApplicationContext(), firstFinish.class);
            startActivity(intent);
        }else{
            Language.changeLang(lanGG);
        }

    }
    public void setDefaults(){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        ProgressBar PROG = (ProgressBar) findViewById(R.id.progressBar);
        int progress = prefs.getInt("progress", 0);
        PROG.setProgress(progress);
        int srca = prefs.getInt("srca",0);
        ImageView srceToChange;
        if(srca > 6)srca=6;

        for(int i=2; i<srca+2; i++){
            int id = getResources().getIdentifier("imageView"+i, "id", getPackageName());
            srceToChange = (ImageView) findViewById(id);
            srceToChange.setImageResource(R.drawable.fillheart);
        }
    }
    @Override
    public void onBackPressed() {
        WebView webV = (WebView) findViewById(R.id.webview);
        String curUrl = webV.getUrl().toString();
        if(!Language.getUrl().equals(curUrl)){
            kliknatoBack=0;
            webV.goBack();
            return;
        }else{
            kliknatoBack++;
            if(kliknatoBack == 2) {
                super.onBackPressed();
                playSoundStop();

            }else{

                Toast.makeText(this, Language.getString("clickOneMoreTime"), Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void goToSettings(View view){
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }
}

