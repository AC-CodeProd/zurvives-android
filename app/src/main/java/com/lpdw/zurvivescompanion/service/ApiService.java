package com.lpdw.zurvivescompanion.service;


import com.lpdw.zurvivescompanion.api.API;
import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;
import com.lpdw.zurvivescompanion.utils.Function;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by CAJUSTE Alain on 31/05/2015.
 */
public class ApiService extends RetrofitGsonSpiceService {

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(API.class);
    }

    @Override
    protected String getServerUrl() {
        return Function.getServerUrl();
    }

}
