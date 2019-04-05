package com.example.yemece.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yemece.R;
import com.example.yemece.data.Imc;
import com.example.yemece.data.ImcDAO;
import com.example.yemece.enums.GravidadeIndiceImc;
import com.example.yemece.helpers.CalculoImcHelper;
import com.example.yemece.utils.NotificationUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditImcActivity extends Activity {

    private ImcDAO imcDAO;
    private EditText edtPeso;
    private EditText edtAltura;

    private FirebaseAuth firebaseAuth;


    private Imc imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.firebaseAuth = FirebaseAuth.getInstance();

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void process(View view) {

        if (validaImc()) {

            double peso = Double.parseDouble(edtPeso.getText().toString());
            double altura = Double.parseDouble(edtAltura.getText().toString());
            CalculoImcHelper calculoImc = new CalculoImcHelper(peso, altura);

            String msg;
            String situacao = ". Parabéns! Você está no peso ideal.";


            if (calculoImc.getGravidadeIndice() != GravidadeIndiceImc.NENHUMA) {
                if (calculoImc.getIndice() > 24.9) {
                    situacao = ". Você precisa perder " + calculoImc.getDiferencaPesoParaSituacaoIdeal() + "kg para atingir o peso ideal.";
                } else {
                    situacao = ". Você precisa ganhar " + calculoImc.getDiferencaPesoParaSituacaoIdeal() + "kg para atingir o peso ideal.";
                }
            }


            if (imc == null) {

                Date hoje = new Date();
                Imc imc = new Imc(calculoImc.getSituacao(), peso, altura, new SimpleDateFormat("dd-MM-yyyy (hh:mm)").format(hoje));


                if (firebaseAuth.getCurrentUser() != null) {
                    imc.setUsuarioCadastro(firebaseAuth.getCurrentUser().getUid());
                }

                imcDAO.save(imc);
                msg = "IMC registrado em = " + imc.getDataRegistro() + situacao;

            } else {
                imc.setSituacao(calculoImc.getSituacao());
                imc.setPeso(peso);
                imc.setAltura(altura);
                // A atualização mantém a data de registro.

                imcDAO.update(imc);
                msg = "O IMC registrado em = " + imc.getDataRegistro() + " foi atualizado" +  situacao;
            }

            if (calculoImc.getGravidadeIndice() == GravidadeIndiceImc.ALTA) {
                this.notify(view);
            }

            final Toast toast = Toast.makeText(this, msg,Toast.LENGTH_SHORT);
            toast.show();

            new CountDownTimer(5000, 1000)
            {

                public void onTick(long millisUntilFinished) {toast.show();}
                public void onFinish() {toast.show();}

            }.start();

            //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }



    }


    private boolean validaImc() {

        boolean validado = true;

        System.out.print("aquii: " + edtAltura.getText().toString());
        System.out.print("aquii: " + edtPeso.getText().toString());

        if (edtPeso.getText().toString().isEmpty()) {
            edtPeso.setError("O campo Peso não pode ser vazio.");
            validado = false;
        }

        if (edtAltura.getText().toString().isEmpty()) {
            edtAltura.setError("O campo Altura não pode ser vazio.");
            validado = false;
        }

        return validado;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notify(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationUtils.getChannelId(this));

        builder.setContentTitle("Yemece");
        builder.setContentText("Sua gravidade é alta. Para um maior controle do seu IMC ative um alerta diário clicando aqui.");
        builder.setSmallIcon(android.R.drawable.sym_action_chat);

        Intent intent = new Intent(this, MessageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        Notification notification =  builder.build();
        NotificationManager nm =  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(50,notification);
    }
}
