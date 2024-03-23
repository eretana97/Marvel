package com.eretana.marvel.model;

import com.google.gson.annotations.SerializedName;

public class ComicsResponse {

    @SerializedName("code")
    public String code;

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public DataResultComics data;

}
