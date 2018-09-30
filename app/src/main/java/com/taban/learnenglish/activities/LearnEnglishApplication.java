package com.taban.learnenglish.activities;

import android.app.Application;
import android.content.Context;

public class LearnEnglishApplication extends Application {
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LearnEnglishApplication.applicationContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return applicationContext;
    }
}
