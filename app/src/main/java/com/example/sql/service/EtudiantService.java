package com.example.sql.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.sql.classes.Etudiant;
import com.example.sql.util.MySQLiteHelper;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService {
    private SQLiteDatabase db;
    private MySQLiteHelper helper;

    public EtudiantService(Context context) {
        helper = new MySQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void addEtudiant(Etudiant e) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COL_NOM, e.getNom());
        values.put(MySQLiteHelper.COL_PRENOM, e.getPrenom());
        values.put(MySQLiteHelper.COL_PHONE, e.getPhone());
        db.insert(MySQLiteHelper.TABLE_ETUDIANT, null, values);
    }

    public void deleteEtudiant(int id) {
        db.delete(MySQLiteHelper.TABLE_ETUDIANT, MySQLiteHelper.COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Etudiant getEtudiant(int id) {
        Cursor cursor = db.query(MySQLiteHelper.TABLE_ETUDIANT, null, MySQLiteHelper.COL_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Etudiant e = new Etudiant(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            cursor.close();
            return e;
        }
        return null;
    }

    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MySQLiteHelper.TABLE_ETUDIANT, null);
        if (cursor.moveToFirst()) {
            do {
                etudiants.add(new Etudiant(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return etudiants;
    }
}
