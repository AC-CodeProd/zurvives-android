package com.lpdw.zurvivescompanion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.data.User;
import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;

/**
 * Created by CAJUSTE Alain on 16/06/2015.
 */
public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ZurvivesPreference.onInitInstance(this);
        mUser = User.getInstance();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i;
                if (mUser.isRememberme() && mUser.isLogged())
                    i = new Intent(SplashScreen.this, MainActivity.class);
                else
                    i = new Intent(SplashScreen.this, AuthActivity.class);

                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
