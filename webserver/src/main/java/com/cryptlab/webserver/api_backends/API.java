package com.cryptlab.webserver.api_backends;

public abstract class API<T> {
    public final String url;
    public final String key;

    public API (String url) {
        this.url = url;
        this.key = null;
    }

    public API (String url, String key) {
        this.url = url;
        this.key = key;
    }

    public abstract T get ();
    public abstract void post (T data);
}