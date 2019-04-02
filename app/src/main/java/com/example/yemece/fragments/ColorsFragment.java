package com.example.yemece.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yemece.R;

public class ColorsFragment extends Fragment implements View.OnClickListener{

    private OnGetButtonId listener;

    private Button genericButton;

    private Button btnBlack;
    private Button btnBlue;
    private Button btnGreen;
    private Button btnYellow;
    private Button btnRed;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof OnGetButtonId)){
            throw new RuntimeException("Falha.");
        }
        listener = (OnGetButtonId) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.colors_fragment,
                container,
                false);

        btnBlack = view.findViewById(R.id.btnBlack);
        btnBlack.setOnClickListener(this);

        btnBlue = view.findViewById(R.id.btnBlue);
        btnBlue.setOnClickListener(this);

        btnGreen = view.findViewById(R.id.btnGreen);
        btnGreen.setOnClickListener(this);

        btnYellow = view.findViewById(R.id.btnYellow);
        btnYellow.setOnClickListener(this);

        btnRed = view.findViewById(R.id.btnRed);
        btnRed.setOnClickListener(this);

        return view;
    }

    public interface OnGetButtonId{
        void buttonId(int id);
    }

    @Override
    public void onClick(View view) {

        genericButton = view.findViewById(view.getId());
        genericButton.setOnClickListener(this);

        if (listener != null) {
            listener.buttonId(view.getId());
        }
    }
}
