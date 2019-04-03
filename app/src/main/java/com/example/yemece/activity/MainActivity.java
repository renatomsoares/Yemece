package com.example.yemece.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.yemece.R;
import com.example.yemece.data.FirebaseConnection;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Yemece");


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);
        databaseReference = FirebaseConnection.getFirebase();
        databaseReference.getRef();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.acao_sair) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
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