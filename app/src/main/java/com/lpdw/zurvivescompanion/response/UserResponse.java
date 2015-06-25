package com.lpdw.zurvivescompanion.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CAJUSTE Alain on 01/06/2015.
 */
public class UserResponse extends BaseResponse {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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


    @Override
    public String toString() {
        if (data != null)
            return "UserResponse{" +
                    "data=" + data.toString() +
                    "}";
        return "errors";
    }
}
