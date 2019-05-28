package org.eu.dopis.dopir;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "appConfiguration";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setLanguages();
        setDefaultValues();

    }
    public void setLanguages(){
        TextView toChange = (TextView) findViewById(R.id.settings);
        toChange.setText(Language.getString("settings"));

        toChange = (TextView) findViewById(R.id.chooseLang);
        toChange.setText(Language.getString("chooseLang"));

        toChange = (TextView) findViewById(R.id.sevenNotif);
        toChange.setText(Language.getString("sevenNotif"));

        toChange = (TextView) findViewById(R.id.eighteenNotif);
        toChange.setText(Language.getString("eighteenNotif"));

        toChange = (TextView) findViewById(R.id.otherNotif);
        toChange.setText(Language.getString("otherNotif"));

        toChange = (TextView) findViewById(R.id.notiflications);
        toChange.setText(Language.getString("notiflications"));

        toChange = (TextView) findViewById(R.id.save);
        toChange.setText(Language.getString("save"));

        toChange = (TextView) findViewById(R.id.gameRules);
        if (Build.VERSION.SDK_INT >= 24) {
            toChange.setText(Html.fromHtml(Language.getString("gameRules"), Html.FROM_HTML_MODE_LEGACY));
            toChange = (TextView) findViewById(R.id.gameRules1);
            toChange.setText(Html.fromHtml(Language.getString("gameRules1"), Html.FROM_HTML_MODE_LEGACY));
        } else {
            toChange.setText(Html.fromHtml(Language.getString("gameRules")));
            toChange = (TextView) findViewById(R.id.gameRules1);
            toChange.setText(Html.fromHtml(Language.getString("gameRules1")));
        }


        toChange = (TextView) findViewById(R.id.gameRulesTitle);
        toChange.setText(Language.getString("gameRulesTitle"));
        WebView marcos = (WebView) findViewById(R.id.glupco);
        marcos.loadUrl("file:///android_asset/totosettings.gif");
        marcos.getSettings().setLoadWithOverviewMode(true);
        marcos.getSettings().setUseWideViewPort(true);

    }
    public void setDefaultValues(){
        Spinner jazik = (Spinner)  findViewById(R.id.spinner);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedLanguage = prefs.getString("lang", null);
        selectValue(jazik, savedLanguage);
        Switch morning = (Switch) findViewById(R.id.sevenNotif);
        morning.setChecked(prefs.getBoolean("morningNotif", false));
        Switch night = (Switch) findViewById(R.id.eighteenNotif);
        night.setChecked(prefs.getBoolean("nightNotif", false));
        Switch other = (Switch) findViewById(R.id.otherNotif);
        other.setChecked(prefs.getBoolean("otherNotif", false));

    }
    public void returnButton(View view){
        super.onBackPressed();

    }
    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
    public void saveSettings(View view){
        Spinner language = (Spinner) findViewById(R.id.spinner);
        Switch morning  = (Switch) findViewById(R.id.sevenNotif);
        Switch night = (Switch) findViewById(R.id.eighteenNotif);
        Switch other = (Switch) findViewById(R.id.otherNotif);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("lang", language.getSelectedItem().toString());
        editor.putBoolean("morningNotif", morning.isChecked());
        editor.putBoolean("nightNotif", night.isChecked());
        editor.putBoolean("otherNotif", other.isChecked());
        editor.apply();
        Language.changeLang(language.getSelectedItem().toString());
        Language.changedLang = true;
        setLanguages();
        Toast.makeText(this, Language.getString("saved"), Toast.LENGTH_SHORT).show();
    }

}
