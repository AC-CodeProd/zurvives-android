package com.lpdw.zurvivescompanion.request;

import com.google.gson.annotations.SerializedName;
import com.lpdw.zurvivescompanion.api.Auth;
import com.lpdw.zurvivescompanion.response.UserResponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by CAJUSTE Alain on 15/06/2015.
 */
public class SignInRequest extends RetrofitSpiceRequest<UserResponse, Auth> {
    private String email;
    private String password;

    public SignInRequest(String email, String password) {
        super(UserResponse.class, Auth.class);
        this.email = email;
        this.password = password;
    }

    @Override
    public UserResponse loadDataFromNetwork() {
        return getService().onSignIn(email, password);
    }

}