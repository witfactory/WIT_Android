package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

public class
EventsRealtime {

    @SerializedName("device_id")
    private String device_id;
    @SerializedName("user")
    private String user;

    public EventsRealtime(String device_id, String user) {
        this.device_id = device_id;
        this.user = user;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
