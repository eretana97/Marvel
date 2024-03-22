package com.eretana.marvel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comic {

    @SerializedName("available")
    public int available;

    @SerializedName("items")
    public List<ComicItem> items;

}
