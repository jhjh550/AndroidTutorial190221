package com.example.a.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestSQLiteHandler handler = new TestSQLiteHandler(this);
        handler.insert("kim", 10, "seoul");
        handler.insert("가나다", 11, "부산");
        handler.update("kim", 12);
        handler.selectAll();
    }
}
