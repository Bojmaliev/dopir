package org.eu.net.pole.polezaglasanje;

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


        MobileAds.initialize(this, "ca-app-pub-2192524803493760~2164041254");
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        /*firsttime*/
        Language.changeLang("Македонски");
        /*webview*/
        view=(WebView) this.findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setDisplayZoomControls(false);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl(Language.getUrl());
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
    public void vidiRez(View v){
       view.loadUrl("javascript:rezultati()");
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
