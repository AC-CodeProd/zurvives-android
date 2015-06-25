package com.lpdw.zurvivescompanion.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;

/**
 * Created by CAJUSTE Alain on 25/06/2015.
 */
public abstract class BaseFragment extends Fragment {
    protected ZurvivesPreference mZurvivesPreference;


    public Boolean onBackPressed() {
        return false;
    }
}
