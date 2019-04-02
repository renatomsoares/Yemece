package com.example.yemece.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yemece.R;
import com.example.yemece.fragments.ColorsFragment;
import com.example.yemece.fragments.ShowColorFragment;

public class SimuladorImcActivity extends AppCompatActivity implements ColorsFragment.OnGetButtonId{

    private ShowColorFragment showColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simuladorimc);
        showColorFragment = (ShowColorFragment) getSupportFragmentManager().findFragmentById(R.id.show_color_frag);
    }


    @Override
    public void buttonId(int buttonName) {
        showColorFragment.setColorShow(buttonName);
    }
}
