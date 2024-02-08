package com.cb.witfactory.data.retrofit.alarms;

import com.google.gson.annotations.SerializedName;

public class ClosedOpenValve {

    @SerializedName("username")
    private String username;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("state")
    private Integer state;


    public ClosedOpenValve(String username, String device_id, Integer state) {
        this.username = username;
        this.device_id = device_id;
        this.state = state;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
