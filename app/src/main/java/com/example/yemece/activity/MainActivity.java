package com.example.yemece.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.example.yemece.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listImcs(View view) {


        Intent listImcs = new Intent(getApplicationContext(), ListImcsActivity.class);

        startActivity(listImcs);
    }

    public void editImc( View view) {

        Intent editImc = new Intent(this, EditImcActivity.class);
        startActivity(editImc);

    }

    public void exit(View view){

        finishAffinity();

    }

    public void simuladorImc(View view) {
        Intent editImc = new Intent(this, SimuladorImcActivity.class);
        startActivity(editImc);
    }

    public void tabelaClassificacao(View view) {
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/renatomsoares/Yemece/blob/master/tabela-imc.png"));
        startActivity(viewIntent);
    }

    public void alarmControl(View view) {
        Intent alarmControl = new Intent(this, AlarmControlActivity.class);
        startActivity(alarmControl);
    }
}