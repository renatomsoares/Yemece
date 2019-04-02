package com.example.yemece.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yemece.R;

public class ShowColorFragment extends Fragment {

    private TextView colorShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_color_fragment,
                container,
                false);
        colorShow = view.findViewById(R.id.colorShow);
        return view;
    }


    public void setColorShow(int buttonName){

        switch(buttonName) {
            case R.id.btnBlack:
                colorShow.setBackgroundColor(Color.BLACK);
                break;
            case R.id.btnBlue:
                colorShow.setBackgroundColor(Color.BLUE);
                break;
            case R.id.btnGreen:
                colorShow.setBackgroundColor(Color.GREEN);
                break;
            case R.id.btnRed:
                colorShow.setBackgroundColor(Color.RED);
                break;
            case R.id.btnYellow:
                colorShow.setBackgroundColor(Color.YELLOW);
                break;
        }
    }
}
