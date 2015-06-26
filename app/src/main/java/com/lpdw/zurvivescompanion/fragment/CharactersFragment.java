package com.lpdw.zurvivescompanion.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.activity.MainActivity;
import com.lpdw.zurvivescompanion.data.User;

/**
 * Created by CAJUSTE Alain on 24/06/2015.
 */
public class CharactersFragment  extends BaseFragment {

    public CharactersFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_characters, container, false);
        initView(mView);
        return mView;
    }

    public void initView(View mView) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Boolean onBackPressed() {
        MainActivity.updateContent(1);
        return true;
    }

}
