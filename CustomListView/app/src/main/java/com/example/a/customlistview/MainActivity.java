package com.example.a.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    class MyData{
        int itemIcon;
        String title;
        String desc;
    }
    ArrayList<MyData> myList = new ArrayList<>();
    private void initData(){
        for(int i=0; i<100; i++){
            MyData myData = new MyData();
            myData.title = "title "+i;
            myData.desc = "desc "+i;
            myData.itemIcon = i%2 == 0 ? R.mipmap.ic_launcher
                    : R.mipmap.ic_launcher_round;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
