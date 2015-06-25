package com.lpdw.zurvivescompanion;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by CAJUSTE Alain on 01/06/2015.
 */
public class ZurvivesApp extends Application {
    private static String TAG = ZurvivesApp.class.getSimpleName();
    private static ZurvivesApp mZurvivesApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mZurvivesApp = this;
        Context context = getApplicationContext();
        PreferenceManager.setDefaultValues(this, R.xml.preferences, true);

    }

    synchronized public static ZurvivesApp getApplication() {
        return mZurvivesApp;
    }
}
