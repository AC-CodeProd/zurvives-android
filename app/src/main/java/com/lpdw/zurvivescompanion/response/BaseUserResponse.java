package com.lpdw.zurvivescompanion.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CAJUSTE Alain on 16/06/2015.
 */
public abstract class BaseUserResponse {

    @SerializedName("data")
    protected Data data;

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("email")
        private String email;

        @SerializedName("id")
        private int id;

        @SerializedName("image")
        private String image;

        @SerializedName("name")
        private String name;

        @SerializedName("nickname")
        private String nickname;

        @SerializedName("provider")
        private String provider;

        @SerializedName("uid")
        private String uid;

        public String getEmail() {
            return email;
        }


        public String getName() {
            return name;
        }


        public int getId() {
            return id;
        }


        public String getImage() {
            return image;
        }


        public String getNickname() {
            return nickname;
        }


        public String getProvider() {
            return provider;
        }


        public String getUid() {
            return uid;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "email='" + email + '\'' +
                    ", id=" + id +
                    ", image='" + image + '\'' +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", provider='" + provider + '\'' +
                    ", uid='" + uid + '\'' +
                    "}";
        }
    }
}