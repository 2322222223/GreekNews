package com.renlz.jiyun.greeknews.myapp;

import android.app.Application;

/**
 * Created by Administrator on 2018/12/27.
 */

public class MyApp extends Application {

    public static MyApp sMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp=this;
    }
}
