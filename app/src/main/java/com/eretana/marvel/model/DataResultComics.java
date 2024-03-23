package com.eretana.marvel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResultComics {

    @SerializedName("results")
    public List<Comic> results;

}
