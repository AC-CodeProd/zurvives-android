package com.lpdw.zurvivescompanion.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.fragment.RegisterFragment;
import com.lpdw.zurvivescompanion.fragment.SignInFragment;
import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;

public class AuthActivity extends BaseActivity {


    private static Fragment mFragment = null;
    private static FragmentManager mFragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mToolbar = ((Toolbar) findViewById(R.id.toolbar));
        mFragmentManager = getSupportFragmentManager();
        if (!mZurvivesPreference.isRegister()) {
            updateContent(1);
        } else {
            mFragment = new SignInFragment();
            updateContent(2);
        }
    }

    public static void updateContent(int value) {
        switch (value) {
            case 1:
                mToolbar.setTitle(R.string.fragment_register_title);
                mFragment = new RegisterFragment();
                mFragmentManager.beginTransaction()
                        .replace(R.id.container_auth, mFragment)
                        .commit();
                break;
            case 2:

                mToolbar.setTitle(R.string.fragment_sign_in_title);
                mFragment = new SignInFragment();
                mFragmentManager.beginTransaction()
                        .replace(R.id.container_auth, mFragment)
                        .commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(AuthActivity.this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
