package com.lpdw.zurvivescompanion.data;

import com.lpdw.zurvivescompanion.response.UserSignInResponse;

/**
 * Created by CAJUSTE Alain on 15/06/2015.
 */
public class User {
    private static User _instance = null;

    private String email;
    private int id;
    private String image;
    private String name;
    private String nickname;
    private String provider;
    private String uid;
    private Boolean rememberme = false;

    private Boolean isLogged = false;

    private User() {
    }

    public static User getInstance() {
        if (_instance == null) {
            _instance = new User();
        }
        return _instance;
    }

    public static void onDestroyUser() {
        _instance = null;
    }

    public void setDataUser(UserSignInResponse.Data data) {
        this.name = data.getName();
        this.nickname = data.getNickname();
        this.email = data.getEmail();
        this.id = data.getId();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setRememberme(Boolean value) {
        this.rememberme = value;
    }

    public void setLogged(Boolean value) {
        this.isLogged = value;
    }

    public Boolean isLogged() {
        return isLogged;
    }

    public Boolean isRememberme() {
        return this.rememberme;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}