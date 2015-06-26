package com.lpdw.zurvivescompanion.request;

import com.lpdw.zurvivescompanion.api.Auth;
import com.lpdw.zurvivescompanion.response.UserSignInResponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by CAJUSTE Alain on 15/06/2015.
 */
public class SignInRequest extends RetrofitSpiceRequest<UserSignInResponse, Auth> {
    private String email;
    private String password;

    public SignInRequest(String email, String password) {
        super(UserSignInResponse.class, Auth.class);
        this.email = email;
        this.password = password;
    }

    @Override
    public UserSignInResponse loadDataFromNetwork() {
        return getService().onSignIn(email, password);
    }

}