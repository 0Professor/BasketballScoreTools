package com.example.basketballscoretools.base;

import android.app.Application;

import com.example.basketballscoretools.database.DataBaseUtils;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseUtils.getInstance(getApplicationContext());
    }
}
