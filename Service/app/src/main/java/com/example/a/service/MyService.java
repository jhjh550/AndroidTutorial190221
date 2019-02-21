package com.example.a.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service", "destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
