package com.eretana.marvel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comic {

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("thumbnail")
    public Thumbnail thumbnail;

    @SerializedName("prices")
    public List<Price> prices;

    @SerializedName("urls")
    public List<Url> urls;


}
