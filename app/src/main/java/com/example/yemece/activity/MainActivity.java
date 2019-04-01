package com.example.yemece.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

}