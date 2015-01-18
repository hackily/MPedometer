package com.unkani.placeholder;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.text.DateFormat;

/**
 * Created by default on 1/17/2015.
 */
public class CounterService extends Service{
    private static final String TAG = "CounterService";
    public static final String BROADCAST_ACTION = "com.unkani.placeholder.counter";
    private final Handler handler = new Handler();
    Intent intent;
    int counter = 0;

    @Override
    public void onCreate(){
        super.onCreate();
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onStart(Intent intent, int startId){
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 1000); // 5 seconds
        }
    };

    private void DisplayLoggingInfo(){
        Log.d(TAG, "entered DisplayLoggingInfo");
        intent.putExtra("time", DateFormat.getDateInstance());
        intent.putExtra("counter", String.valueOf(counter=counter+5));
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onDestroy(){
        handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
    }

}
