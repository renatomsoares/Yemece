package com.example.yemece.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public final class FirebaseConnection {
    private static DatabaseReference firebase;

    private FirebaseConnection(){}

    public static DatabaseReference getFirebase(){
        if( firebase == null ){
            firebase = FirebaseDatabase.getInstance().getReference();
        }

        return( firebase );
    }
}