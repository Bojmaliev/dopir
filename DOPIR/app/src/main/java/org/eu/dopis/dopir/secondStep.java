package org.eu.dopis.dopir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class secondStep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step);
        getDefaults();
    }
    public void getDefaults(){
        TextView change = (TextView) findViewById(R.id.textView6);
        change.setText(Language.getString("thankYouForInstallingThisApp"));
        change = (TextView) findViewById(R.id.textView7);
        change.setText(Language.getString("guideForUsingOnMail"));
        change = (TextView) findViewById(R.id.textView8);
        change.setText(Language.getString("pleaseReadItCarefully"));
    }
    public void vratiSe(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed(){
        return;
    }
}
