package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

public class
Data {
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("led")
    private boolean led;
    @SerializedName("device_id")
    private String device_id;
    @SerializedName("db_counter")
    private int db_counter;
    @SerializedName("id")
    private String id;
    @SerializedName("user_name")
    private String user_name;

    public Data(){}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isLed() {
        return led;
    }

    public void setLed(boolean led) {
        this.led = led;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getDb_counter() {
        return db_counter;
    }

    public void setDb_counter(int db_counter) {
        this.db_counter = db_counter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
