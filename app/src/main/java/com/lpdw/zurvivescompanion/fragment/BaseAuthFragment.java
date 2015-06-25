package com.lpdw.zurvivescompanion.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;
import com.lpdw.zurvivescompanion.service.AuthService;
import com.lpdw.zurvivescompanion.views.widgets.ProgressDialog;
import com.octo.android.robospice.SpiceManager;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by CAJUSTE Alain on 22/06/2015.
 */
public abstract class BaseAuthFragment extends Fragment {
    protected ProgressDialog progressDialog;
    protected SpiceManager mAuthServiceSpiceManager;
    protected ZurvivesPreference mZurvivesPreference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZurvivesPreference = ZurvivesPreference.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuthServiceSpiceManager = new SpiceManager(AuthService.class);
        mAuthServiceSpiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuthServiceSpiceManager.shouldStop();
    }

    public void requestFailure(String message) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

    protected SpiceManager getSpiceManager() {
        return mAuthServiceSpiceManager;
    }
}
