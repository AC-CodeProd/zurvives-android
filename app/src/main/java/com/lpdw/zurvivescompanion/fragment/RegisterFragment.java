package com.lpdw.zurvivescompanion.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.activity.AuthActivity;
import com.lpdw.zurvivescompanion.preference.ZurvivesPreference;
import com.lpdw.zurvivescompanion.request.RegisterRequest;
import com.lpdw.zurvivescompanion.response.UserResponse;
import com.lpdw.zurvivescompanion.utils.Function;
import com.lpdw.zurvivescompanion.views.widgets.ProgressDialog;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by CAJUSTE Alain on 16/06/2015.
 */
public class RegisterFragment extends BaseAuthFragment {

    private MaterialEditText fragmentRegisterEdittextName;
    private MaterialEditText fragmentRegisterEdittextNickname;
    private MaterialEditText fragmentRegisterEdittextEmail;
    private MaterialEditText fragmentRegisterEdittextPassword;
    private MaterialEditText fragmentRegisterEdittextPasswordConfirmation;
    private ButtonRectangle fragmentRegisterButtonNewAccount;
    private TextView linkToLogin;


    public RegisterFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_register, container, false);
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        fragmentRegisterEdittextName = (MaterialEditText) mView.findViewById(R.id.fragment_register_edittext_name);
        fragmentRegisterEdittextNickname = (MaterialEditText) mView.findViewById(R.id.fragment_register_edittext_nickname);
        fragmentRegisterEdittextEmail = (MaterialEditText) mView.findViewById(R.id.fragment_register_edittext_email);
        fragmentRegisterEdittextPassword = (MaterialEditText) mView.findViewById(R.id.fragment_register_edittext_password);
        fragmentRegisterEdittextPasswordConfirmation = (MaterialEditText) mView.findViewById(R.id.fragment_register_edittext_password_confirmation);
        fragmentRegisterButtonNewAccount = (ButtonRectangle) mView.findViewById(R.id.fragment_register_button_new_account);
        linkToLogin = (TextView) mView.findViewById(R.id.link_to_login);
        fragmentRegisterButtonNewAccount.setEnabled(false);

        fragmentRegisterEdittextName.setText("Cajuste");
        fragmentRegisterEdittextNickname.setText("AC Dragnir");
        fragmentRegisterEdittextEmail.setText("cajuste.alain@gmail.com");
        fragmentRegisterEdittextPassword.setText("azerty123");
        fragmentRegisterEdittextPasswordConfirmation.setText("azerty123");

        fragmentRegisterEdittextName.addTextChangedListener(textWatcher);
        fragmentRegisterEdittextNickname.addTextChangedListener(textWatcher);
        fragmentRegisterEdittextEmail.addTextChangedListener(textWatcher);
        fragmentRegisterEdittextPassword.addTextChangedListener(textWatcher);
        fragmentRegisterEdittextPasswordConfirmation.addTextChangedListener(textWatcher);


        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthActivity.updateContent(2);
            }
        });
        fragmentRegisterButtonNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Function.isNetworkOn(getActivity())) {
                    requestFailure("Pas de connexion réseau");
                    return;
                }
                RegisterRequest request = new RegisterRequest(
                        getName(),
                        getNickname(),
                        getEmail(),
                        getPassword(),
                        getPasswordConfirmation()
                );
                progressDialog = new ProgressDialog(getActivity(), "Enregistrement en cours...", getResources().getColor(R.color.background_progress_bar_circular));
                progressDialog.setCancelable(false);
                progressDialog.show();
                getSpiceManager().execute(request,
                        "message_cache",
                        DurationInMillis.ALWAYS_EXPIRED, new RequestListener<UserResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                Log.v("onRequestFailure", spiceException.getMessage());
                                requestFailure(spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(UserResponse userResponse) {
                                Log.v("onRequestSuccess", userResponse.toString());
                                registerSuccess();
                            }
                        });
            }
        });
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateStateButtonNewAccount();
        }

        public void afterTextChanged(Editable s) {

        }
    };


    public boolean isDataValid() {
        boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
        boolean isPasswordValid = !getPassword().isEmpty();
        boolean isPasswordValidConfirmation = !getPasswordConfirmation().isEmpty();
        boolean isPasswordMatcher = getPassword().equals(getPasswordConfirmation());
        return isEmailValid && isPasswordValid && isPasswordValidConfirmation && isPasswordMatcher;
    }


    public String getName() {
        return fragmentRegisterEdittextName.getText().toString().trim();
    }

    public String getNickname() {
        return fragmentRegisterEdittextNickname.getText().toString().trim();
    }

    public String getEmail() {
        return fragmentRegisterEdittextEmail.getText().toString().trim();
    }

    public String getPassword() {
        return fragmentRegisterEdittextPassword.getText().toString().trim();
    }

    public String getPasswordConfirmation() {
        return fragmentRegisterEdittextPasswordConfirmation.getText().toString().trim();
    }


    private void updateStateButtonNewAccount() {

        if (isDataValid()) {
            fragmentRegisterButtonNewAccount.setEnabled(true);
        } else {
            fragmentRegisterButtonNewAccount.setEnabled(false);
        }
    }

    private void registerSuccess() {
        progressDialog.dismiss();
        mZurvivesPreference.setRegister(true);
        AuthActivity.updateContent(2);
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

}
