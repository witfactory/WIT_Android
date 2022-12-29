package com.cb.witfactory.data.model;

public class Device {
    Boolean state;
    String imageDevice;
    String imageWifi;
    String title;
    String location;

    public Device(){}

    public Device(Boolean state, String imageDevice, String imageWifi, String title, String location) {
        this.state = state;
        this.imageDevice = imageDevice;
        this.imageWifi = imageWifi;
        this.title = title;
        this.location = location;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getImageDevice() {
        return imageDevice;
    }

    public void setImageDevice(String imageDevice) {
        this.imageDevice = imageDevice;
    }

    public String getImageWifi() {
        return imageWifi;
    }

    public void setImageWifi(String imageWifi) {
        this.imageWifi = imageWifi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titler) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
