package com.sw.demo.weatherlist;

import android.app.Application;

/**
 * Created by SimonWong on 15/11/2016.
 */

public class CustomApplication extends Application {

    private final static String TAG = "WeatherListApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.d(TAG, "onCreate()");
    }

    @Override
    public void onTerminate() {
        android.util.Log.d(TAG, "onTerminate()");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        android.util.Log.d(TAG, "onLowMemory()");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        android.util.Log.d(TAG, "onTrimMemory()");
    }
}
