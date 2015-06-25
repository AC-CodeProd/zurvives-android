package com.lpdw.zurvivescompanion.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lpdw.zurvivescompanion.R;

/**
 * Created by CAJUSTE Alain on 31/05/2015.
 */
public class ZurvivesPreference {
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private static ZurvivesPreference _instance = null;


    public static ZurvivesPreference onInitInstance(Context mContext) {
        if (_instance == null) {
            _instance = new ZurvivesPreference(mContext);
        }
        return _instance;
    }

    public static ZurvivesPreference getInstance() {

        if (_instance == null) {
            return null;
        }
        return _instance;
    }

    private ZurvivesPreference(Context mContext) {
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        this.mContext = mContext;
        if (!isFirstLaunch()) {
            this.onInit();
        }

    }

    private void onInit() {
        this.setFirstLaunch(true);
        this.setRegister(false);
    }

    public Boolean isDebugging() {
        return getBoolean(R.string.key_pref_debug, mContext.getResources().getBoolean(R.bool.pref_enabled_default));
    }


    public String getIpDebugging() {
        return getString(R.string.key_pref_debug_edittext, mContext.getString(R.string.pref_ip_default));
    }

    public Boolean isFirstLaunch() {
        return getBoolean(R.string.key_pref_first_launch, mContext.getResources().getBoolean(R.bool.pref_enabled_default));
    }

    public Boolean isRegister() {
        return getBoolean(R.string.key_pref_register, mContext.getResources().getBoolean(R.bool.pref_enabled_default));
    }

    public void setFirstLaunch(Boolean value) {
        putBoolean(R.string.key_pref_first_launch, value);
    }

    public void setRegister(Boolean value) {
        putBoolean(R.string.key_pref_register, value);
    }


    private boolean getBoolean(int prefKeyId, boolean prefDefault) {
        return mSharedPreferences.getBoolean(mContext.getString(prefKeyId), prefDefault);
    }

    private String getString(int prefKeyId, String defaultVal) {
        return mSharedPreferences.getString(mContext.getString(prefKeyId), defaultVal);
    }

    private int getInt(int prefKeyId, int defaultVal) {
        return mSharedPreferences.getInt(mContext.getString(prefKeyId), defaultVal);
    }

    public void putString(int prefKeyId, String newVal) {
        SharedPreferences.Editor settings = mSharedPreferences.edit();
        settings.putString(mContext.getString(prefKeyId), newVal);
        settings.commit();

    }

    public void putBoolean(int prefKeyId, Boolean newVal) {
        SharedPreferences.Editor settings = mSharedPreferences.edit();
        settings.putBoolean(mContext.getString(prefKeyId), newVal);
        settings.commit();
    }
}
