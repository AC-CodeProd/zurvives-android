package com.lpdw.zurvivescompanion.service;

import com.lpdw.zurvivescompanion.api.Auth;
import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;
import com.lpdw.zurvivescompanion.utils.Function;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by CAJUSTE Alain on 15/06/2015.
 */
public class AuthService extends RetrofitGsonSpiceService {
    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Auth.class);
    }

    @Override
    protected String getServerUrl() {
        return Function.getServerUrl();
    }
}