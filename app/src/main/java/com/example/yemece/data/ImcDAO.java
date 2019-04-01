package com.example.yemece.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ImcDAO {

    private static ImcDAO instance;
    private SQLiteDatabase db;

    private ImcDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static ImcDAO getInstance(Context context) {
        if (instance == null) {
            instance = new ImcDAO(context.getApplicationContext());
        }

        return instance;
    }

    public List<Imc> list() {
        String[] columns = {
                ImcsContract.Columns._ID,
                ImcsContract.Columns.SITUACAO,
                ImcsContract.Columns.PESO,
                ImcsContract.Columns.ALTURA,
                ImcsContract.Columns.DATAREGISTRO
        };

        List<Imc> imcs = new ArrayList<>();

        try(
                Cursor c = db.query(ImcsContract.TABLE_NAME, columns, null, null,
                        null, null, ImcsContract.Columns._ID + " DESC")) {

            if (c.moveToFirst()) {

                do {
                    Imc imc = ImcDAO.fromCursor(c);
                    imcs.add(imc);

                } while(c.moveToNext());

            }
        }


        return imcs;
    }

    public void save(Imc imc) {

        ContentValues values = new ContentValues();
        values.put(ImcsContract.Columns.SITUACAO, imc.getSituacao());
        values.put(ImcsContract.Columns.PESO, imc.getPeso());
        values.put(ImcsContract.Columns.ALTURA, imc.getAltura());
        values.put(ImcsContract.Columns.DATAREGISTRO, imc.getDataRegistro().toString());

        long id = db.insert(ImcsContract.TABLE_NAME, null, values);

        imc.setId((int) id);
    }

    public void update(Imc imc) {

        ContentValues values = new ContentValues();
        values.put(ImcsContract.Columns.SITUACAO, imc.getSituacao());
        values.put(ImcsContract.Columns.PESO, imc.getPeso());
        values.put(ImcsContract.Columns.ALTURA, imc.getAltura());


        db.update(
                ImcsContract.TABLE_NAME,
                values,
                ImcsContract.Columns._ID + "=?",
                new String[]{String.valueOf(imc.getId())}
        );
    }

    public void delete(Imc imc) {

        db.delete(ImcsContract.TABLE_NAME,
                ImcsContract.Columns._ID+"=?",
                new String[]{String.valueOf(imc.getId())}
        );
    }

    private static Imc fromCursor(Cursor c) {

        int id = c.getInt(c.getColumnIndex(ImcsContract.Columns._ID));
        String situacao = c.getString(c.getColumnIndex(ImcsContract.Columns.SITUACAO));
        double peso = c.getDouble(c.getColumnIndex(ImcsContract.Columns.PESO));
        double altura = c.getDouble(c.getColumnIndex(ImcsContract.Columns.ALTURA));
        String dataRegistro = c.getString(c.getColumnIndex(ImcsContract.Columns.DATAREGISTRO));

        return new Imc(id, situacao, peso, altura, dataRegistro);
    }
}


