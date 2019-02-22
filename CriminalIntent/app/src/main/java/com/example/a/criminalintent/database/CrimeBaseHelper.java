package com.example.a.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a.criminalintent.database.CrimeDbSchema.CrimeTable;
import com.example.a.criminalintent.database.CrimeDbSchema.CrimeTable.Cols;

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "crimeBase.db";
    private static final int VERSION = 1;
    public CrimeBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+CrimeTable.NAME+"("+
                "_id integer primary key autoincrement, "+
                Cols.UUID + ", " + Cols.TITLE + ", "+
                Cols.DATE + ", " + Cols.SOLVED + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
