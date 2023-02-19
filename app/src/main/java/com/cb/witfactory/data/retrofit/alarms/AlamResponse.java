package com.cb.witfactory.data.retrofit.alarms;

import com.google.gson.annotations.SerializedName;

public class AlamResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("location")
    private String location;

    public AlamResponse(String id, String username, String location) {
        this.id = id;
        this.username = username;
        this.location = location;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
