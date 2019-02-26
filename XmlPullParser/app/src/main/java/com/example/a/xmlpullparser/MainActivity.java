package com.example.a.xmlpullparser;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

// http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000
public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    class WeatherData{
        int day;
        int hour;
        float temp;
        String wfKor;

        @Override
        public String toString() {
            return "day : "+day + " hour : "+hour+" temp : "+temp+
                    " wfKor : "+wfKor;
        }
    }
    ArrayList<WeatherData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnWeather).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyPullParserTask task = new MyPullParserTask();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000");
    }

    class MyPullParserTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
