package com.cb.witfactory.data.model;

public class ValueDevice {
    String imageDevice;
    String title;
    Boolean state;
    String description;

    public ValueDevice(){}

    public ValueDevice(String imageDevice, String title, Boolean state, String description) {
        this.imageDevice = imageDevice;
        this.title = title;
        this.state = state;
        this.description = description;
    }

    public String getImageDevice() {
        return imageDevice;
    }

    public void setImageDevice(String imageDevice) {
        this.imageDevice = imageDevice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
