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

public class DicasFragment extends Fragment implements View.OnClickListener{

    private OnGetButtonId listener;

    private Button genericButton;

    private Button dica1;
    private Button dica2;
    private Button dica3;
    private Button dica4;
    private Button dica5;


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

        View view = inflater.inflate(R.layout.dicas_fragment,
                container,
                false);

        dica1 = view.findViewById(R.id.btnDica1);
        dica1.setOnClickListener(this);

        dica2 = view.findViewById(R.id.btnDica2);
        dica2.setOnClickListener(this);

        dica3 = view.findViewById(R.id.btnDica3);
        dica3.setOnClickListener(this);

        dica4 = view.findViewById(R.id.btnDica4);
        dica4.setOnClickListener(this);

        dica5 = view.findViewById(R.id.btnDica5);
        dica5.setOnClickListener(this);

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
