package com.lpdw.zurvivescompanion.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.CheckBox;
import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.activity.AuthActivity;
import com.lpdw.zurvivescompanion.activity.MainActivity;
import com.lpdw.zurvivescompanion.data.User;
import com.lpdw.zurvivescompanion.request.SignInRequest;
import com.lpdw.zurvivescompanion.response.UserSignInResponse;
import com.lpdw.zurvivescompanion.utils.Function;
import com.lpdw.zurvivescompanion.views.widgets.ProgressDialog;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.RetrofitError;

/**
 * Created by CAJUSTE Alain on 16/06/2015.
 */
public class SignInFragment extends BaseAuthFragment {

    private MaterialEditText fragmentSignInEdittextEmail;
    private MaterialEditText fragmentSignInEdittextPassword;
    private ButtonRectangle fragmentSignInButtonLogin;
    private TextView linkToRegister;
    private TextView fragmentSignInRememberme;
    private CheckBox fragmentSignInCheckboxRememberme;

    public SignInFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        fragmentSignInEdittextEmail = (MaterialEditText) mView.findViewById(R.id.fragment_sign_in_edittext_email);
        fragmentSignInEdittextPassword = (MaterialEditText) mView.findViewById(R.id.fragment_sign_in_edittext_password);
        fragmentSignInButtonLogin = (ButtonRectangle) mView.findViewById(R.id.fragment_sign_in_button_login);
        fragmentSignInRememberme = (TextView) mView.findViewById(R.id.fragment_sign_in_rememberme);
        fragmentSignInCheckboxRememberme = (CheckBox) mView.findViewById(R.id.fragment_sign_in_checkbox_rememberme);
        linkToRegister = (TextView) mView.findViewById(R.id.link_to_register);
        fragmentSignInEdittextEmail.setText("nacho3@gmail.com");
        fragmentSignInEdittextPassword.setText("nacho1234");

        linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthActivity.updateContent(1);
            }
        });
        fragmentSignInRememberme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSignInCheckboxRememberme.setChecked(!fragmentSignInCheckboxRememberme.isCheck());
            }
        });
        fragmentSignInButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Function.isNetworkOn(getActivity())) {
                    requestFailure("Pas de connexion réseau");
                    return;
                }
                SignInRequest request = new SignInRequest(
                        getEmail(),
                        getPassword()
                );
                progressDialog = new ProgressDialog(getActivity(), "Connexion en cours...", getResources().getColor(R.color.background_progress_bar_circular));
                progressDialog.setCancelable(false);
                progressDialog.show();
                getSpiceManager().execute(request,
                        "message_cache",
                        DurationInMillis.ALWAYS_EXPIRED, new RequestListener<UserSignInResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                String message = "Login failed";
                                if (spiceException.getCause() != null && ((RetrofitError) spiceException.getCause()).getBody() != null) {
                                    message = ((UserSignInResponse) ((RetrofitError) spiceException.getCause()).getBody()).getErrors()[0];
                                } else if (spiceException.getCause() != null) {
                                    message = ((RetrofitError) spiceException.getCause()).getMessage();

                                }
                                requestFailure(message);
                            }

                            @Override
                            public void onRequestSuccess(UserSignInResponse userResponse) {
                                loginSuccess(userResponse);
                            }
                        });
            }
        });
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public boolean isDataValid() {
        boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
        boolean isPasswordValid = !getPassword().isEmpty();
        return isEmailValid && isPasswordValid;
    }

    public String getEmail() {
        return fragmentSignInEdittextEmail.getText().toString().trim();
    }

    public String getPassword() {
        return fragmentSignInEdittextPassword.getText().toString().trim();
    }

    private void loginFailure(String message) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

    private void loginSuccess(UserSignInResponse userResponse) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        Intent i;
        if (fragmentSignInCheckboxRememberme.isCheck()) {
            User.getInstance().setRememberme(fragmentSignInCheckboxRememberme.isCheck());
        }
        User.getInstance().setLogged(true);
        User.getInstance().setDataUser(userResponse.getData());
        i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }


}
