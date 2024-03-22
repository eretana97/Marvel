package com.eretana.marvel.model;

import android.util.Log;

import com.eretana.marvel.tools.MD5Crypt;

public class EndpointParameters {

    private String apikey;
    private String privatekey;
    private long timestamp;
    private String hash;

    public EndpointParameters(){
        this.timestamp = System.currentTimeMillis();
        this.apikey = "95ff5465dd2a684665d40290e3586b08";
        this.privatekey = "f1938d6e84678cd99afae3eaf684e9fc26867992";
        this.hash = MD5Crypt.md5encrypt(this.timestamp + this.privatekey + this.apikey);
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
