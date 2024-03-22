package com.eretana.marvel.model;

import com.google.gson.annotations.SerializedName;

public class CharactersResponse {

    @SerializedName("code")
    public String code;

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public CharactersDataResponse data;



}
