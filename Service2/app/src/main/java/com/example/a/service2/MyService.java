package com.example.a.service2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {
    public MyService() {
    }

    public class MyBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        }
    }
    MyBinder myBinder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    Random random = new Random();
    public int getRandomNumber(){
        return random.nextInt(100);
    }

}
