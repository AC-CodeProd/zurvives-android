package com.lpdw.zurvivescompanion.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CAJUSTE Alain on 01/06/2015.
 */
public class UserRegisterResponse extends BaseUserResponse {

    @SerializedName("success")
    protected String success;

    @SerializedName("errors")
    protected Errors errors;

    public String getSuccess() {
        return success;
    }

    public Errors getErrors() {
        return errors;
    }


    public class Errors {
        @SerializedName("email")
        String[] email;

        @SerializedName("full_messages")
        String[] fullMessages;


        public String[] getEmail() {
            return email;
        }

        public String[] getFullMessages() {
            return fullMessages;
        }
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
