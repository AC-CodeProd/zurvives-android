package com.lpdw.zurvivescompanion.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;
import com.lpdw.zurvivescompanion.service.AuthService;
import com.lpdw.zurvivescompanion.views.widgets.ProgressDialog;
import com.octo.android.robospice.SpiceManager;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by CAJUSTE Alain on 25/06/2015.
 */
public abstract class BaseFragment extends Fragment {
    protected ZurvivesPreference mZurvivesPreference;
    protected SpiceManager mApiServiceSpiceManager;
    protected ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZurvivesPreference = ZurvivesPreference.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        mApiServiceSpiceManager = new SpiceManager(AuthService.class);
        mApiServiceSpiceManager.start(getActivity());
    }
    @Override
    public void onStop() {
        super.onStop();
        mApiServiceSpiceManager.shouldStop();
    }

    public void requestFailure(String message) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

    protected SpiceManager getSpiceManager() {
        return mApiServiceSpiceManager;
    }

    public Boolean onBackPressed() {
        return false;
    }
}
