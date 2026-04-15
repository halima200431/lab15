package com.example.sql.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "etudiants.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ETUDIANT = "etudiant";
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_PRENOM = "prenom";
    public static final String COL_PHONE = "phone";

    private static final String CREATE_TABLE_ETUDIANT = "CREATE TABLE " + TABLE_ETUDIANT + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOM + " TEXT NOT NULL, " +
            COL_PRENOM + " TEXT NOT NULL, " +
            COL_PHONE + " TEXT NOT NULL);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ETUDIANT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANT);
        onCreate(db);
    }
}
