package com.lpdw.zurvivescompanion.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CAJUSTE Alain on 16/06/2015.
 */
public abstract class BaseResponse {
    @SerializedName("success")
    protected String success;

    @SerializedName("errors")
    protected String[] errors;


    public String getSuccess() {
        return success;
    }

    public String[] getErrors() {
        return errors;
    }
}