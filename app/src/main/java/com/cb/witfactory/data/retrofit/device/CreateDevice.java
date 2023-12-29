package com.cb.witfactory.data.retrofit.device;


import com.google.gson.annotations.SerializedName;

public class CreateDevice {

    @SerializedName("device_name")
    private String device_name;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("device_serial")
    private String device_serial;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lon")
    private String lon;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("mac")
    private String mac;

    @SerializedName("device_location")
    private String device_location;

    @SerializedName("device_type")
    private String device_type;

    @SerializedName("description_location")
    private String description_location;


    public CreateDevice(String device_name, String device_id, String device_serial, String lat, String lon, String user_id, String mac, String device_location, String device_type, String description_location) {
        this.device_name = device_name;
        this.device_id = device_id;
        this.device_serial = device_serial;
        this.lat = lat;
        this.lon = lon;
        this.user_id = user_id;
        this.mac = mac;
        this.device_location = device_location;
        this.device_type = device_type;
        this.description_location = description_location;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_serial() {
        return device_serial;
    }

    public void setDevice_serial(String device_serial) {
        this.device_serial = device_serial;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDevice_location() {
        return device_location;
    }

    public void setDevice_location(String device_location) {
        this.device_location = device_location;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDescription_location() {
        return description_location;
    }

    public void setDescription_location(String description_location) {
        this.description_location = description_location;
    }
}

