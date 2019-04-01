package com.example.yemece.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yemece.R;
import com.example.yemece.data.Imc;
import com.example.yemece.data.ImcDAO;

public class EditImcActivity extends Activity {

    private ImcDAO imcDAO;
    private EditText edtSituacao;
    private EditText edtPeso;
    private EditText edtAltura;

    private Imc imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editimc);

        edtSituacao = findViewById(R.id.edt_situacao);
        edtPeso = findViewById(R.id.edt_peso);
        edtAltura = findViewById(R.id.edt_altura);

        imcDAO = ImcDAO.getInstance(this);

        imc = (Imc) getIntent().getSerializableExtra("imc");

        if (imc != null) {
            edtSituacao.setText(imc.getSituacao());
            edtPeso.setText(String.valueOf(imc.getPeso()));
            edtAltura.setText(String.valueOf(imc.getAltura()));

        }
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void process(View view) {
        String situacao = edtSituacao.getText().toString();
        double peso = Double.parseDouble(edtPeso.getText().toString());
        double altura = Double.parseDouble(edtAltura.getText().toString());

        String msg;

        if (imc == null) {
            Imc imc = new Imc(situacao, peso, altura);
            imcDAO.save(imc);
            msg = "IMC gravado com ID = " + imc.getId();

        } else {
            imc.setSituacao(situacao);
            imc.setPeso(peso);
            imc.setAltura(altura);

            imcDAO.update(imc);
            msg = "IMC atualizado com ID = " + imc.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
}
