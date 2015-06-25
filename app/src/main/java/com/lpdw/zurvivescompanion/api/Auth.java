package com.lpdw.zurvivescompanion.api;

import com.lpdw.zurvivescompanion.response.UserResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by CAJUSTE Alain on 15/06/2015.
 */
public interface Auth {
    @FormUrlEncoded
    @POST("/auth")
    UserResponse onRegister(
            @Field("name") String name,
            @Field("nickname") String nickname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("confirm_success_url") String confirm_success_url
    );

    @FormUrlEncoded
    @POST("/auth/sign_in")
    UserResponse onSignIn(
            @Field("email") String email,
            @Field("password") String password
    );


}
