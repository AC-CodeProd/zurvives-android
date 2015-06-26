package com.lpdw.zurvivescompanion.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CAJUSTE Alain on 01/06/2015.
 */
public class UserSignInResponse extends BaseUserResponse {

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


    @Override
    public String toString() {
        if (data != null)
            return "UserSignInResponse{" +
                    "data=" + data.toString() +
                    "}";
        return "errors";
    }
}
