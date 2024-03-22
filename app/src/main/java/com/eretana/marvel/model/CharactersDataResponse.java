package com.eretana.marvel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharactersDataResponse {

    @SerializedName("results")
    public List<Character> results;


}
