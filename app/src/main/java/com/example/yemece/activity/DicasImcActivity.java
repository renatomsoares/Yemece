package com.example.yemece.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yemece.R;
import com.example.yemece.fragments.DicasFragment;
import com.example.yemece.fragments.ExibeDicaFragment;

public class DicasImcActivity extends AppCompatActivity implements DicasFragment.OnGetButtonId{

    private ExibeDicaFragment showColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicasimc);
        showColorFragment = (ExibeDicaFragment) getSupportFragmentManager().findFragmentById(R.id.show_color_frag);
    }


    @Override
    public void buttonId(int buttonName) {
        showColorFragment.setColorShow(buttonName);
    }
}
