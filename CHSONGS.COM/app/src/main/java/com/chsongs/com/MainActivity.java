package com.chsongs.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
/*

Koga vrtime ekran, da si ostane istata stranica!
Settings gore levo,
Najdolu imame NajLevo settings, BARce za polenje 1-30, tajmer....


 */
public class MainActivity extends AppCompatActivity {

    private int kliknatoBack=0;
    public static final String MY_PREFS_NAME = "appConfiguration";
    WebView view;
    SwipeRefreshLayout mySwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*google ad */
        MobileAds.initialize(this, "ca-app-pub-2192524803493760~4977572741");
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        /*firsttime*/
        checkFirstTime();
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
                if (progress == 100) {
                    mySwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        view.loadUrl(Language.getUrl());
    }
    @Override
    public void onResume(){
        super.onResume();
        /*WebView webV = (WebView) findViewById(R.id.webview);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String Langg = prefs.getString("lang", "Македонски");
        Language.changeLang(Langg);*/
        Bundle b = getIntent().getExtras();
        if (b != null) {
            view.loadUrl(b.getString("urlToGo"));
        }
        if(Language.changedLang){
            view.loadUrl(Language.getUrl()); Language.changedLang = false;
        }


    }
    public void checkFirstTime(){
 /*      SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.remove("email");
        editor.remove("lang");
        editor.apply();
*/
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
