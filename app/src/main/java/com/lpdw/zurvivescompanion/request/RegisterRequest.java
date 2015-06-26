package com.lpdw.zurvivescompanion.request;

import com.lpdw.zurvivescompanion.api.Auth;
import com.lpdw.zurvivescompanion.response.UserRegisterResponse;
import com.lpdw.zurvivescompanion.response.UserSignInResponse;
import com.lpdw.zurvivescompanion.utils.Function;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by CAJUSTE Alain on 15/06/2015.
 */
public class RegisterRequest extends RetrofitSpiceRequest<UserRegisterResponse, Auth> {
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String password_confirmation;
    private String confirm_success_url = Function.getServerUrl() + "/login";

    public RegisterRequest(String name, String nickname, String email, String password, String password_confirmation) {
        super(UserRegisterResponse.class, Auth.class);
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    @Override
    public UserRegisterResponse loadDataFromNetwork() {
        return getService().onRegister(name, nickname, email, password, password_confirmation, confirm_success_url);
    }

}