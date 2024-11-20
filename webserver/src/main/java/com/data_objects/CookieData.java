package com.data_objects;

public class CookieData {
    public long duration;
    public long timestamp;
    public String value;

    public CookieData (long duration, long timestamp, String value) {
        this.duration = duration;
        this.timestamp = timestamp;
        this.value = value;
    }
}
