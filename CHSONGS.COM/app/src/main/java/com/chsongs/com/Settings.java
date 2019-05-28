package com.chsongs.com;

import android.content.SharedPreferences;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
