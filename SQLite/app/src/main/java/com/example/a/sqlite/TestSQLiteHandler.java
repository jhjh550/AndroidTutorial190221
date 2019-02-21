package com.example.a.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestSQLiteHandler {
    TestSQLiteOpenHelper helper;

    public TestSQLiteHandler(Context context) {
        helper = new TestSQLiteOpenHelper(context, "people", null, 1);
    }

    public void insert(String name, int age, String address){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("address", address);
        db.insert("student", null, values);
    }

    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("student", "name = ?",
                new String[]{name});
    }

    public void update(String name, int newAge){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age", newAge);
        db.update("student", values, "name = ?",
                new String[]{name});
    }

    public void selectAll(){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query("student", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String address = cursor.getString(cursor.getColumnIndex("address"));

            Log.d("student", name + age + address);
        }
    }
}
