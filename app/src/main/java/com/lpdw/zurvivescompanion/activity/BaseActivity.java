package com.lpdw.zurvivescompanion.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;

/**
 * Created by CAJUSTE Alain on 16/06/2015.
 */
public abstract class BaseActivity extends ActionBarActivity {
    protected ZurvivesPreference mZurvivesPreference;
    protected static Toolbar mToolbar;
    protected static CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZurvivesPreference = ZurvivesPreference.getInstance();
        Log.v("BaseActivity", "onCreate");
    }
}
