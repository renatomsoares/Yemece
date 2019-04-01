package com.example.yemece.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String BD_NAME = "imcsdb";
    public static final int BD_VERSION = 1;

    private static String SQL_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, " +
                    "%s DOUBLE NOT NULL, " +
                    "%s DOUBLE NOT NULL, " +
                    "%s TEXT NOT NULL )",
            ImcsContract.TABLE_NAME,
            ImcsContract.Columns._ID,
            ImcsContract.Columns.SITUACAO,
            ImcsContract.Columns.PESO,
            ImcsContract.Columns.ALTURA,
            ImcsContract.Columns.DATAREGISTRO);

    private static String SQL_DROP = "DROP TABLE IF EXISTS " + ImcsContract.TABLE_NAME;

    private static DBHelper instance;

    private DBHelper(Context context) {

        super(context, BD_NAME, null, BD_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }
}
