package com.lpdw.zurvivescompanion.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.lpdw.zurvivescompanion.R;

/**
 * Created by CAJUSTE Alain on 24/06/2015.
 */
public class ProfileFragment extends BaseFragment {

    public ProfileFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_profile, container, false);
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
        Dialog dialog = new Dialog(getActivity(), getString(R.string.dialog_title_confirmation), getString(R.string.dialog_message_confirmation));
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        dialog.addCancelButton(getString(R.string.dialog_cancel_button));
        dialog.show();
        ButtonFlat acceptButton = dialog.getButtonAccept();
        acceptButton.setText(getString(R.string.dialog_accept_button));
        return true;
    }

}
