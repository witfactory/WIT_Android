package com.cb.witfactory.data.retrofit.device;

import com.google.gson.annotations.SerializedName;

public class DeviceResponse {


    @SerializedName("date_entered")
    private String date_entered;

    @SerializedName("lon")
    private String lon;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("device_name")
    private String device_name;

    @SerializedName("mac")
    private String mac;

    @SerializedName("date_modified")
    private String date_modified;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("deleted")
    private String deleted;

    @SerializedName("device_location")
    private String device_location;

    @SerializedName("device_serial")
    private String device_serial;

    @SerializedName("device_type")
    private String device_type;

    @SerializedName("lat")
    private String lat;

    @SerializedName("description_location")
    private String description_location;

    public DeviceResponse(String date_entered, String lon, String device_id, String device_name, String mac, String date_modified, String user_id, String deleted, String device_location, String device_serial, String device_type, String lat, String description_location) {
        this.date_entered = date_entered;
        this.lon = lon;
        this.device_id = device_id;
        this.device_name = device_name;
        this.mac = mac;
        this.date_modified = date_modified;
        this.user_id = user_id;
        this.deleted = deleted;
        this.device_location = device_location;
        this.device_serial = device_serial;
        this.device_type = device_type;
        this.lat = lat;
        this.description_location = description_location;
    }

    public String getDate_entered() {
        return date_entered;
    }

    public void setDate_entered(String date_entered) {
        this.date_entered = date_entered;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDevice_location() {
        return device_location;
    }

    public void setDevice_location(String device_location) {
        this.device_location = device_location;
    }

    public String getDevice_serial() {
        return device_serial;
    }

    public void setDevice_serial(String device_serial) {
        this.device_serial = device_serial;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDescription_location() {
        return description_location;
    }

    public void setDescription_location(String description_location) {
        this.description_location = description_location;
    }
}