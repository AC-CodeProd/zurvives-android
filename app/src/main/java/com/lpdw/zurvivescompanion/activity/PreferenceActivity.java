package com.lpdw.zurvivescompanion.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.fragment.SettingsFragment;

/**
 * Created by CAJUSTE Alain on 31/05/2015.
 */
public class PreferenceActivity extends ActionBarActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemePreference);
        setContentView(R.layout.activity_preference);
        mToolbar = ((Toolbar) findViewById(R.id.toolbar));
        mToolbar.setTitle(R.string.action_settings);
        getFragmentManager().beginTransaction()
                .replace(R.id.container_preference, new SettingsFragment())
                .commit();

    }
}
