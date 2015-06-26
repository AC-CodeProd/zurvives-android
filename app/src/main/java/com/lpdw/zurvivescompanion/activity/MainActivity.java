package com.lpdw.zurvivescompanion.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.data.User;
import com.lpdw.zurvivescompanion.fragment.BaseFragment;
import com.lpdw.zurvivescompanion.fragment.CharactersFragment;
import com.lpdw.zurvivescompanion.fragment.EquipmentsFragment;
import com.lpdw.zurvivescompanion.fragment.ProfileFragment;
import com.lpdw.zurvivescompanion.fragment.RegisterFragment;
import com.lpdw.zurvivescompanion.fragment.SignInFragment;
import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;
import com.lpdw.zurvivescompanion.views.adapters.NavigationRecyclerViewAdapter;


public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private User mUser;
    private int currentMenu = 0;
    private static Fragment mFragment = null;
    private static FragmentManager mFragmentManager = null;

    private String TITLES[] = {"Profil", /*"Mes personnages",*/ "Les équipements"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = ((Toolbar) findViewById(R.id.toolbar));
        mToolbar.setTitle(R.string.app_name);
        mTitle = mToolbar.getTitle();
        this.setSupportActionBar(mToolbar);
        mUser = User.getInstance();


        mRecyclerView = (RecyclerView) findViewById(R.id.navigation_drawer);
        mRecyclerView.setHasFixedSize(true);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mAdapter = new NavigationRecyclerViewAdapter(this, TITLES, mUser, new NavigationRecyclerViewAdapter.IViewHolderClicks() {
            @Override
            public void onLogoutClick(View view) {
                Dialog dialog = new Dialog((Activity) view.getContext(), getString(R.string.dialog_title_confirmation), getString(R.string.dialog_message_confirmation));
                dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i;
                        i = new Intent(MainActivity.this, AuthActivity.class);
                        startActivity(i);
                        User.onDestroyUser();
                        finish();
                    }
                });
                dialog.addCancelButton(getString(R.string.dialog_cancel_button));
                dialog.show();
                ButtonFlat acceptButton = dialog.getButtonAccept();
                acceptButton.setText(getString(R.string.dialog_accept_button));
            }
        });
        //mAdapter.setListener(new NavigationRecyclerViewAdapter.IViewHolderClicks());
        mRecyclerView.setAdapter(mAdapter);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }
        };
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mGestureDetector.onTouchEvent(e) && rv.getChildPosition(child) != 0) {
                    mDrawer.closeDrawers();
                    onTouchDrawer(rv.getChildPosition(child));
                    return true;
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    @Override
    public void onStart() {
        super.onStart();
        mFragmentManager = getSupportFragmentManager();
        updateContent(1);

    }

    public static void updateContent(int value) {
        switch (value) {
            case 1:
                mToolbar.setTitle(R.string.fragment_profile_title);
                mFragment = new ProfileFragment();
                mFragmentManager.beginTransaction()
                        .replace(R.id.container, mFragment)
                        .commit();
                break;
            /*case 2:
                mToolbar.setTitle(R.string.fragment_characters_title);
                mFragment = new CharactersFragment();
                mFragmentManager.beginTransaction()
                        .replace(R.id.container, mFragment)
                        .commit();
                break;*/
            case 2:
                mToolbar.setTitle(R.string.fragment_equipments_title);
                mFragment = new EquipmentsFragment();
                mFragmentManager.beginTransaction()
                        .replace(R.id.container, mFragment)
                        .commit();
                break;
        }
    }

    public void onTouchDrawer(final int position) {
        if (currentMenu == position) return;
        updateContent(position);
    }

    public void onNavigationDrawerItemSelected(final Fragment fragment) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null)
            mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerToggle != null) {
                    mDrawerToggle.onOptionsItemSelected(item);

                    if (mDrawer.isDrawerOpen(mRecyclerView))
                        mDrawer.closeDrawer(mRecyclerView);
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (mFragment != null && !mDrawer.isDrawerOpen(mRecyclerView)) {
            if (((BaseFragment) mFragment).onBackPressed())
                return;
            else
                super.onBackPressed();
        } else if (mDrawer.isDrawerOpen(mRecyclerView)) {
            mDrawer.closeDrawer(mRecyclerView);
            return;
        }
        super.onBackPressed();
    }

}
