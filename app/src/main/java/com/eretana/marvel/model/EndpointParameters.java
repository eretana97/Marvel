package com.eretana.marvel.model;

import android.util.Log;

import com.eretana.marvel.BuildConfig;
import com.eretana.marvel.tools.MD5Crypt;

public class EndpointParameters {

    private String apikey;
    private String privatekey;
    private long timestamp;
    private String hash;

    public EndpointParameters(){
        this.timestamp = System.currentTimeMillis();

        this.apikey = BuildConfig.PUBLIC_API_KEY;
        this.privatekey = BuildConfig.PRIVATE_API_KEY;

        this.hash = MD5Crypt.md5encrypt(this.timestamp + this.privatekey + this.apikey);
    }

    public String getApikey() {
        return apikey;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }
}
