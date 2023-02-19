package com.cb.witfactory.data.retrofit.device;

import com.google.gson.annotations.SerializedName;

public class UpdateDeviceResponse {
    @SerializedName("Key")
    private KeyDeviceResponse Key;

    @SerializedName("mac")
    private String mac;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lon")
    private String lon;

    @SerializedName("device_serial")
    private String device_serial;

    @SerializedName("device_name")
    private String device_name;

    @SerializedName("device_location")
    private String device_location;


    @SerializedName("description_location")
    private String description_location;

    public UpdateDeviceResponse(KeyDeviceResponse key, String mac, String lat, String lon, String device_serial, String device_name, String device_location, String description_location) {
        Key = key;
        this.mac = mac;
        this.lat = lat;
        this.lon = lon;
        this.device_serial = device_serial;
        this.device_name = device_name;
        this.device_location = device_location;
        this.description_location = description_location;
    }

    public KeyDeviceResponse getKey() {
        return Key;
    }

    public void setKey(KeyDeviceResponse key) {
        Key = key;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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

    public String getDevice_serial() {
        return device_serial;
    }

    public void setDevice_serial(String device_serial) {
        this.device_serial = device_serial;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_location() {
        return device_location;
    }

    public void setDevice_location(String device_location) {
        this.device_location = device_location;
    }

    public String getDescription_location() {
        return description_location;
    }

    public void setDescription_location(String description_location) {
        this.description_location = description_location;
    }
}
