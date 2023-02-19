package com.cb.witfactory.data.retrofit.user;

import com.google.gson.annotations.SerializedName;

public class keyUserResponse {

    @SerializedName("id")
    private String id;

    public keyUserResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
