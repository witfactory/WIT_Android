package com.cb.witfactory.adapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    public String title;
    public double value;
    public String color;
    public LocalDateTime timestamp;
    public String deviceId;

    // Constructor
    public Event(String title, double value, String color, String timestamp, String deviceId) {
        this.title = title;
        this.value = value;
        this.color = color;
        this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME);
        this.deviceId = deviceId;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
