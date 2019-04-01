package com.example.yemece.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yemece.R;
import com.example.yemece.data.Imc;
import com.example.yemece.data.ImcDAO;
import com.example.yemece.helpers.CalculoImcHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditImcActivity extends Activity {

    private ImcDAO imcDAO;
    private EditText edtPeso;
    private EditText edtAltura;

    private Imc imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editimc);

        edtPeso = findViewById(R.id.edt_peso);
        edtAltura = findViewById(R.id.edt_altura);

        imcDAO = ImcDAO.getInstance(this);

        imc = (Imc) getIntent().getSerializableExtra("imc");

        if (imc != null) {
            edtPeso.setText(String.valueOf(imc.getPeso()));
            edtAltura.setText(String.valueOf(imc.getAltura()));

        }
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void process(View view) {
        double peso = Double.parseDouble(edtPeso.getText().toString());
        double altura = Double.parseDouble(edtAltura.getText().toString());
        CalculoImcHelper calculoImc = new CalculoImcHelper(peso, altura);

        String msg;

        if (imc == null) {

            Date hoje = new Date();
            Imc imc = new Imc(calculoImc.getSituacao(), peso, altura, new SimpleDateFormat("dd-MM-yyyy (hh:mm)").format(hoje));
            imcDAO.save(imc);
            msg = "IMC gravado com ID = " + imc.getId();

        } else {
            imc.setSituacao(calculoImc.getSituacao());
            imc.setPeso(peso);
            imc.setAltura(altura);
            // A atualização mantém a data de registro.

            imcDAO.update(imc);
            msg = "IMC atualizado com ID = " + imc.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
}
